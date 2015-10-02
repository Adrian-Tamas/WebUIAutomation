package daos;

import dtos.Admin;

public class AdminDAO {
	
	public static Admin getUser() {
		String username = "demo";
		String password = "demo";
		return new Admin(username, password);
	}
}
