package fctreddit.impl.api.java;

import fctreddit.api.java.Result;

public interface ImageAdmin {

	Result<Void> deleteUserImage(String userId, String imageId, String token);

}
