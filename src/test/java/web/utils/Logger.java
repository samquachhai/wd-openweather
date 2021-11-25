/**
 * Logging and report
 */

package web.utils;

import java.util.UUID;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

/**
 * Logger helps log test info into during each test run 
 * in thread local for parallel testing
 * 
 */
public final class Logger {
	
	private Logger() {
		
	}
	
	/**
	 * This method is intended to log an event with Status.INFO 
	 * and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logInfo(final String details) {
		logReport(Status.INFO, details);
	}

	/**
	 * This method is intended to log an event with Status.INFO and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logLoadStart(final String details) {
		logReport(Status.INFO, "<b style=\"color: blue;\"> [Start Load]: " + details + "</b>");
	}

	/**
	 * This method is intended to log an event with Status.INFO and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logLoadEnd(final String details) {
		logReport(Status.INFO, "<b style=\"color: red;\"> [End Load]: " + details + "</b>");
	}

	/**
	 * This method is intended to log an event with Status.INFO, captured screenshot and details 
	 * in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logInfoWithScreenshot(final String details) {
		logReport(Status.INFO, details + logScreenshot());
	}

	/**
	 * This method is intended to log an event with Status.PASS 
	 * and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logPass(final String details) {
		logReport(Status.PASS, details);
	}

	/**
	 * This method is intended to log an event with Status.PASS, captured screenshot and details 
	 * in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logPassWithScreenshot(final String details) {
		logReport(Status.PASS, details + logScreenshot());
	}

	/**
	 * This method is intended to log an event with Status.FAIL, 
	 * captured screenshot and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logFail(final String details) {
		logReport(Status.FAIL, details + logScreenshot());
	}

	/**
	 * This method is intended to log an event with Status.SKIP and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void logSkip(final String details) {
		logReport(Status.SKIP, details);
	}

	/**
	 * This method is intended to log an event with Status.PASS and details in the current thread's ExtendReporter
	 * 
	 * @param details the details description 
	 */
	public static void log(final String details) {
		logReport(Status.PASS, details);
	}

	/**
	 * This method is intended to log an event with Status.FAIL, 
	 * captured screenshot and details in the current thread's ExtendReporter 
	 * and fails the test with given details message
	 * 
	 * @param details the details description 
	 */
	public static void logExceptionFail(final String details) {
		logReport(Status.FAIL, details + logScreenshot());
		
		// fails the test with given details message
		Assert.fail(details);
	}

	/**
	 * This method is intended to log an event with Status and details in the current thread's ExtendReporter
	 * 
	 * @param status the ExtentReport Status object
	 * @param details the details description 
	 */
	public static void logReport(final Status status, final String details) {
		ExtentTestReporter.testReporters.get().log(status, details);
	}

	/**
	 * This method is intended assert the condition is false. 
	 * Log an event with Status.INFO and details in the current thread's ExtendReporter
	 * 
	 * @param condition condition to evaluate
	 * 
	 */
	public static void logInfo(final Boolean condition, final String message) {			
		if (condition.equals(false)) {
			ExtentTestReporter.testReporters.get().log(Status.INFO, String.format("%s ", message));
		}
	}

	/**
	 * This method is intended to capture screenshot to log in the current thread's ExtendReporter
	 * 
	 */
	public static String logScreenshot() {
		try {
			String path = WebDrivers.captureScreenshot(
					UUID.randomUUID().toString(), "screenshots");
			

			String screenShotPath = "<br/><a href=\"" + path
					+ "\" target=\"_blank\"><img src=\"" + path
					+ "\" style=\"width: 160px;\"/></a>";
			
			return screenShotPath;
		} catch (Exception ex) {
			return "";
		}
		
	}

