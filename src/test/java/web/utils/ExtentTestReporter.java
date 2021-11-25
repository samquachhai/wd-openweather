/*
 * ExtentTestReporter 
 *
 */
package web.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentTestReporter defines a test, writes test information to report.
 */
public class ExtentTestReporter implements IExtentTestReporter {

	// Create a thread local variable storing ExtentTest instance
	public static ThreadLocal<ExtentTest> testReporters = new ThreadLocal<>();
	private ExtentReports reporter;

	public ExtentTestReporter(ExtentReports reporter) {
		this.reporter = reporter;
	}

	public void startTest(String testName) {
		testReporters.set(reporter.createTest(testName));
	}

	public void startTest(String testName, String testDescription) {
		testReporters.set(reporter.createTest(testName, testDescription));
	}

	public void endTest() {
		reporter.flush();
	}

}
