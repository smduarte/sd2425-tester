package fctreddit.impl.api.java;

import fctreddit.api.java.Result;

public interface ContentAdmin {

	public Result<Void> handleDeletedUser(String userId, String token);

}
