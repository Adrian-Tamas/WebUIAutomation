package dtos;

public class Admin extends User{

	public Admin(String username, String password) {
		super(username, password);
		setUserType("admin");
	}
	
	public Admin(String username, String password, String firstName, String lastName, String email) {
		super(username,password, firstName,lastName, email, "admin");
	}

}
