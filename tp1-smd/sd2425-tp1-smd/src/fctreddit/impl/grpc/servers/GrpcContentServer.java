package fctreddit.impl.grpc.servers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import fctreddit.api.java.Content;
import utils.Args;

public class GrpcContentServer extends AbstractGrpcServer {
public static final int PORT = 14567;
	
	private static Logger Log = Logger.getLogger(GrpcContentServer.class.getName());

	public GrpcContentServer() {
		super( Log, Content.NAME, PORT);
	}
	
	@Override
	protected List<AbstractGrpcStub> stubs(String uri) {
		return List.of( new GrpcContentServerStub(), new GrpcContentAdminServerStub());
	}
	
	public static void main(String[] args) {
		try {
			Args.use(args);
			new GrpcContentServer().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
}
