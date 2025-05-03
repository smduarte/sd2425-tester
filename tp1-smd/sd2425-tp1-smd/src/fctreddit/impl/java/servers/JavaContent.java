package fctreddit.impl.java.servers;

import static fctreddit.api.java.Result.error;
import static fctreddit.api.java.Result.ok;
import static fctreddit.api.java.Result.ErrorCode.BAD_REQUEST;
import static fctreddit.api.java.Result.ErrorCode.INTERNAL_ERROR;
import static fctreddit.impl.java.clients.Clients.ImageAdmin;
import static fctreddit.impl.java.clients.Clients.UsersAdmin;
import static java.lang.String.format;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fctreddit.api.Post;
import fctreddit.api.java.Content;
import fctreddit.api.java.Result;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.java.servers.data.Vote;
import fctreddit.impl.persistence.DB;

public class JavaContent implements Content, ContentAdmin {

	private static Logger Log = Logger.getLogger(JavaContent.class.getName());
	
	private static final long USER_CACHE_EXPIRATION = 3000;

	protected static final String TOKEN = "";

	static record Credentials(String userId, String pwd) {}

	static record TotalVotes( long upVotes, long downVotes ) {}
	
	private AtomicLong counter = new AtomicLong(0L);
		
	protected final LoadingCache<Credentials, Result<Void>> usersCache = CacheBuilder.newBuilder()
			.expireAfterWrite(Duration.ofMillis(USER_CACHE_EXPIRATION))
			.build(new CacheLoader<>() {
				@Override
				public Result<Void> load(Credentials u) throws Exception {
					return UsersAdmin.get().checkUser(u.userId(), u.pwd(), TOKEN);
				}
			});


	private String nextPostId() {
		return Long.toString(counter.incrementAndGet(), Character.MAX_RADIX); //use largest base to make strings smaller, using numbers and letters
	}
	

	private JavaContent() {}
	private static JavaContent instance;
	
	public static synchronized JavaContent getInstance() {
		if( instance == null )
			instance = new JavaContent();
		return instance;
	}
	
	
	@Override
	public Result<String> createPost(Post post, String userPassword) {
		Log.info(format("createPost : post = %s, pwd = %s\n", post, userPassword));
				
		if( post.notValid() )
			return error(BAD_REQUEST);

		var parentId = post.computeParentId();

		post.setCreationTimestamp( System.currentTimeMillis() );						
		post.setParentId( parentId );

		if( parentId != null ) // post is a reply
			return checkUser(post.getAuthorId(), userPassword)					
						.andThen( () -> DB.transaction( hibernate ->
											hibernate.getOne(parentId, Post.class)
												.andThen( () -> {
													Monitors.notifyAll(parentId);
													post.setPostId( parentId + '-' + nextPostId() );
													return DB.insertOne( post ).mapValue( p -> p.getPostId());
												})
			));
		
		//post is a top-level post...
		post.setPostId( nextPostId() );
		return checkUser(post.getAuthorId(), userPassword)
				.andThen(() -> DB.insertOne( post ).mapValue( p -> p.getPostId()));
	}

	private Result<List<String>> getNewestPostsFirst(long timestamp) {
		var sql = format(
			"""
				SELECT postId
				FROM Post
				WHERE parentUrl IS NULL AND creationTimestamp >= %d
				ORDER BY creationTimestamp ASC, postId ASC
			""", timestamp);
		
		return DB.query( sql, String.class);		
	}
	
	private Result<List<String>> getMostRepliedPostsFirst(long timestamp) {
		var sql = format(
			"""
				SELECT p.postId, COUNT(c.postId) AS child_count
				FROM Post p
				LEFT JOIN Post c ON c.parentId = p.postId
				WHERE p.parentId IS NULL AND p.creationTimestamp >= %d
				GROUP BY p.postId
				ORDER BY child_count DESC, p.postId ASC;
			""", timestamp);
		
		return DB.query( sql, String.class);		
	}
	
