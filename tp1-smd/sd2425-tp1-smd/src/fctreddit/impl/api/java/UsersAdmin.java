package fctreddit.impl.api.java;

import fctreddit.api.java.Result;

public interface UsersAdmin {

	Result<Void> checkUser(String userId, String password, String token);

	
}
