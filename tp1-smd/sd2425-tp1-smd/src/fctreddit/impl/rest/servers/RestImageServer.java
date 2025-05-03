package fctreddit.impl.rest.servers;

import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;

import fctreddit.api.java.Image;
import fctreddit.api.rest.RestImage;
import fctreddit.impl.java.servers.JavaImage;
import utils.Args;


public class RestImageServer extends AbstractRestServer {
	public static final int PORT = 5678;
	
	private static Logger Log = Logger.getLogger(RestImageServer.class.getName());

	RestImageServer(int port) {
		super( Log, Image.NAME, port);
	}
	
	
	@Override
	void registerResources(ResourceConfig config) {
		JavaImage.getInstance( super.serverURI + RestImage.PATH);
		
		config.register( RestImageResource.class ); 
//		config.register(new GenericExceptionMapper());
//		config.register(new CustomLoggingFilter());
	}
	
	public static void main(String[] args) {
		Args.use(args);
		new RestImageServer(Args.valueOf("-port", PORT)).start();
	}	
}