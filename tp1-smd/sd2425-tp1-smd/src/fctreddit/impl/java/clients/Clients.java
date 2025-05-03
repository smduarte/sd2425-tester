package fctreddit.impl.java.clients;

import fctreddit.api.java.Content;
import fctreddit.api.java.Image;
import fctreddit.api.java.Users;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.api.java.ImageAdmin;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.grpc.clients.GrpcContentAdminClient;
import fctreddit.impl.grpc.clients.GrpcImageAdminClient;
import fctreddit.impl.grpc.clients.GrpcUsersAdminClient;
import fctreddit.impl.rest.clients.RestContentAdminClient;
import fctreddit.impl.rest.clients.RestImageAdminClient;
import fctreddit.impl.rest.clients.RestUsersAdminClient;

public class Clients {
	public static final ClientFactory<UsersAdmin> UsersAdmin = new ClientFactory<>(Users.NAME, RestUsersAdminClient::new, GrpcUsersAdminClient::new);
	
	public static final ClientFactory<ImageAdmin> ImageAdmin = new ClientFactory<>(Image.NAME, RestImageAdminClient::new, GrpcImageAdminClient::new);

	public static final ClientFactory<ContentAdmin> ContentAdmin = new ClientFactory<>(Content.NAME, RestContentAdminClient::new, GrpcContentAdminClient::new);	
}
