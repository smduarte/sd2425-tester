package fctreddit.impl.api.rest;

import fctreddit.api.rest.RestContent;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.QueryParam;

public interface RestContentAdmin {

	static final String PATH  = "/admin" + RestContent.PATH;

	static final String TOKEN = "token";
	static final String USER_ID = "userId";
	
	@DELETE
	void handleDeletedUser(@QueryParam(USER_ID) String userId, @QueryParam(TOKEN) String token);
	
}
