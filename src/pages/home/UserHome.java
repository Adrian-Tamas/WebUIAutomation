package pages.home;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;
import utils.WebdriverHelper;

public class UserHome extends BasePage{
	
    /** The logo. */
    @FindBy(how = How.ID, using = "horde-logo")
    private WebElement logo;
	
    /** Logout button. */
    @FindBy(how = How.CSS, using = "a[title='Log out']")
    private WebElement logOut;
   
	public UserHome(WebDriver driver) {
		super(driver);
		
		if (!isLoaded()) {
			Assert.fail("Incorect page is loaded.");
		}
	}
	
    public <T> T logout(Class<T> logoutPage) {
        logOut.click();
		WebdriverHelper.waitForLoad(driver);
        return PageFactory.initElements(driver, logoutPage);
    }
	
    @Override
    public By getPageLoadedIndicator() {
        return By.id("horde-logout");
    }

    public WebElement getLogo(){
    	return logo;
    }
}
