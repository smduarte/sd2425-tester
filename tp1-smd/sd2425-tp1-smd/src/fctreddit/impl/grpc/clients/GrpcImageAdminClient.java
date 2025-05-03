package fctreddit.impl.grpc.clients;

import java.io.File;
import java.net.URI;

import fctreddit.api.java.Result;
import fctreddit.impl.api.java.ImageAdmin;
import fctreddit.impl.grpc.generated_java.ImageAdminGrpc;
import fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs;

public class GrpcImageAdminClient extends GrpcClient implements ImageAdmin {

	final ImageAdminGrpc.ImageAdminBlockingStub stub;

	public GrpcImageAdminClient(String serverURI) {
		super(serverURI);
		this.stub = ImageAdminGrpc.newBlockingStub( super.channel );
	}

	@Override
	public Result<Void> deleteUserImage(String userId, String imageURL, String token) {
		if( imageURL == null) 
			return Result.ok();
		
		var imageId = new File(URI.create(imageURL).getPath()).getName();
	
		return super.toJavaResult(() -> {
			stub.deleteUserImage( AdminDeleteImageArgs.newBuilder()
					.setUserId(userId)
					.setImageId(imageId)
					.setToken(token).build());
		});
	}
}
