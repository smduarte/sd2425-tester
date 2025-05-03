package fctreddit.impl.rest.clients;

import fctreddit.api.java.Result;
import fctreddit.api.rest.RestContent;
import fctreddit.impl.api.java.ContentAdmin;
import fctreddit.impl.api.rest.RestContentAdmin;

public class RestContentAdminClient extends RestClient implements ContentAdmin {

	public RestContentAdminClient(String serverURI) {
		super(serverURI, RestContent.PATH);
	}

	public Result<Void> _handleDeletedUser(String userId, String token) {
		return super.toJavaResult(
				target
				.queryParam(RestContentAdmin.USER_ID, userId)
				.queryParam(RestContentAdmin.TOKEN, token)
				.request()
				.delete());
	}
	
	@Override
	public Result<Void> handleDeletedUser(String userId, String token) {
		return super.reTry( () -> _handleDeletedUser(userId, token));
	}
}
