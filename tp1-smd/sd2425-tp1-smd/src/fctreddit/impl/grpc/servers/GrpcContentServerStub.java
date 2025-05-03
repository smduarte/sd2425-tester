package fctreddit.impl.grpc.servers;

import static fctreddit.impl.grpc.common.DataModelAdaptor.GrpcPost_to_Post;
import static fctreddit.impl.grpc.common.DataModelAdaptor.Post_to_GrpcPost;

import com.google.protobuf.Empty;

import fctreddit.api.java.Content;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.grpc.generated_java.ContentAdminGrpc;
import fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs;
import fctreddit.impl.grpc.generated_java.ContentGrpc;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.ChangeVoteArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.CreatePostArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.CreatePostResult;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.DeletePostArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.EmptyMessage;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GetPostAnswersArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GetPostArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GetPostsArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GetPostsResult;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.GrpcPost;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.UpdatePostArgs;
import fctreddit.impl.grpc.generated_java.ContentProtoBuf.VoteCountResult;
import fctreddit.impl.java.servers.JavaContent;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class GrpcContentServerStub extends AbstractGrpcStub implements ContentGrpc.AsyncService {

	final Content impl = JavaContent.getInstance();
		
	@Override
	public final ServerServiceDefinition bindService() {
		return ContentGrpc.bindService(this);
	}

    public void createPost(CreatePostArgs request, StreamObserver<CreatePostResult> responseObserver) {
    	super.toGrpcResult( responseObserver, 
    			impl.createPost(GrpcPost_to_Post(request.getPost()), request.getPassword()), 
    			(postId) -> CreatePostResult.newBuilder().setPostId( postId ).build());    		
    }

    public void getPosts(GetPostsArgs request, StreamObserver<GetPostsResult> responseObserver) {
    	super.toGrpcResult( responseObserver, 
    			impl.getPosts( request.getTimestamp(), request.getSortOrder()), 
    			(list) -> GetPostsResult.newBuilder().addAllPostId(list).build());    		
    }

    public void getPost(GetPostArgs request, StreamObserver<GrpcPost> responseObserver) {
    	super.toGrpcResult( responseObserver, 
    			impl.getPost( request.getPostId()), 
    			(post) -> Post_to_GrpcPost(post));    		
    }

    public void getPostAnswers(GetPostAnswersArgs request, StreamObserver<GetPostsResult> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.getPostAnswers( request.getPostId(), request.getTimeout()),
    			(list) -> GetPostsResult.newBuilder().addAllPostId(list).build());    		
    }

    public void updatePost(UpdatePostArgs request, StreamObserver<GrpcPost> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.updatePost( request.getPostId(), request.getPassword(), GrpcPost_to_Post(request.getPost())),
    			(post) -> Post_to_GrpcPost(post));    			
    }

    public void deletePost(DeletePostArgs request, StreamObserver<EmptyMessage> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.deletePost( request.getPostId(), request.getPassword()),
    			(__) -> EmptyMessage.newBuilder().build());    			
    }

    public void upVotePost(ChangeVoteArgs request, StreamObserver<EmptyMessage> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.upVotePost( request.getPostId(), request.getUserId(), request.getPassword()),
    			(__) -> EmptyMessage.newBuilder().build());    			
    }

    public void removeUpVotePost(ChangeVoteArgs request, StreamObserver<EmptyMessage> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.removeUpVotePost( request.getPostId(), request.getUserId(), request.getPassword()),
    			(__) -> EmptyMessage.newBuilder().build());    			
    }

    public void downVotePost(ChangeVoteArgs request, StreamObserver<EmptyMessage> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.downVotePost( request.getPostId(), request.getUserId(), request.getPassword()),
    			(__) -> EmptyMessage.newBuilder().build());    			
    }

    public void removeDownVotePost(ChangeVoteArgs request, StreamObserver<EmptyMessage> responseObserver) {
       	super.toGrpcResult( responseObserver, 
    			impl.removeDownVotePost( request.getPostId(), request.getUserId(), request.getPassword()),
    			(__) -> EmptyMessage.newBuilder().build());    			
    }

    public void getUpVotes(GetPostArgs request, StreamObserver<VoteCountResult> responseObserver) {
      	super.toGrpcResult( responseObserver, 
    			impl.getupVotes( request.getPostId()),
    			(votes) -> VoteCountResult.newBuilder().setCount(votes).build());    			
   }

    public void getDownVotes(GetPostArgs request, StreamObserver<VoteCountResult> responseObserver) {
     	super.toGrpcResult( responseObserver, 
    			impl.getDownVotes( request.getPostId()),
    			(votes) -> VoteCountResult.newBuilder().setCount(votes).build()); 
    } 
}

class GrpcContentAdminServerStub extends AbstractGrpcStub implements ContentAdminGrpc.AsyncService {

	final ContentAdmin admin = JavaContent.getInstance();
	
	@Override
	public final ServerServiceDefinition bindService() {
		return ContentAdminGrpc.bindService(this);
	}
    
    public void handleDeletedUser(HandleDeletedUserArgs request, StreamObserver<Empty> responseObserver) {
      	super.toGrpcResult( responseObserver, 
    			admin.handleDeletedUser(request.getUserId(), request.getToken()),
    			(__) -> Empty.newBuilder().build());    			
    }
}
