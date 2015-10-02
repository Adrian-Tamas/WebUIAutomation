package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverSetupHelper {

	public static WebDriver getDriver(String browser) throws InvalidWebDriver {

		if (browser.equalsIgnoreCase("chrome")) {
			return initChrome();
		} else if (browser.equalsIgnoreCase("firefox")) {
			return initFirefox();
		} else if (browser.equalsIgnoreCase("ie")) {
			return initIE();
		} else if (browser.equalsIgnoreCase("safari")) {
			return initSafari();
		} else {
			throw new InvalidWebDriver();
		}
	}

	private static WebDriver initChrome() {
		ConfigurationHelper configurationLoader = new ConfigurationHelper();
		Properties properties = configurationLoader.loadDriverProps();
		System.setProperty("webdriver.chrome.driver",
				properties.getProperty(ConfigurationHelper.CHROME_DRIVER_PATH_VALUE));

		return new ChromeDriver();
	}

	private static WebDriver initFirefox() {
		return new FirefoxDriver();
	}

	private static WebDriver initIE() {
		ConfigurationHelper configurationLoader = new ConfigurationHelper();
		Properties properties = configurationLoader.loadDriverProps();
		System.setProperty("webdriver.ie.driver",
				properties.getProperty(ConfigurationHelper.IE_DRIVER_PATH_VALUE));
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver initSafari() {
		return new SafariDriver();
	}

	@SuppressWarnings("serial")
	public static class InvalidWebDriver extends Exception {
	}
}