	private Result<List<String>> getMostUpVotedPostsFirst(long timestamp) {	
		var sql = format(
				"""
					SELECT p.postId, COALESCE(o.upVotes, 0) AS upVotes
					FROM Post p
					LEFT JOIN (
						SELECT postId, COUNT(CASE WHEN sentiment > 0 THEN 1 END) AS upVotes
						FROM Vote 
						GROUP BY postId 
					) o ON p.postId = o.postId
					WHERE p.parentUrl IS NULL AND p.creationTimestamp >= %d
					ORDER BY upVotes DESC, p.postId ASC;
				""", timestamp);
		
		return DB.query( sql, String.class);		
	}
	
	@Override
	public Result<List<String>> getPosts(long timestamp, String sortOrder) {
		if( sortOrder == null || sortOrder.isEmpty() )
			return getNewestPostsFirst(timestamp);
		
		return switch( sortOrder) {
			case Content.MOST_REPLIES -> getMostRepliedPostsFirst(timestamp);
			case Content.MOST_UP_VOTES -> getMostUpVotedPostsFirst(timestamp);
			default -> error(BAD_REQUEST);
		};
	}

	@Override
	public Result<Post> getPost(String postId) {
		Log.info(format("getPost : postId = %s\n", postId ));
		
		if( postId == null || postId.isBlank() )
			return error(BAD_REQUEST );
			
		return DB.getOne(postId, Post.class )
				.andThenWith( post -> countVotes( postId ).mapValue(votes -> post.copyWithVotes(votes.upVotes, votes.downVotes)));
	}

	@Override
	public Result<List<String>> getPostAnswers(String postId, long maxTimeout) {
		if( maxTimeout > 0 )
			Monitors.waitFor( postId, maxTimeout);
		
		var sql = format("""     
							SELECT postId 
							FROM Post  
							WHERE parentUrl LIKE '%%/%s' 
							ORDER BY creationTimestamp ASC
						""", postId );

		return getPost(postId)
				.andThenWith( post -> DB.query(sql, String.class));
	}
	
	@Override
	public Result<Post> updatePost(String postId, String userPassword, Post update) {
		Log.info(format("updatePost : postId = %s, userPassword=%s, update=%s\n", postId, userPassword, update ));
		
		return getPost(postId)
				.andThenWith( post -> getPostAnswers( postId, 0L).mapResult( replies -> replies.size() > 0 ? error(BAD_REQUEST) : ok(post))
				.andThen( () -> countVotes(postId).mapResult( votes -> (votes.upVotes > 0 || votes.downVotes > 0) ? error(BAD_REQUEST) : ok(post)))
				.andThen( () -> checkUser( post.getAuthorId(), userPassword))
				.andThen( () -> DB.updateOne(post.updateFrom( update ))));	
	}

	@Override
	public Result<Void> deletePost(String postId, String userPassword) {
		Log.info(format("deletePost : postId = %s, userPassword=%s\n", postId, userPassword ));

		return getPost(postId)
				.andThenWith( post -> checkUser( post.getAuthorId(), userPassword).andThen(() -> deletePostAndChildren(post))) ;
	}

	private Result<Void> deletePostAndChildren( Post post ) {
		var sqlStatement = format(
				"""
					DELETE FROM Post
					WHERE postId = '%s' OR postId LIKE '%s-%%';
				""", post.getPostId(), post.getPostId() );

		return DB.exec(sqlStatement)
				.andThen( () -> ImageAdmin.get().deleteUserImage( post.getAuthorId(), post.getMediaUrl(), TOKEN));	
	}
	
	@Override
	public Result<Void> upVotePost(String postId, String userId, String userPassword) {
		Log.info(format("upVotePost : postId = %s, userId = %s, pwd = %s\n", postId, userId, userPassword));

		return getPost(postId)
			.mapValue( post -> new Vote(userId, postId, Vote.UP))
			.andThenWith( vote -> checkUser( userId, userPassword).andThen( () -> DB.insertOne(vote) ).mapToVoid() );
	}
	
