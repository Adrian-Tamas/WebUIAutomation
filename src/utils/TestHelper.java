package utils;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class TestHelper {
	public static void setCookie(WebDriver driver, String cookieName, String cookieValue, String cookieDomain){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse("31/12/2035");
		} catch (ParseException e) {
			System.out.println("Wrong date format!");
		}
		
		Cookie cookie = new Cookie(cookieName, cookieValue, cookieDomain, "/", date);
		driver.manage().addCookie(cookie);
	}
	

	public static void openLinkInNewTabAndTestPageTitle(WebDriver driver, String linkElementText, String linkPageTitle) {
		WebElement link;
		int originalTabCount;
		ArrayList<String> tabs;

		tabs = new ArrayList<String>(driver.getWindowHandles());
		originalTabCount = tabs.size();

		try {
			link = driver.findElement(By.linkText(linkElementText));
			link.click();
		} catch (NoSuchElementException e){
			fail("There is no link with the text " + linkElementText + " on the page");
		}

		tabs = new ArrayList<String>(driver.getWindowHandles());

		Assert.assertNotEquals("Check tab count increased", tabs.size(), originalTabCount);
		driver.switchTo().window(tabs.get(tabs.size() - 1));
		WebdriverHelper.waitForLoad(driver);
		//WebdriverHelper.waitToLoad(5000);
		Assert.assertTrue("The new page title is correct", driver.getTitle().contains(linkPageTitle));

		// Close tab and return to the original one
		driver.close();
		driver.switchTo().window(tabs.get(tabs.size() - 2));
	}

	public static void openLinkInNewTabAndTestPageContent(WebDriver driver, String linkElementText, String linkPageTitle, String expectedLinkOnPage) {
		WebElement link;
		int originalTabCount;
		ArrayList<String> tabs;

		tabs = new ArrayList<String>(driver.getWindowHandles());
		originalTabCount = tabs.size();

		try {
			link = driver.findElement(By.linkText(linkElementText));
			link.click();
		} catch (NoSuchElementException e){
			fail("There is no link with the text " + linkElementText + " on the page");
		}

		tabs = new ArrayList<String>(driver.getWindowHandles());

		Assert.assertNotEquals("Check tab count increased", tabs.size(), originalTabCount);
		driver.switchTo().window(tabs.get(tabs.size() - 1));
		WebdriverHelper.waitForLoad(driver);
		//WebdriverHelper.waitToLoad(5000);
		Assert.assertTrue("The new page title is correct", driver.getTitle().contains(linkPageTitle));
		WebdriverHelper.verifyLinkPresent(driver, expectedLinkOnPage);

		// Close tab and return to the original one
		driver.close();
		driver.switchTo().window(tabs.get(tabs.size() - 2));
	}

	public static void openLinkAndTestPageTitle(WebDriver driver, String linkElementText, String linkPageTitle) {
		WebElement link;

		try {
			link = driver.findElement(By.linkText(linkElementText));
			link.click();
		} catch (NoSuchElementException e){
			fail("There is no link with the text " + linkElementText + " on the page");
		}
		WebdriverHelper.waitForLoad(driver);
		//WebdriverHelper.waitToLoad(5000);
		Assert.assertTrue("The new page title is correct", driver.getTitle().contains(linkPageTitle));
	}

}
