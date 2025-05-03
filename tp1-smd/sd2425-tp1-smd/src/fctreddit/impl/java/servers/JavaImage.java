package fctreddit.impl.java.servers;

import static fctreddit.api.java.Result.error;
import static fctreddit.api.java.Result.ok;
import static fctreddit.api.java.Result.ErrorCode.BAD_REQUEST;
import static fctreddit.api.java.Result.ErrorCode.CONFLICT;
import static fctreddit.api.java.Result.ErrorCode.NOT_FOUND;
import static fctreddit.impl.java.clients.Clients.UsersAdmin;
import static java.lang.String.format;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import fctreddit.api.java.Image;
import fctreddit.api.java.Result;
import fctreddit.impl.api.java.ImageAdmin;
import utils.Hash;
import utils.Hex;
import utils.IO;

public class JavaImage implements Image, ImageAdmin {
	private static Logger Log = Logger.getLogger(JavaImage.class.getName());
	
	private static final String IMAGES_ROOT_DIR = "/tmp/images/";

	private static final String TOKEN = "";

	private static JavaImage singleton;
	

	private String baseURI;
	private final AtomicLong counter = new AtomicLong(0L);
	
	private JavaImage( String baseURI ) {
		this.baseURI = baseURI;
	}
	
	public static synchronized JavaImage getInstance( String baseURI ) {
		if( singleton == null )
			singleton = new JavaImage(baseURI);
		
		return singleton;
	}
	
	@Override
	public Result<String> createImage(String userId, byte[] imageContents, String password) {
		Log.info(format("createImage : userId = %s, password = %s, sha256 = %s\n", userId, password, Hex.of(Hash.sha256(imageContents))));

		return UsersAdmin.get().checkUser(userId, password, TOKEN)
				.andThen( () -> {
					var imageId = "" + counter.incrementAndGet();
					
					var file = toFilePath(imageId);

					if (file.exists()) {
						if (Arrays.equals(Hash.sha256(imageContents), Hash.sha256(IO.read(file))))
							return ok(imageId);
						else
							return error(CONFLICT);
					}
					IO.write(file, imageContents);
					return ok(String.format("%s/%s/%s", baseURI, userId, imageId));
				});
	}

	@Override
	public Result<byte[]> getImage(String userId, String imageId) {
		Log.info(format("getImage : imageId = %s\n", imageId));

		var file = toFilePath(imageId);
		if (file == null)
			return error(BAD_REQUEST);

		if (file.exists())
			return ok(IO.read(file));
		else			
			return error(NOT_FOUND);
	}

	@Override
	public Result<Void> deleteImage(String userId, String imageId, String password) {
		Log.info(format("deleteImage : userId = %s, password = %s, imageId = %s\n", userId, password, imageId));

		return UsersAdmin.get().checkUser(userId, password, TOKEN)
				.andThen(() -> {
					if (IO.delete(toFilePath(imageId)))
						return ok();
					else
						return error(NOT_FOUND);			
				});
	}

	@Override
	public Result<Void> deleteUserImage(String userId, String imageId, String token) {
		Log.info(format("deleteUserImage : userId = %s, imageId = %s, token: %s\n", userId, imageId, token));

		//TODO Validate Token
		if (IO.delete(toFilePath(imageId)))
			return ok();
		else
			return error(NOT_FOUND);			
	}

	private File toFilePath(String imageId) {
		var res = new File(IMAGES_ROOT_DIR + imageId);
		res.getParentFile().mkdirs();
		return res;
	}
}
