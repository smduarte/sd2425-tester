package fctreddit.impl.grpc.servers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import fctreddit.api.java.Image;
import fctreddit.impl.java.servers.JavaImage;
import utils.Args;

public class GrpcImageServer extends AbstractGrpcServer {
public static final int PORT = 15678;
	
	private static Logger Log = Logger.getLogger(GrpcImageServer.class.getName());

	public GrpcImageServer(int port) {
		super( Log, Image.NAME, port);
	}
	
	@Override
	protected List<AbstractGrpcStub> stubs(String uri) {
		JavaImage.getInstance(uri);
		return List.of( new GrpcImageServerStub(), new GrpcImageAdminServerStub());
	}
	
	public static void main(String[] args) {
		try {
			Args.use(args);
			new GrpcImageServer(Args.valueOf("-port", PORT)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
