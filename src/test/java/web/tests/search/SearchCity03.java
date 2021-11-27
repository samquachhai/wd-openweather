package web.tests.search;

import java.io.IOException;
import java.time.LocalDateTime;

import org.json.simple.parser.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.actions.SearchActions;
import web.base.TestBase;
import web.pages.HomePage;
import web.utils.Logger;
import web.utils.Constants;
import web.utils.DateTime;
import web.utils.JsonReader;

/**
 * The test to verify searching weather with valid city location id
 * 
 */
public class SearchCity03 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="city")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-city-id.json", "city", 2);

	}
	
	/**
	 * The test to verify searching weather with valid city location id
	 * 
	 * @param cityId the city id
	 * @param cityName the city name
	 */
	@Test(dataProvider = "city",
			groups = { "smoke", "crossbrowser" },
			description = "Search weather with valid city location id")
	public void searchCity03(final String cityId, final String cityName) {
		
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
			
			// 1. User navigate to the city location via url
			Logger.logInfo("1. User navigate to the city location via url");
			final String locationUrl = Constants.URL + "/city/" + cityId;
			
			homePage.navigateToUrl(locationUrl);
			
			// 2. Verify the current date (E.g. Jun 9), city name 
			// and the weather display correct. 
			// (Note: Only validate the temperature display regardless its number)
			Logger.logInfo("2. Verify the current date, city name and the weather display correct.");
			
			// Get current date displays on home page
			final String currentDate = homePage.getCurrentDate();
			
			// Get current local date time
			final LocalDateTime localDateTime = LocalDateTime.now();
			
			// Format date time as "MMM dd, yyyy"
			final String today = DateTime.format(localDateTime, "MMM dd, yyyy");
			
			// Get only Month and Day "MMM dd"
			final String expectedDate = today.split(",")[0];			
		    
			// Verify the current date (E.g. Jun 9)
			Logger.assertExtentContains(
					currentDate, 
					expectedDate);
			
			// Get current city name displays on home page
			final String currentCityName = homePage.getCurrentCityName();
			
			// Verify the current city name display correct
			Logger.assertExtentContains(
					currentCityName, 
					cityName);
			
			// Get current temperature displays on home page
			final String currentTemperature = homePage.getCurrentTemparature();
			
			// Verify the current temperature display correct regardless its number
			Logger.assertExtentContains(
					currentTemperature, 
					"°");

			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
		} 
	}
}
