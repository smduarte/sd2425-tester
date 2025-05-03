package fctreddit.impl.rest.servers;

import java.util.List;

import fctreddit.api.Post;
import fctreddit.api.java.Content;
import fctreddit.api.rest.RestContent;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.api.rest.RestContentAdmin;
import fctreddit.impl.java.servers.JavaContent;
import jakarta.inject.Singleton;

@Singleton
public class RestContentResource extends RestResource implements RestContent, RestContentAdmin {

	final Content impl = JavaContent.getInstance();
	final ContentAdmin admin = JavaContent.getInstance();
	
	@Override
	public String createPost(Post post, String userPassword) {
		return super.resultOrThrow( impl.createPost(post, userPassword));
	}
	
	@Override
	public List<String> getPosts(long timestamp, String sortOrder) {
		return super.resultOrThrow( impl.getPosts(timestamp, sortOrder));
	}
	
	@Override
	public Post getPost(String postId) {
		return super.resultOrThrow( impl.getPost(postId));
	}
	
	@Override
	public List<String> getPostAnswers(String postId, long maxTimeout) {
		return super.resultOrThrow( impl.getPostAnswers(postId, maxTimeout));
	}
	
	@Override
	public Post updatePost(String postId, String userPassword, Post post) {
		return super.resultOrThrow( impl.updatePost(postId, userPassword, post));
	}
	
	@Override
	public void deletePost(String postId, String userPassword) {
		super.resultOrThrow( impl.deletePost(postId, userPassword));
	}
	
	@Override
	public void upVotePost(String postId, String userId, String userPassword) {
		super.resultOrThrow( impl.upVotePost(postId, userId, userPassword));
	}
	
	@Override
	public void removeUpVotePost(String postId, String userId, String userPassword) {
		super.resultOrThrow( impl.removeUpVotePost(postId, userId, userPassword));
	}
	
	@Override
	public void downVotePost(String postId, String userId, String userPassword) {
		super.resultOrThrow( impl.downVotePost(postId, userId, userPassword));
	}
	
	@Override
	public void removeDownVotePost(String postId, String userId, String userPassword) {
		super.resultOrThrow( impl.removeDownVotePost(postId, userId, userPassword));
	}
	
	@Override
	public Integer getupVotes(String postId) {
		return super.resultOrThrow( impl.getupVotes(postId));
	}
	
	@Override
	public Integer getDownVotes(String postId) {
		return super.resultOrThrow( impl.getDownVotes(postId));
	}
	
	@Override
	public void handleDeletedUser(String userId, String token) {
		super.resultOrThrow( admin.handleDeletedUser(userId, token));
	}
}
