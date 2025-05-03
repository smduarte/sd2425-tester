package fctreddit.impl.grpc.servers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import fctreddit.api.java.Users;
import utils.Args;

public class GrpcUsersServer extends AbstractGrpcServer {
public static final int PORT = 13456;
	
	private static Logger Log = Logger.getLogger(GrpcUsersServer.class.getName());

	public GrpcUsersServer() {
		super( Log, Users.NAME, PORT);
	}
	
	@Override
	protected List<AbstractGrpcStub> stubs(String uri) {
		return List.of( new GrpcUsersServerStub(), new GrpcUsersAdminServerStub());
	}
	
	public static void main(String[] args) {
		try {
			Args.use(args);
			new GrpcUsersServer().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
