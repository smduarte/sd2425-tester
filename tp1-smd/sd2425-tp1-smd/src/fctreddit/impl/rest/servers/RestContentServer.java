package fctreddit.impl.rest.servers;

import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;

import fctreddit.api.java.Content;
import utils.Args;


public class RestContentServer extends AbstractRestServer {
	public static final int PORT = 4567;
	
	private static Logger Log = Logger.getLogger(RestContentServer.class.getName());

	RestContentServer() {
		super( Log, Content.NAME, PORT);
	}
	
	
	@Override
	void registerResources(ResourceConfig config) {
		config.register( RestContentResource.class ); 
//		config.register(new GenericExceptionMapper());
//		config.register(new CustomLoggingFilter());
	}
	
	public static void main(String[] args) {
		Args.use(args);
		new RestContentServer().start();
	}	
}