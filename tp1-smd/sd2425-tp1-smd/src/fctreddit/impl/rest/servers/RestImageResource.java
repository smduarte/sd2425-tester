package fctreddit.impl.rest.servers;

import fctreddit.api.java.Image;
import fctreddit.api.rest.RestImage;
import fctreddit.impl.api.java.ImageAdmin;
import fctreddit.impl.api.rest.RestImageAdmin;
import fctreddit.impl.java.servers.JavaImage;
import jakarta.inject.Singleton;

@Singleton
public class RestImageResource extends RestResource implements RestImage, RestImageAdmin {

	final Image impl = JavaImage.getInstance(null);
	final ImageAdmin admin = JavaImage.getInstance(null);
	
	@Override
	public String createImage(String userId, byte[] imageContents, String password) {
		return super.resultOrThrow( impl.createImage(userId, imageContents, password));
	}

	@Override
	public byte[] getImage(String userId, String imageId) {
		return super.resultOrThrow(impl.getImage(userId, imageId));
	}

	@Override
	public void deleteImage(String userId, String imageId, String password) {
		super.resultOrThrow( impl.deleteImage(userId, imageId, password));
	}
	
	@Override
	public void deleteUserImage(String userId, String imageId, String token) {
		super.resultOrThrow( admin.deleteUserImage(userId, imageId, token));
	}
}
