package fctreddit.impl.rest.servers;

import java.util.List;

import fctreddit.api.User;
import fctreddit.api.java.Users;
import fctreddit.api.rest.RestUsers;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.api.rest.RestUsersAdmin;
import fctreddit.impl.java.servers.JavaUsers;
import jakarta.inject.Singleton;

@Singleton
public class RestUsersResource extends RestResource implements RestUsers, RestUsersAdmin {

	final Users impl = JavaUsers.getInstance();
	final UsersAdmin admin = JavaUsers.getInstance();
		
	@Override
	public String createUser(User user) {
		return super.resultOrThrow( impl.createUser( user));
	}

	@Override
	public User getUser(String name, String pwd) {
		return super.resultOrThrow( impl.getUser(name, pwd));
	}
	
	@Override
	public User updateUser(String name, String pwd, User user) {
		return super.resultOrThrow( impl.updateUser(name, pwd, user));
	}

	@Override
	public User deleteUser(String name, String pwd) {
		return super.resultOrThrow( impl.deleteUser(name, pwd));
	}

	@Override
	public List<User> searchUsers(String pattern) {
		return super.resultOrThrow( impl.searchUsers( pattern));
	}

	@Override
	public void checkUser(String userId, String password, String token) {
		super.resultOrThrow( admin.checkUser(userId, password, token));
	}
}
