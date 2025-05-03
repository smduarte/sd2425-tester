package fctreddit.impl.grpc.common;

import java.util.function.Consumer;
import java.util.function.Supplier;

import fctreddit.api.Post;
import fctreddit.api.User;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GrpcPost;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.GrpcUser;

public class DataModelAdaptor {

	public static User GrpcUser_to_User( GrpcUser from )  {
		var b = new User();
		
		setOptionalField(from::hasUserId, from::getUserId, b::setUserId );
		setOptionalField(from::hasFullName, from::getFullName, b::setFullName );
		setOptionalField(from::hasEmail, from::getEmail, b::setEmail );
		setOptionalField(from::hasPassword, from::getPassword, b::setPassword );
		setOptionalField(from::hasAvatarUrl, from::getAvatarUrl, b::setAvatarUrl );
		
		return b;
	}

	public static GrpcUser User_to_GrpcUser( User from )  {
		var b = GrpcUser.newBuilder();
		setOptionalFieldIfNotNull( from::getUserId, b::setUserId);
		setOptionalFieldIfNotNull( from::getEmail, b::setEmail);
		setOptionalFieldIfNotNull( from::getFullName, b::setFullName);
		setOptionalFieldIfNotNull( from::getPassword, b::setPassword );
		setOptionalFieldIfNotNull( from::getAvatarUrl, b::setAvatarUrl);
		return b.build();
	}
	
	public static GrpcPost Post_to_GrpcPost( Post from )  {
		var b = GrpcPost.newBuilder();

		setOptionalFieldIfNotNull( from::getPostId, b::setPostId);
		setOptionalFieldIfNotNull( from::getUpVote, b::setUpVote);
		setOptionalFieldIfNotNull( from::getContent, b::setContent);
		setOptionalFieldIfNotNull( from::getDownVote, b::setDownVote);
		setOptionalFieldIfNotNull( from::getMediaUrl, b::setMediaUrl);
		setOptionalFieldIfNotNull( from::getAuthorId, b::setAuthorId);
		setOptionalFieldIfNotNull( from::getParentUrl, b::setParentUrl);
		setOptionalFieldIfNotNull( from::getCreationTimestamp, b::setCreationTimestamp);		
		return b.build();
	}

	public static Post GrpcPost_to_Post( GrpcPost from )  {
		var b = new Post();

		setOptionalField(from::hasPostId, from::getPostId, b::setPostId );
		setOptionalField(from::hasAuthorId, from::getAuthorId, b::setAuthorId );
		setOptionalField(from::hasContent, from::getContent, b::setContent );
		setOptionalField(from::hasCreationTimestamp, from::getCreationTimestamp, b::setCreationTimestamp );
		setOptionalField(from::hasParentUrl, from::getParentUrl, b::setParentUrl );
		setOptionalField(from::hasMediaUrl, from::getMediaUrl, b::setMediaUrl );
		setOptionalField(from::hasUpVote, from::getUpVote, b::setUpVote );
		setOptionalField(from::hasDownVote, from::getDownVote, b::setDownVote );

		return b;
	}
	
	static <T> void setOptionalFieldIfNotNull( Supplier<T> t, Consumer<T> b) {
		var v = t.get();
		if( v != null )
			b.accept(v);
	}
	
	static <T> void setOptionalField(Supplier<Boolean> cond, Supplier<T> from, Consumer<T> to ) {
		if( cond.get() )
			to.accept( from.get() );
	}
}
