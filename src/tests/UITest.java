package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import pages.login.LoginPage;
import utils.ConfigurationHelper;
import utils.DriverSetupHelper;
import utils.Logger;
import utils.TestHelper;
import utils.WebdriverHelper;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class UITest{

    protected static WebDriver driver;
    protected static Properties properties;
    protected static WebdriverHelper webdriverHelper;
    protected static TestHelper testHelper;
    protected static Logger logger;
       
    private static void initTests() {
        ConfigurationHelper configurationLoader = new ConfigurationHelper();
        properties = configurationLoader.loadDriverProps();
        if (properties.getProperty(ConfigurationHelper.TESTING_ENV).equalsIgnoreCase("prod")) {
        	properties.putAll(configurationLoader.loadEnvProp(ConfigurationHelper.prodEnvFilePath));
        } else if (properties.getProperty(ConfigurationHelper.TESTING_ENV).equalsIgnoreCase("test")) {
        	properties.putAll(configurationLoader.loadEnvProp(ConfigurationHelper.testEnvFilePath));
        } else if (properties.getProperty(ConfigurationHelper.TESTING_ENV).equalsIgnoreCase("qa")) {
        	properties.putAll(configurationLoader.loadEnvProp(ConfigurationHelper.qaEnvFilePath));
        }
    }

	private static void initHelpers() {
		webdriverHelper = new WebdriverHelper();
		testHelper = new TestHelper();
		logger = new Logger(properties.getProperty(ConfigurationHelper.LOG_LEVEL));
	}

	@BeforeClass
	public static void setUp() throws DriverSetupHelper.InvalidWebDriver {
		initTests();
		initHelpers();
		final String browser = properties.getProperty(ConfigurationHelper.VM_OPTION_BROWSER_KEY);
		driver = DriverSetupHelper.getDriver(browser);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		LoginPage.setLoginUrl(properties.getProperty(ConfigurationHelper.LOGIN_LINK));
	}

	@After
	public void closeAllTabs() {
		ArrayList<String> tabs;
		tabs = new ArrayList<String> (driver.getWindowHandles());

		while (tabs.size() > 1) {
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			driver.close();
			tabs = new ArrayList<String> (driver.getWindowHandles());
		}
		driver.switchTo().window(tabs.get(tabs.size() - 1));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
