package fctreddit.impl.rest.clients;

import java.io.File;
import java.net.URI;

import fctreddit.api.java.Result;
import fctreddit.api.rest.RestImage;
import fctreddit.impl.api.java.ImageAdmin;
import fctreddit.impl.api.rest.RestImageAdmin;

public class RestImageAdminClient extends RestClient implements ImageAdmin {

	public RestImageAdminClient(String serverURI) {
		super(serverURI, RestImage.PATH);
	}

	public Result<Void> _deleteUserImage(String userId, String imageId, String token) {
		return super.toJavaResult( 
				target.path(RestImageAdmin.ADMIN)
				.queryParam(RestImageAdmin.TOKEN, token)
				.queryParam(RestImageAdmin.USER_ID, userId)
				.queryParam(RestImageAdmin.IMAGE_ID, imageId)
				.request()
				.delete());
	}
	
	@Override
	public Result<Void> deleteUserImage(String userId, String imageURI, String token) {
		if( imageURI == null)
			return Result.ok();
		
		var imageId = new File(URI.create( imageURI ).getPath()).getName();		
		return super.reTry(() -> _deleteUserImage(userId, imageId, token));
	}	
}
