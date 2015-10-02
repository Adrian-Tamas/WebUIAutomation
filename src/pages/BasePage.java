package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;

import utils.WebdriverHelper;

public abstract class BasePage {
	public static WebDriver driver;
	
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		//PageFactory.initElements(BasePage.driver, this);
	}

	public boolean isLoaded(){
		return WebdriverHelper.isElementPresentOnPage((RemoteWebDriver)driver, getPageLoadedIndicator());	
	}
	
	public By getPageLoadedIndicator(){
		return null;
	}
}
