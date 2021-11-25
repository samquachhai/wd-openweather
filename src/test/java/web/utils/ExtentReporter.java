/*
 * ExtentReporter 
 *
 */
package web.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * The ExtentReporter that using the ExtentReports report client for starting reporters and building reports 
 * 
 */
public class ExtentReporter {

	private static ExtentReports extentReport = null;
	private static IExtentTestReporter testReporter = null;
	private static ExtentSparkReporter sparkReporter = null;

	public static IExtentTestReporter currentReporter() {
		return testReporter;
	}
	
	public static ExtentSparkReporter currentSparkReporter() {
		return sparkReporter;
	}
	
	/**
	 * This method is intended to get current ExtentReporter
	 * 
	 */
	public static ExtentReports currentExtent() {
		return extentReport;
	}

	/**
	 * This method is intended to create an ExtentReports instance for building test report
	 * 
	 * @param filePath the test report file path  
	 * @param browser browser info to added to report
	 * @param env environment info to added to report
	 */
	public static void createReporter(String filePath, String browser, String env) {
		extentReport = createInstance(filePath);
		extentReport.setSystemInfo("Browser", browser);
		extentReport.setSystemInfo("Env", env);
	}

	/**
	 * This method is intended to write test information from the started reported to the HTML file
	 * 
	 */
	public static void closeReport() {
		extentReport.flush();
	}

	
	public static IExtentTestReporter getTestReporter() {
		testReporter = (IExtentTestReporter) new ExtentTestReporter(extentReport);
		return testReporter;
	}

	/**
	 * This method is intended to create an ExtentReports instance for building test report
	 * 
	 * @param filePath the test report file path  
	 */
	public static ExtentReports createInstance(String filePath) {
		
		String reportCSS = ".end-time:before {content: 'End Time: ';} "
				+ "\n .start-time:before {content: 'Start Time: '; display: inline-block;}"
				+ "\n .grey.lighten-1:before {content: 'Time taken: '; display: inline-block;}"
				+ "\n .end-time, .category-status > .fail {background: #5a50ef; display: inline-block;}";
		
		sparkReporter = new ExtentSparkReporter(filePath);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		 
		sparkReporter.config().setReportName("AUTOMATION REPORT");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setCss(reportCSS);
					
		return extentReport;
	}
}

