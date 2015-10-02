package daos;

import dtos.User;

public class UserDAO {

	public static User getUser() {
		String username = "guest";
		String password = "guest";
		return new User(username, password);
	}
}