	/**
	 * This method is intended to assert two objects are equal. Log an event with Status 
	 * in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 */
	public static void assertExtentEquals(final Object actual, final Object expected) {
		// Compare actual string equals expected string			
		if (!(actual.toString().equals(expected.toString()))) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' does not equal '" + expected.toString().trim() + "'");
		} else {
			logPass("Actual '" + actual.toString().trim() 
					+ "' equals '" + expected.toString().trim() + "'");
		}
	}

	/**
	 * This method is intended to assert two objects are equal. 
	 * Log an event with Status, captured screenshot in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 */
	public static void assertExtentEqualsWithScreenshot(final Object actual, final Object expected) {
		// Compare actual string equals expected string	
		if (!(actual.toString().equals(expected.toString()))) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' does not equal '" + expected.toString().trim() + "'");
		} else {
			logPassWithScreenshot("Actual '" + actual.toString().trim() 
					+ "' equals '" + expected.toString().trim() + "'");
		}
	}

	/**
	 * This method is intended to assert two objects are equal. Log an event with Status and details 
	 * in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message 
	 */
	public static void assertExtentEquals(final Object actual, final Object expected, final String message) {
		// Compare actual string equals expected string	
		if (!(actual.toString().equals(expected.toString()))) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' does not  equals '" + expected.toString().trim() + "' " 
					+ message);
		} else {
			logPass("Actual '" + actual.toString().trim() 
					+ "' equals '" + expected.toString().trim());
		}
	}

	/**
	 * This method is intended to assert two objects are equal. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message 
	 */
	public static void assertExtentEqualsWithScreenshot(final Object actual, final Object expected, final String message) {
		// Compare actual string equals expected string	
		if (!(actual.toString().equals(expected.toString()))) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' does not  equals '" + expected.toString().trim() + "' " + message);
		} else {
			logPassWithScreenshot("Actual '" + actual.toString().trim() 
					+ "' equals '" + expected.toString().trim() + "'");
		}
	}
	
	/**
	 * This method is intended to assert two objects are not equal. 
	 * Log an event with Status, captured screenshot in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 */
	public static void assertExtentNotEquals(final Object actual, final Object expected) {
		// Compare actual string does not equal expected string	
		if (actual.toString().equals(expected.toString())) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' still equals '" + expected.toString().trim() + "' ");
		} else {
			logPass("Actual '" + actual.toString().trim() 
					+ "' does not equals '" + expected.toString().trim() + "' ");
		}
	}

	/**
	 * This method is intended to assert two objects are not equal. 
	 * Log an event with Status, captured screenshot 
	 * in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *   
	 */
	public static void assertExtentNotEqualsWithScreenshot(final Object actual, final Object expected) {
		// Compare actual string does not equal expected string	
		if (actual.toString().equals(expected.toString())) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' still equals '" + expected.toString().trim() + "' ");
		} else {
			logPassWithScreenshot("Actual '" + actual.toString() 
					+ "' does not equal '" + expected.toString() + "'");
		}
	}
	
	/**
	 * This method is intended to assert two objects are not equal. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message 
	 */
	public static void assertExtentNotEquals(final Object actual, final Object expected, final String message) {
		// Compare actual string does not equal expected string	
		if (actual.toString().equals(expected.toString())) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' still equals '" + expected.toString().trim() + "' " + message);
		} else {
			logPass("Actual '" + actual.toString().trim() 
					+ "' does not equal '" + expected.toString().trim() + "' ");
		}
	}

	/**
	 * This method is intended to assert two objects are not equal. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message 
	 */
	public static void assertExtentNotEqualsWithScreenshot(final Object actual, final Object expected, final String message) {
		// Compare actual string does not equal expected string	
		if (actual.toString().equals(expected.toString())) {
			logFail("Actual '" + actual.toString().trim() 
					+ "' still equals '" + expected.toString().trim() + "' "
					+ message);
		} else {
			logPassWithScreenshot("Actual '" + actual.toString() 
					+ "' does not equal '" + expected.toString() + "'");
		}
	}

	/**
	 * This method is intended to assert the actual object string 
	 * contains the expected object string. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message 
	 */
	public static void assertExtentContains(final String actual, final String expected) {
		// Compare actual string contains expected string	
		if (!actual.contains(expected)) {
			logFail("Actual '" + actual + "' does not contain '" + expected + "' ");
		} else {
			logPass("Actual '" + actual + "' contains '" + expected + "' ");
		}
	}
	
	/**
	 * This method is intended to assert the actual object string contains the expected object string. 
	 * Log an event with Status and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  
	 */
	public static void assertExtentContainsWithScreenshot(final String actual, final String expected) {
		// Compare actual string contains expected string
		if (!actual.contains(expected)) {
			logFail("Actual '" + actual + "' does not contain '" + expected + "' ");
		} else {
			logPassWithScreenshot("Actual '" + actual + "' contains '" + expected + "' ");
		}
	}
	
	/**
	 * This method is intended to assert the actual object string contains the expected object string. 
	 * Log an event with Status, captured screenshot and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  
	 */
	public static void assertExtentNotContains(final String actual, final String expected) {
		// Compare actual string does not contain expected string
		if (actual.contains(expected)) {
			logFail("Actual '" + actual + "' still contain '" + expected + "' ");
		} else {
			logPass("Actual '" + actual + "' does not contain '" + expected + "' ");
		}
	}

	/**
	 * This method is intended to assert the actual object string does not contain the expected object string. 
	 * Log an event with Status, captured screenshot and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  
	 */
	public static void assertExtentNotContainsWithScreenshot(final String actual, final String expected) {
		// Compare actual string does not contain expected string
		if (actual.contains(expected)) {
			logFail("Actual '" + actual + "' still contain '" + expected + "' ");
		} else {
			logPassWithScreenshot("Actual '" + actual + "' does not contain '" + expected + "' ");
		}
	}

	/**
	 * This method is intended to assert the actual object string contains the expected object string. 
	 * Log an event with Status and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message
	 */  
	public static void assertExtentContains(final String actual, final String expected, final String message) {
		// Compare actual string contains expected string
		if (!actual.contains(expected)) {
			logFail("Actual '" + actual	+ "' does not contain '" + expected + "' " + message);
		}
		else {
			logPass("Actual '" + actual	+ "' contains '" + expected + "' ");
		}
	}

	/**
	 * This method is intended to assert the actual object string contains the expected object string. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param actual the actual value
	 *  @param expected the expected value
	 *  @param message details message
	 */
	public static void assertExtentContainsWithScreenshot(final String actual, final String expected, final String message) {
		// Compare actual string contains expected string
		if (!actual.contains(expected)) {
			logFail("Actual '" + actual	+ "' does not contain '" + expected + "' " + message);
		}
		else {
			logPassWithScreenshot("Actual '" + actual	+ "' contains '" + expected + "' ");
		}
	}

	/**
	 * This method is intended to assert the condition is true. Log an event with Status and details 
	 * in the current thread's ExtendReporter
	 * 
	 *  @param condition condition to evaluate
	 *  @param message details message 
	 */
	public static void assertExtentTrue(final Boolean condition, final String message) {
		if (!condition.equals(true)) {
			logFail(message);
		} else {
			logPass(message);
		}
	}

	/**
	 * This method is intended to assert the condition is true. 
	 * Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param condition condition to evaluate
	 *  @param message details message 
	 */
	public static void assertExtentTrueWithScreenshot(final Boolean condition, final String message) {
		if (!condition.equals(true)) {
			logFail(message);
		} else {
			logPassWithScreenshot(message);
		}
	}

	/**
	 * This method is intended to assert the condition is false. Log an event with Status and details 
	 * in the current thread's ExtendReporter
	 * 
	 *  @param condition condition to evaluate
	 *  @param message details message 
	 */
	public static void assertExtentFalse(final Boolean condition, final String message) {
		if (condition.equals(true)) {
			logFail(message);
		} else {
			logPass(message);
		}
	}

	/**
	 * This method is intended to assert the condition is false. Log an event with Status, captured screenshot 
	 * and details in the current thread's ExtendReporter
	 * 
	 *  @param condition condition to evaluate
	 *  @param message details message 
	 */
	public static void assertExtentFalseWithScreenshot(final Boolean condition, final String message) throws Exception {
		if (condition.equals(true)) {
			logFail(message);
		} else {
			logPassWithScreenshot(message);
		}
	}

}
