package frameworkDemo;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import pages.home.AdminHome;
import pages.home.UserHome;
import pages.login.LoginPage;
import daos.AdminDAO;
import daos.UserDAO;
import dtos.Admin;
import dtos.User;
import tests.UITest;
import utils.Logger;

public class BasicUserLoginTest extends UITest{

	@Test
	public void testUserLogin() {
		Logger.startStep("User login");
		User user = UserDAO.getUser();
		Logger.debug("User used: " + user.getUsername() + " and password used: " + user.getPassword());
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);;
		UserHome homePage = loginPage.login(user.getUsername(), user.getPassword(), UserHome.class);
		homePage.logout(LoginPage.class);
		Logger.endStep("User login");
	}
	
	@Test
	public void testAdminLogin() {
		Logger.startStep("User login");
		Admin admin = AdminDAO.getUser();
		Logger.debug("User used: " + admin.getUsername() + " and password used: " + admin.getPassword());
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		AdminHome homePage = loginPage.login(admin.getUsername(), admin.getPassword(), AdminHome.class);
		homePage.logout(LoginPage.class);
		Logger.endStep("User login");
	}
}
