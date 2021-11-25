/*
 * ExtentTestReporter interface
 *
 */
package web.utils;

/**
 * This is an interface for ExtentTestReporter.
 */
public interface IExtentTestReporter {
	
	/**
	 * This method is intended to create an ExtentTest and add
	 * current thread's ExtentTest to this thread-local
	 * 
	 * @param testName the test name
	 */
	public void startTest(String testName);
	
	/**
	 * This method is intended to create an ExtentTest and add
	 * current current thread's ExtentTest to this thread-local
	 * 
	 * @param testName the test name
	 * @param testDescription the test description
	 */
	public void startTest(String testName, String testDescription);
	
	/**
	 * This method is intended to write test information from the started reporters to their output view 
	 * 
	 */
	public void endTest();
}
