package fctreddit.impl.grpc.clients;

import fctreddit.api.java.Result;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.grpc.generated_java.UsersAdminGrpc;
import fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs;

public class GrpcUsersAdminClient extends GrpcClient implements UsersAdmin {

	final UsersAdminGrpc.UsersAdminBlockingStub stub;

	public GrpcUsersAdminClient(String serverURI) {
		super(serverURI);
		this.stub = UsersAdminGrpc.newBlockingStub( super.channel );	
	}

	@Override
	public Result<Void> checkUser(String userId, String password, String token) {
		return super.toJavaResult(() -> {
			stub.checkUser( CheckUserArgs.newBuilder()
					.setUserId(userId)
					.setPassword(password)
					.setToken(token).build());
		});
	}
	
}
