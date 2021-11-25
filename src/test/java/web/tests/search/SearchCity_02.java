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
public class SearchCity_02 extends TestBase {
	
	// Constructor
	public SearchCity_02() {}
	
	@DataProvider(name="invalid")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-city.json", "invalid", 2);

	}
	
	@Test(dataProvider = "invalid",
			groups = { "crossbrowser" })
	public void searchCity_02(String id, String city) throws Exception {
		
		HomePage homePage = new HomePage();
		SearchActions searchActions = new SearchActions();
		
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
			
			searchActions.searchCity(city);
			
			// Verify inline message "Not found. ..." displayed
			Logger.assertExtentContains(
					homePage.getSearchInlineMessage(), 
					"Not found.");
			
			// Verify search notification "No results for ..." displayed
			String expected = "No results for " + city;
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
