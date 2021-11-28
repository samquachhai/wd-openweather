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
import web.utils.Constants;
import web.utils.JsonReader;

/**
 * The test to verify searching weather with invalid city name and country code
 * from Weather in your city page
 */
public class SearchCity08 extends TestBase {
	
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
	 * from Weather in your city page
	 * 
	 * @param cityId the city id
	 * @param cityName the city name
	 */
	@Test(dataProvider = "city",
			groups = { "smoke", "crossbrowser" },
			description = "Search weather with invalid city name and country code from Weather in your city page")
	public void searchCity08(final String cityId, final String cityName) {
		
		final HomePage homePage = new HomePage();
		final SearchResults searchResults = new SearchResults();
		final SearchActions searchActions = new SearchActions();
		
		try {
			// Pre-Condition: User navigate to the Weather in your city page"
			Logger.logInfo("Pre-Condition: Open https://openweathermap.org/find");
			final String locationUrl = Constants.URL + "/find";
			
			searchActions.searchPreparation();
			homePage.navigateToUrl(locationUrl);
			
			// Verify the Weather in your city page displays 
			Logger.assertExtentEquals(
					searchResults.getSearchBoxText(), 
					"London, UK", 
					"  Search box is not displayed");
			
			// 1. Enter valid city name  (E.g. Ha Noi)  on Search box 
			Logger.logInfo("1. Enter valid city name on Search box");
			searchResults.enterSearchCityName(cityName);
			
			// Verify Input city name is displayed on Search box
			Logger.assertExtentEquals(
					searchResults.getSearchBoxText(), 
					cityName);
			
			// 2. Click Search button or Press Enter to search
			Logger.logInfo("2. Click Search button or Press Enter to search");
			searchResults.clickSearchButton();
			
			// Verify search notification "Not found " displayed
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
