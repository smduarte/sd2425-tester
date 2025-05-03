package fctreddit.impl.grpc.servers;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;

import fctreddit.api.java.Image;
import fctreddit.impl.api.java.ImageAdmin;
import fctreddit.impl.grpc.generated_java.ImageAdminGrpc;
import fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs;
import fctreddit.impl.grpc.generated_java.ImageGrpc;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.CreateImageArgs;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.CreateImageResult;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.DeleteImageArgs;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.DeleteImageResult;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.GetImageArgs;
import fctreddit.impl.grpc.generated_java.ImageProtoBuf.GetImageResult;
import fctreddit.impl.java.servers.JavaImage;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class GrpcImageServerStub extends AbstractGrpcStub implements ImageGrpc.AsyncService {

	final Image impl = JavaImage.getInstance(null);
	
	@Override
	public ServerServiceDefinition bindService() {
		return ImageGrpc.bindService(this);
	}
	
	@Override
    public void createImage(CreateImageArgs request, StreamObserver<CreateImageResult> responseObserver) {		
		super.toGrpcResult(responseObserver, 
			impl.createImage(request.getUserId(), request.getImageContents().toByteArray(), request.getPassword()),
			(imageId) -> CreateImageResult.newBuilder().setImageId( imageId ).build());
    }

    
    public void getImage(GetImageArgs request, StreamObserver<ImageProtoBuf.GetImageResult> responseObserver) {
    	var userId = request.getUserId().isEmpty() ? null : request.getUserId();
    	var imageId = request.getImageId().isEmpty() ? null : request.getImageId();
    	
    	super.toGrpcResult(responseObserver, 
    		impl.getImage(userId, imageId),
    		(bytes) -> GetImageResult.newBuilder().setData( ByteString.copyFrom(bytes)).build());
		}
   
    public void deleteImage(DeleteImageArgs request, StreamObserver<ImageProtoBuf.DeleteImageResult> responseObserver) {
		
    	super.toGrpcResult(responseObserver, 
    		impl.deleteImage(request.getUserId(), request.getImageId(), request.getPassword()),
    		(__) -> DeleteImageResult.newBuilder().build());
    }
}

class GrpcImageAdminServerStub extends AbstractGrpcStub implements ImageAdminGrpc.AsyncService{

	final ImageAdmin admin = JavaImage.getInstance(null);
	
	@Override
	public ServerServiceDefinition bindService() {
		return ImageAdminGrpc.bindService(this);
	}

	@Override
    public void deleteUserImage(AdminDeleteImageArgs request, StreamObserver<Empty> responseObserver) {
    	super.toGrpcResult(responseObserver, 
    		admin.deleteUserImage(request.getUserId(), request.getImageId(), request.getToken()),
    		(__) -> Empty.newBuilder().build());
    }
}