	@Override
	public Result<Void> downVotePost(String postId, String userId, String userPassword) {
		Log.info(() -> format("upVotePost : postId = %s, userId = %s, pwd = %s\n", postId, userId, userPassword));

		return getPost(postId)
				.mapValue( post -> new Vote(userId, postId, Vote.DOWN))
				.andThenWith( vote -> checkUser( userId, userPassword).andThen( () -> DB.insertOne(vote) ).mapToVoid() );
	}

	@Override
	public Result<Void> removeUpVotePost(String postId, String userId, String userPassword) {
		Log.info(() -> format("remove upVotePost : postId = %s, userId = %s, pwd = %s\n", postId, userId, userPassword));
		
		return checkUser( userId, userPassword)
				.andThen( () -> DB.deleteOne( new Vote( postId, userId, Vote.UP ) ).mapToVoid());
	}

	@Override
	public Result<Void> removeDownVotePost(String postId, String userId, String userPassword) {
		Log.info(() -> format("remove downVotePost : postId = %s, userId = %s, pwd = %s\n", postId, userId, userPassword));
		
		return checkUser( userId, userPassword)
				.andThen( () -> DB.deleteOne( new Vote( postId, userId, Vote.DOWN ) ).mapToVoid());
	}

	@Override
	public Result<Integer> getupVotes(String postId) {
		return countVotes( postId ).mapValue( votes -> (int)votes.upVotes );
	}

	@Override
	public Result<Integer> getDownVotes(String postId) {
		return countVotes( postId ).mapValue( votes -> (int)votes.downVotes );
	}
	
	public Result<TotalVotes> countVotes(String postId) {
		var sql = format("""
					SELECT
						COUNT(CASE WHEN sentiment > 0 THEN 1 END) AS upVotes,
						COUNT(CASE WHEN sentiment < 0 THEN 1 END) AS downVotes
					FROM Vote 
					WHERE postId = '%s';
					""", postId);
		
		return DB.query(sql, TotalVotes.class).mapValue( list -> list.get(0) );
	}
			
	protected Result<Void> checkUser( String userId, String pwd) {
		try {
			return usersCache.get( new Credentials(userId, pwd));
		} catch (Exception x) {
			x.printStackTrace();
			return Result.error(INTERNAL_ERROR);
		}
	}
	
	@Override
	public Result<Void> handleDeletedUser(String userId, String token ) {
		Log.info(() -> format("Handling deleted user: %s, token: %s", userId, token ));

		//TODO: Validate token
		return DB.transaction( (hibernate) -> {
			var sqlUpdate1 = format("""
					UPDATE Post
					SET authorId = NULL
					WHERE authorId = '%s'
					""", userId);	
			
			hibernate.update( sqlUpdate1);
			
			var sqlUpdate2 = format("""
					DELETE FROM Vote
					WHERE userId = '%s'
					""", userId);
			
			hibernate.update(sqlUpdate2);
			return ok();
		});
	}
}

class Monitors {
	
	static final Map<String, Object> monitors = new ConcurrentHashMap<>();

	static void notifyAll(String postId) {
		var m = monitors.get(postId);
		if( m != null) synchronized(m) {
			m.notifyAll();
		}
	}
	
	static void waitFor( String postId, long ms ) {
		Object m;
		synchronized( m = monitors.computeIfAbsent(postId, __ -> new Object() )) {
			long remaining;
			long deadline = System.currentTimeMillis() + ms;
			while( (remaining = deadline - System.currentTimeMillis()) > 0)
			try { 
				m.wait( remaining ); 
				break;
			} catch( InterruptedException x ) {};
			
			monitors.remove( postId );
		}
	}
}
