package fctreddit.impl.api.rest;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

public interface RestImageAdmin {

	static final String ADMIN  = "/admin";

	static final String TOKEN = "token";
	static final String USER_ID = "userId";
	static final String IMAGE_ID = "imageId";
	
	@DELETE
	@Path( ADMIN )
	void deleteUserImage(@QueryParam(USER_ID) String userId, @QueryParam(IMAGE_ID) String imageId, @QueryParam(TOKEN) String token);
	
}
