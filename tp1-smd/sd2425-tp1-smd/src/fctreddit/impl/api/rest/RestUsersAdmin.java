package fctreddit.impl.api.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

public interface RestUsersAdmin {
	static final String ADMIN  = "/admin";

	static final String PWD = "pwd";
	static final String TOKEN = "token";
	static final String USER_ID = "userId";
	
	@GET
	@Path(ADMIN)
	void checkUser(@QueryParam(USER_ID) String userId, @QueryParam(PWD) String password, @QueryParam(TOKEN) String token);
}
