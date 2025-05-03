package fctreddit.impl.rest.servers;

import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;

import fctreddit.api.java.Users;
import fctreddit.impl.rest.servers.utils.CustomLoggingFilter;
import fctreddit.impl.rest.servers.utils.GenericExceptionMapper;
import utils.Args;


public class RestUsersServer extends AbstractRestServer {
	public static final int PORT = 3456;
	
	private static Logger Log = Logger.getLogger(RestUsersServer.class.getName());

	RestUsersServer() {
		super( Log, Users.NAME, PORT);
	}
	
	
	@Override
	void registerResources(ResourceConfig config) {
		config.register( RestUsersResource.class ); 
		config.register(GenericExceptionMapper.class);
		config.register(CustomLoggingFilter.class);
	}
	
	public static void main(String[] args) {
		Args.use(args);
		new RestUsersServer().start();
	}	
}