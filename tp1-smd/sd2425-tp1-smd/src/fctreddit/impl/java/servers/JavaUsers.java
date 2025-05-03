package fctreddit.impl.java.servers;

import static fctreddit.api.java.Result.error;
import static fctreddit.api.java.Result.ok;
import static fctreddit.api.java.Result.ErrorCode.BAD_REQUEST;
import static fctreddit.api.java.Result.ErrorCode.FORBIDDEN;
import static fctreddit.impl.java.clients.Clients.ContentAdmin;
import static fctreddit.impl.java.clients.Clients.ImageAdmin;
import static java.lang.String.format;

import java.util.List;
import java.util.logging.Logger;

import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Users;
import fctreddit.impl.api.java.UsersAdmin;
import fctreddit.impl.persistence.DB;

public class JavaUsers implements Users, UsersAdmin {
	
	private static final String TOKEN = "";
	private static Logger Log = Logger.getLogger(JavaUsers.class.getName());

	private JavaUsers() {}
	
	@Override
	public Result<String> createUser(User user) {
		Log.info(() -> format("createUser : %s\n", user));

		if( badUserInfo( user ) )
			return error(BAD_REQUEST);
		
		return DB.insertOne(user).mapValue( u -> u.getUserId() );
	}

	@Override
	public Result<User> getUser(String userId, String pwd) {
		Log.info( () -> format("getUser : userId = %s, pwd = %s\n", userId, pwd));

		if (userId == null)
			return error(BAD_REQUEST);

		if (pwd == null)
			return error(FORBIDDEN);
		
		return fetchUser(userId, pwd);
	}

	@Override
	public Result<User> updateUser(String userId, String pwd, User other) {
		Log.info(() -> format("updateUser : userId = %s, pwd = %s, user: %s\n", userId, pwd, other));

		if (badUpdateUserInfo(userId, pwd, other))
			return error(BAD_REQUEST);

		return fetchUser(userId, pwd)
				.andThenWith( user -> DB.updateOne(user.updateFrom(other)));
	}

	@Override
	public Result<User> deleteUser(String userId, String pwd) {
		Log.info(() -> format("deleteUser : userId = %s, pwd = %s\n", userId, pwd));

		if (userId == null || pwd == null )
			return error(BAD_REQUEST);

		return fetchUser(userId, pwd )
				.andThen( () -> DB.deleteOne(userId, User.class))
				.async( (user) -> {
					ContentAdmin.get().handleDeletedUser(userId, TOKEN);
					var avatarUrl = user.getAvatarUrl();
					if( avatarUrl != null )
						ImageAdmin.get().deleteUserImage(userId, avatarUrl, TOKEN);
				});
	}

	@Override
	public Result<List<User>> searchUsers(String pattern) {
		Log.info( () -> format("searchUsers : patterns = %s\n", pattern));

		var sql = format("SELECT * FROM User u WHERE UPPER(u.userId) LIKE '%%%s%%'", pattern.toUpperCase());
		return DB.query(sql, User.class)
					.mapValue( list -> list.stream().map(User::copyWithoutPassword).toList());
	}

	@Override
	public Result<Void> checkUser(String userId, String password, String token) {
		Log.info( () -> format("checkUser : userId = %s, password = %s, token = %s\n", userId, password, token));

		return fetchUser( userId, password).mapToVoid();
	}

	
	private Result<User> fetchUser(String userId, String pwd) {
		return DB.getOne(userId, User.class)
			.mapResult( user -> user.getPassword().equals(pwd) ? ok(user) : error(FORBIDDEN));
	}
	
	private boolean badUserInfo( User user) {
		return (user.getUserId() == null || user.getPassword() == null || user.getFullName() == null || user.getEmail() == null || user.getPassword().isEmpty());
	}

	
	private boolean badUpdateUserInfo( String userId, String pwd, User info) {
		return (userId == null || pwd == null || info.getUserId() != null && ! userId.equals( info.getUserId()));
	}


	public static synchronized JavaUsers getInstance() {
		if( instance == null )
			instance = new JavaUsers();
		return instance;
	}
	
	static JavaUsers instance;
}
