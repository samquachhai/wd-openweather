package web.tests.search;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.actions.SearchActions;
import web.base.TestBase;
import web.pages.HomePage;
import web.utils.Logger;
import web.utils.JsonReader;

/**
 * The test to verify searching weather with invalid city name
 * 
 */
public class SearchCity02 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="invalid")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-city.json", "invalid", 2);

	}
	
	/**
	 * The test to verify searching weather with invalid city name
	 * 
	 * @param cityId the city id
	 * @param cityName the city name
	 */
	@Test(dataProvider = "invalid",
			groups = { "smoke", "crossbrowser" })
	public void searchCity02(String cityId, String cityName) throws Exception {
		
		final HomePage homePage = new HomePage();
		final SearchActions searchActions = new SearchActions();
		
		try {
			// Pre-Condition: Open https://openweather.org page
			Logger.logInfo("Pre-Condition: Open https://openweather.org page");
			
			searchActions.searchPreparation();
			
			// Verify Home page displays by checking Search button is displayed 
			Logger.assertExtentEquals(
					homePage.getSearchButtonText(), 
					"Search", 
					"  Search button is displayed");
			
			// 1. Searching for an invalid city (E.g. Invalid City)
			Logger.logInfo("1. Searching for an invalid city (E.g. Invalid City)");
			
			searchActions.searchCity(cityName);
			
			// Verify inline message "Not found. ..." displayed
			Logger.assertExtentContains(
					homePage.getSearchInlineMessage(), 
					"Not found.");
			
			// Verify search notification "No results for ..." displayed
			String expected = "No results for " + cityName;
			Logger.assertExtentEquals(
					homePage.getSearchNotification(), 
					expected);
			

			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
		} finally {
			closeBrowser();
		}
	}
}
