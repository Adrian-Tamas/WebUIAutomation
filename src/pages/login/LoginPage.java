package pages.login;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;
import utils.WebdriverHelper;

public class LoginPage extends BasePage{
	
	private static String loginUrl;
	
    /** The input user name. */
    @FindBy(how = How.ID, using = "horde_user")
    private WebElement inputUserName;

    /** The input user password. */
    @FindBy(how = How.ID, using = "horde_pass")
    private WebElement inputUserPassword;

    /** The button login. */
    @FindBy(how = How.ID, using = "login-button")
    private WebElement buttonLogin;

	public LoginPage(WebDriver driver) {
		super(driver);
		driver.get(loginUrl);
		if (!isLoaded()) {
			Assert.fail("Incorect page is loaded.");
		}
	}
	
    @Override
    public By getPageLoadedIndicator() {
        return By.id("login-button");
    }

    public <T> T login(String username, String password, Class<T> homePage) {
        inputUserName.sendKeys(username);
        inputUserPassword.sendKeys(password);
        buttonLogin.click();
		WebdriverHelper.waitForLoad(driver);
        return PageFactory.initElements(driver, homePage);
    }

	public static String getLoginUrl() {
		return loginUrl;
	}

	public static void setLoginUrl(String loginUrl) {
		LoginPage.loginUrl = loginUrl;
	}
}
