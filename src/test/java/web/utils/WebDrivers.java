/*
 * WebDrivers
 *
 */
package web.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * WebDrivers helps store the initiated driver during each test run in thread local for parallel testing
 * 
 */
public class WebDrivers {
	// Create a thread local variable storing WebDriver instance
	public static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<>();


	public WebDrivers() {

	}
	
	/**
	 * This method is intended to get the current thread's WebDriver of this thread-local
	 * 
	 * @return the current thread's WebDriver of this thread-local
	 */
	public static WebDriver getCurrentWebDriver() {
		return webDrivers.get();
	}


	/**
	 * This method is intended to start new test and add initiated WebDriver instance to current thread-local
	 * 
	 * @param browser running browser (chrome / firefox)
	 * @param mode running mode (headed / headless)
	 */
	public void startTest(String browser, String mode) throws MalformedURLException {
		WebDriver driver;
				
		if (browser.equalsIgnoreCase("chrome")) {
			// Init Chrome driver with running mode (headed/headless)
			driver = initChromeDriver(mode);
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Init Firefox driver with running mode (headed/headless)
			driver = initFirefoxDriver(mode);
			
		} else {
			// Init default Chrome driver with running mode (headed/headless)
			driver = initChromeDriver(mode);
		}
		
		// Add web driver to current thread-local
		webDrivers.set(driver);
	}

	/**
	 * This method is intended to end test, quit current thread-local driver and closing every associated windows
	 * 
	 */
	public void endTest() {
		if (getWebDriver() != null) {
			
			try {
				Thread.sleep(1000);
				getWebDriver().close();
				getWebDriver().quit();
			} catch (Exception ignore) {

			}
			
		}
	}

	/**
	 * This method is intended to quit current thread-local driver and closing every associated windows
	 * 
	 */
	public void closeDrivers() throws InterruptedException {
		if (getWebDriver() != null) {
			Thread.sleep(1000);
			getWebDriver().close();
			getWebDriver().quit();
		}
	}
	
	/**
	 * This method is intended to capture the screenshot and store it in specified location
	 * 
	 * @param screenshotFileName the local captured screenshot file name
	 * @param screenshotFilePath the local captured screenshot file path
	 * 
	 * @return the local absolute path of captured screenshot file
	 */
	public static String captureScreenshot(String screenshotFileName, String screenshotFilePath) {
		String localScreenshotFilePath = "";
		try {
			// Taking the screen using TakesScreenshot Class
			File captureScreenshotFile = ((TakesScreenshot) getCurrentWebDriver()).getScreenshotAs(OutputType.FILE);

			// Storing the image in the local system.
			File localScreenshotFile = new File(
					System.getProperty("user.dir") + File.separator + screenshotFilePath + File.separator 
					+ screenshotFileName + ".png");
			
			FileUtils.copyFile(captureScreenshotFile, localScreenshotFile);
			
			// Get the absolute path of local screenshot file
			localScreenshotFilePath = localScreenshotFile.getAbsolutePath();
			
		} catch (Exception e) {
			Logger.logExceptionFail("An error occurred when capturing screen shot: " + e.getMessage());
		}
		
		return localScreenshotFilePath;
	}

	/**
	 * This method is intended to get the current thread's WebDriver of this thread-local
	 * 
	 * @return the current thread's WebDriver of this thread-local
	 * 
	 */
	public WebDriver getWebDriver() {
		return webDrivers.get();
	}
	
	/**
	 * This method is intended to get the current URL that the browser is looking at
	 * 
	 * @return the URL of the page currently loaded in the browser
	 * 
	 */
	public static String getCurrentUrl() {
		return getCurrentWebDriver().getCurrentUrl();
	}

	private WebDriver initChromeDriver(String mode) throws MalformedURLException {
		WebDriver driver;
		// allow download file to local storage
		Map<String, Object> prefs = new HashMap<String, Object>();
/*	    
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_setting_values.geolocation", 1);
		prefs.put("profile.default_content_setting_values.notification", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
*/		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		
		// options.addArguments
		options.addArguments("test-type");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("test-type=browser");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		
		// Run incognito mode
		if (mode.equalsIgnoreCase("incognito")) {
			options.addArguments("incognito");
		}
		
		// Run headless mode
		if (mode.equalsIgnoreCase("headless")) {
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
		}
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				UnexpectedAlertBehaviour.ACCEPT);
		options.merge(cap);
		
				
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		return driver;
	}

	private WebDriver initFirefoxDriver(String mode) throws MalformedURLException {
		WebDriver driver;
		
		
		// Create prefs for FirefoxProfile
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.startup.page", 0);
		options.addPreference("browser.startup.homepage", "about:blank");
		options.addPreference("browser.startup.homepage_override.mstone",
				"ignore");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		options.merge(cap);

		// Run incognito mode
		if (mode.equalsIgnoreCase("incognito")) {
			options.addArguments("incognito");
		}
		
		// Run headless mode
		if (mode.equalsIgnoreCase("headless")) {
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
		}
		driver = new FirefoxDriver(options);
		
		driver.manage().window().maximize();

		return driver;
	}
	
   
}
