package fctreddit.impl.grpc.clients;

import fctreddit.api.java.Result;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.grpc.generated_java.ContentAdminGrpc;
import fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs;

public class GrpcContentAdminClient extends GrpcClient implements ContentAdmin {

	final ContentAdminGrpc.ContentAdminBlockingStub stub;

	public GrpcContentAdminClient(String serverURI) {
		super(serverURI);
		this.stub = ContentAdminGrpc.newBlockingStub( super.channel );	
	}

	@Override
	public Result<Void> handleDeletedUser(String userId, String token ) {
		return super.toJavaResult(() -> {
			stub.handleDeletedUser( HandleDeletedUserArgs.newBuilder()
					.setUserId(userId)
					.setToken(token).build());
		});
	}
}
