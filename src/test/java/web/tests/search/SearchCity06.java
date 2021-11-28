package web.tests.search;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.actions.SearchActions;
import web.base.TestBase;
import web.pages.HomePage;
import web.pages.SearchResults;
import web.utils.Logger;
import web.utils.JsonReader;

/**
 * The test to verify searching weather with invalid city name and country code
 * from Search Bar in Home page
 */
public class SearchCity06 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="city")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-city-name-invalid.json", "city", 2);

	}
	
	/**
	 * The test to verify searching weather with invalid city name and country code
	 * from Search Bar in Home page
	 * 
	 * @param cityId the city id
	 * @param cityName the city name
	 */
	@Test(dataProvider = "city",
			groups = { "smoke", "crossbrowser" },
			description = "Search weather with invalid city name and country code from Search Bar in Home page")
	public void searchCity06(final String cityId, final String cityName) {
		
		final HomePage homePage = new HomePage();
		final SearchResults searchResults = new SearchResults();
		final SearchActions searchActions = new SearchActions();
		
		try {
			// Pre-Condition: Open https://openweathermap.org
			Logger.logInfo("Pre-Condition: Open https://openweathermap.org");
			
			searchActions.searchPreparation();
			
			// Verify Home page displays  
			Logger.assertExtentEquals(
					homePage.getSearchButtonText(), 
					"Search", 
					"  Home page is not displayed");
			
			// 1. Searching for an invalid city (E.g. Invalid City)
			Logger.logInfo("1. Searching for an invalid city (E.g. Invalid City)");
			
			searchActions.searchCityFromTopBar(cityName);
			
			// Verify the Weather in your city page displays 
			Logger.assertExtentEquals(
					searchResults.getSearchBoxText(), 
					cityName);
						
			// Verify search notification "Not found" displayed
			Logger.assertExtentContains(
					searchResults.getResultNotification(), 
					"Not found");

			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
		} 
	}
}
