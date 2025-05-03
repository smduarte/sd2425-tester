package fctreddit.impl.grpc.servers;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import fctreddit.impl.discovery.Discovery;
import fctreddit.impl.java.servers.AbstractServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import utils.IP;


public abstract class AbstractGrpcServer extends AbstractServer {
	private static final String SERVER_BASE_URI = "grpc://%s:%s%s";

	private static final String GRPC_CTX = "/grpc";

	protected final Server server;

	protected AbstractGrpcServer(Logger log, String service, int port) {
		super(log, service, String.format(SERVER_BASE_URI, IP.hostAddress(), port, GRPC_CTX));
		
		var builder = ServerBuilder.forPort(port);
		for( var s : stubs( super.serverURI ) )
			builder.addService( s );
		
		this.server = builder.build();
	}

	protected abstract List<AbstractGrpcStub> stubs( String uri );
	
	protected void start() throws IOException {
		
		Discovery.getInstance().announce(service, super.serverURI);
		
		Log.info(String.format("%s gRPC Server ready @ %s\n", service, serverURI));

		server.start();
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			System.err.println("*** shutting down gRPC server since JVM is shutting down");
			server.shutdownNow();
			System.err.println("*** server shut down");
		}));
	}
	
}
