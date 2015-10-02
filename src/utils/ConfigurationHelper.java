package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHelper {
	// General values for the webdrivers
	public static String CHROME_DRIVER_PATH_KEY = "webdriver.chrome.path.key";
	public static String CHROME_DRIVER_PATH_VALUE = "webdriver.chrome.path.value";
	public static String IE_DRIVER_PATH_KEY = "webdriver.ie.path.key";
	public static String IE_DRIVER_PATH_VALUE = "webdriver.ie.path.value";
	public static String VM_OPTION_BROWSER_KEY = "vm.option.browser.key";
	public static String TESTING_ENV = "enviorment";
	public static String LOG_LEVEL = "log.level";

	public static String LOGIN_LINK = "login.link"; 
	
	private static final String configFilePath = "/config/configuration.properties";
	public static final String testEnvFilePath = "/config/testEnv.properties";
	public static final String prodEnvFilePath = "/config/prodEnv.properties";
	public static final String qaEnvFilePath = "/config/qaEnv.properties";
	
	public Properties loadDriverProps() {
		Properties driverProp = new Properties();
		InputStream in = this.getClass().getResourceAsStream(configFilePath);

		try {
			driverProp.load(in);
		} catch (IOException e) {
			System.out.println("Error loading the configuration file");
		}
		return driverProp;
	}
	
	public Properties loadEnvProp(String enviormentFile) {
		Properties envProp = new Properties();
		InputStream in = this.getClass().getResourceAsStream(enviormentFile);

		try {
			envProp.load(in);
		} catch (IOException e) {
			System.out.println("Error loading the configuration file");
		}
		return envProp;
	}
}
