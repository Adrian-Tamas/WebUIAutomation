package utils;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverHelper {
	@SuppressWarnings("deprecation")
	public static void waitForText(WebDriver driver, By locator, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(locator, expectedText));
	}
	
	public static void waitForElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public static void waitForElementToDissapear(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
	}
	
	public static void waitForElementToBeClickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitToLoad(int nrOfMilliseconds) {
		try {
			Thread.sleep(nrOfMilliseconds);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void verifyTextPresentOnPage(WebDriver driver, String textToBeVerified) {
		try {
			WebElement textContainer = driver.findElement(By.xpath("//*[contains(.,'" + textToBeVerified + "')]"));
			Assert.assertTrue("Check the text " + textToBeVerified  + " is displayed on the page", textContainer.isDisplayed());
		} catch (NoSuchElementException e){
			fail("The text " + textToBeVerified + " is not present on the page");
		}
	}
	
	public static void clickOnText(WebDriver driver, String textToClick){
		if(isTextPresentOnPage(driver, textToClick)) {
			driver.findElement(By.xpath("//*[contains(.,'" + textToClick + "')]")).click();
		}
	}

	public static boolean isTextPresentOnPage(WebDriver driver, String textToBeVerified) {
		try {
			driver.findElement(By.xpath("//*[contains(.,'" + textToBeVerified + "')]")).isDisplayed();
			return true;
		} catch (NoSuchElementException e){
			return false;
		}
	}

	public static boolean isLinkPresentOnPage(WebDriver driver, String linkText) {
		try {
			driver.findElement(By.linkText(linkText));
			return true;
		} catch (NoSuchElementException e){
			return false;
		}
	}

	public static boolean isElementPresentOnPage(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	public static boolean isElementPresentInContainer(WebDriver driver, By container, By locator) {
		try {
			driver.findElement(container).findElement(locator);
			return true;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	public static void waitForLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	    };
	    WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(pageLoadCondition);
	}

	public static void verifyLinkPresent(WebDriver driver, String linkText) {
		try {
			WebElement link = driver.findElement(By.linkText(linkText));
			Assert.assertNotNull("Verifying link " + linkText + " is on the page",link);
		} catch (NoSuchElementException e){
			fail("There is no link with the text " + linkText + " on the page");
		}
	}
}
