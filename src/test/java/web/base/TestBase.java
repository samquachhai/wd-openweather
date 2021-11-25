/*
 * TestBase class
 *
 */

package web.base;

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import web.utils.ExtentReporter;
import web.utils.WebDrivers;
import web.utils.Constants;

/**
 * The TestBase 
 * 
 */
public class TestBase {
	private WebDrivers webDrivers;
	protected String browsers = "chrome";
	protected String mode = "headless";
	
	public static String URL = Constants.URL;
	public static String ENVIRONMENT = Constants.ENVIRONMENT;	
	public static String BUILD = Constants.BUILD;	
	String reportFile = "";
	
	static InetAddress inetAddress = null;
	public static String testsuiteName = "";
	protected String testCaseName = this.getClass().getName();
	protected String testClass = testCaseName;
	protected String testSuiteFileName = "";
	
	public static final String TEST_OUTPUT_DIR = System.getProperty("user.dir")	+ "/";

	/**
	 * This method is intended to setup information before suite run
	 * 
	 */	
	@Parameters({ "browsers", "mode", "testsuite"})
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(@Optional("firefox") String browsers,
							@Optional("headless") String mode,
							@Optional("DEBUG") String testsuite) {
		String directoryName = TEST_OUTPUT_DIR + "extent-report/";
		File directory = new File(directoryName);
	    if (! directory.exists()){
	    	// Create new directory if not exist
	        directory.mkdirs();
	      
	    }

	    // Build test report file from suite name, browser name and running mode
		reportFile = Paths.get(directoryName 
				+ testsuite 
				+ "_"
				+ browsers
				+ "_"
				+ mode
				+ "_"
				+ getHostName() 
				+ "_"
				+ getCurrentDate() 
				+ ".html").toString();
		
		// Create ExtendReport instance
		ExtentReporter.createReporter(reportFile, browsers, URL);
				
		testsuiteName = testsuite;
	}

	/**
	 * This method is intended to setup WebDriver , browser and running mode for each test run
	 * 
	 */
	@Parameters({"browsers","mode"})
	@BeforeClass(alwaysRun=true)
	public void classSetUp(@Optional("chrome") String browsers, 
						   @Optional("headed") String mode) {
		this.browsers = browsers;
		this.mode = mode;
		webDrivers = new WebDrivers();
		
	}
	
	/**
	 * This method is intended to start new test run
	 * 
	 */
	@BeforeMethod(alwaysRun=true)
	public synchronized void methodSetup(Method caller) throws MalformedURLException {
		// Create an ExtentTest instance and add to current thread-local
		ExtentReporter.getTestReporter().startTest(getTestName(caller));
		// Create a WebDriver instance and add to current thread-local
		webDrivers.startTest(this.browsers, this.mode);
	}

	/**
	 * This method is intended to attach a ExtentReporter reporter, 
	 * allowing it to access all started tests, nodes and logs
	 * 
	 */ 
	@AfterClass(alwaysRun=true)
	public synchronized void afterClass() {
		ExtentReporter.currentSparkReporter().config()
				.setJs("$(document).ready(function() {"
						+ "var list = $('#test-view-charts').find('.block.text-small');\n"
						+ "var left_list_0 = list[0];\n"
						+ "var left_list_1 = list[1];\n"
						+ "var list_span_0 = $(left_list_0).find('span');\n"
						+ "var span_value_1 = parseInt($(list_span_0[0]).text());\n"
						+ "var list_span_1 = $(left_list_1).find('span');\n"
						+ "var span_value_2 = parseInt($(list_span_1[0]).text());\n"
						+ "var span_value_3 = parseInt($(list_span_1[1]).text());\n"
						+ "var total = span_value_1 + span_value_2 +span_value_3;\n"
						+ "$($('.block.text-small')[0]).prepend('<span>'+total+' Total test(s) in "
						+ ENVIRONMENT 
						+ ", " 
						+ testsuiteName
						+ "</span></br>');\n"
						+ "$('.test-desc').prepend('<b><div>"
						+ BUILD 
						+ "</div></b>');\n});");
		ExtentReporter.currentExtent()
				.attachReporter(ExtentReporter.currentSparkReporter());
	}

	/**
	 * This method is intended to write test information from the started reported to the HTML file
	 * after suite run
	 */ 
	@AfterSuite(alwaysRun=true)
	protected void afterSuite() throws Exception {
		ExtentReporter.closeReport();
		
	}

	/**
	 * This method is intended to get the running machine host name
	 * 
	 * @return the host name
	 */ 
	public static String getHostName() {
		try {
			inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostName();
		} catch (UnknownHostException e) {
			return "LocalHost";
		}
	}

	/**
	 * This method is intended to get the running machine IP address
	 * 
	 * @return the IP address
	 */ 
	public static String getIPAddress() {
		try {
			inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			return "Local Host";
		}
	}

	/**
	 * This method is intended to build Test name from running Test Class name
	 * 
	 * @return the name of the test  
	 */
	private String getTestName(Method caller) {
		Test testAnnotation = caller.getAnnotation(Test.class);
		if (testAnnotation != null) {
			if (!testAnnotation.testName().isEmpty()) {
				return testAnnotation.testName();
			}
		}
		return testCaseName;
	}

	/**
	 * This method is intended to get current date format with "yyyyMMddHHmmss"
	 * 
	 * @return the current date 
	 */ 
	public String getCurrentDate() {
		 Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	     return formatter.format(date);
	}
	
	/**
	 * This method is intended to end test, quit current WebDriver and close all associated windows
	 * 
	 */ 
	public void closeBrowser() {
		try {
			webDrivers.endTest();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error occurred while closing browser."
					+ e.getStackTrace());
		}
	}
}
