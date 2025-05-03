package fctreddit.impl.rest.clients;

import fctreddit.api.java.Result;
import fctreddit.api.rest.RestUsers;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.api.rest.RestUsersAdmin;


public class RestUsersAdminClient extends RestClient implements UsersAdmin {

	public RestUsersAdminClient( String serverURI ) {
		super( serverURI, RestUsers.PATH );
	}
		
	private Result<Void> _checkUser(String userId, String pwd, String token) {
		return super.toJavaResult(
				target
				.path(RestUsersAdmin.ADMIN)
				.queryParam(RestUsersAdmin.USER_ID, userId)
				.queryParam(RestUsersAdmin.TOKEN, token)
				.queryParam(RestUsersAdmin.PWD, pwd)
				.request()
				.get(), Void.class);
	}

	@Override
	public Result<Void> checkUser(String userId, String pwd, String token) {
		return super.reTry( () -> _checkUser(userId, pwd, token));
	}

}
