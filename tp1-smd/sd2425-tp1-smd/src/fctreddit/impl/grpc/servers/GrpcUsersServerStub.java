package fctreddit.impl.grpc.servers;

import static fctreddit.impl.grpc.common.DataModelAdaptor.GrpcUser_to_User;
import static fctreddit.impl.grpc.common.DataModelAdaptor.User_to_GrpcUser;

import com.google.protobuf.Empty;

import fctreddit.api.java.Users;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.grpc.generated_java.UsersAdminGrpc;
import fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs;
import fctreddit.impl.grpc.generated_java.UsersGrpc;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.CreateUserArgs;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.CreateUserResult;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.DeleteUserArgs;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.DeleteUserResult;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.GetUserArgs;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.GetUserResult;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.GrpcUser;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.SearchUserArgs;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.UpdateUserArgs;
import fctreddit.impl.grpc.generated_java.UsersProtoBuf.UpdateUserResult;
import fctreddit.impl.java.servers.JavaUsers;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class GrpcUsersServerStub extends AbstractGrpcStub implements UsersGrpc.AsyncService {

	Users impl = JavaUsers.getInstance();

	@Override
	public final ServerServiceDefinition bindService() {
		return UsersGrpc.bindService(this);
	}

	@Override
	public void createUser(CreateUserArgs request, StreamObserver<CreateUserResult> responseObserver) {
		super.toGrpcResult(responseObserver,
			impl.createUser(GrpcUser_to_User(request.getUser())),
			(userId) -> CreateUserResult.newBuilder().setUserId(userId).build());
	}

	@Override
	public void getUser(GetUserArgs request, StreamObserver<GetUserResult> responseObserver) {
		super.toGrpcResult(responseObserver, 
				impl.getUser(request.getUserId(), request.getPassword()),
				(user) -> GetUserResult.newBuilder().setUser(User_to_GrpcUser(user)).build());
	}

	@Override
	public void updateUser(UpdateUserArgs request, StreamObserver<UpdateUserResult> responseObserver) {
		super.toGrpcResult(responseObserver,
				impl.updateUser(request.getUserId(), request.getPassword(), GrpcUser_to_User(request.getUser())),
				(user) -> UpdateUserResult.newBuilder().setUser( User_to_GrpcUser(user)).build());
	}

	@Override
	public void deleteUser(DeleteUserArgs request, StreamObserver<DeleteUserResult> responseObserver) {
		super.toGrpcResult(responseObserver, 
				impl.deleteUser(request.getUserId(), request.getPassword()),
				(user) -> DeleteUserResult.newBuilder().setUser(User_to_GrpcUser(user)).build());
	}

	@Override
	public void searchUsers(SearchUserArgs request, StreamObserver<GrpcUser> responseObserver) {
		super.toGrpcResultCollection(responseObserver,
				impl.searchUsers(request.getPattern()),
				(user) -> User_to_GrpcUser(user));
	}
}

class GrpcUsersAdminServerStub extends AbstractGrpcStub implements UsersAdminGrpc.AsyncService {

	UsersAdmin admin = JavaUsers.getInstance();

	@Override
	public final ServerServiceDefinition bindService() {
		return UsersAdminGrpc.bindService(this);
	}
	
	@Override
	public void checkUser(CheckUserArgs request, StreamObserver<Empty> responseObserver) {
		super.toGrpcResult(responseObserver, 
				admin.checkUser(request.getUserId(), request.getPassword(), request.getToken()),
				(__) -> Empty.newBuilder().build());
	}

}
