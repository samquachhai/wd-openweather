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
import web.utils.JsonReader;
import web.utils.DateTime;

/**
 * The test to verify searching weather with valid city name
 * 
 */
public class SearchCity01 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="city")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-city.json", "city", 2);

	}
	
	/**
	 * The test to verify searching weather with invalid city name
	 * 
	 * @param cityId the city id
	 * @param cityName the city name
	 */
	@Test(dataProvider = "city",
			groups = { "smoke"})
	public void searchCity01(String cityId, String cityName) throws Exception {
		
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
			
			// 1. Searching for a city of your choice (E.g. Ha Noi)
			Logger.logInfo("1. Searching weather for '" + cityName + "'");
			
			searchActions.searchCity(cityName);
			
			// Verify list result contain (E.g. Ha Noi)
			Logger.assertExtentContainsWithScreenshot(
					homePage.getFirstItemOfSearchResults(), 
					cityName);
			
			// 2. Click on the link in result list
			Logger.logInfo("2. Click on the link in result list");
			
			homePage.clickFirstItemOfSearchResults();
			homePage.waitUntilTextDisplay(homePage.currentCityName, cityName);
			
			// Get current url in running browser
			String currentURL = homePage.getCurrentURL();
			
			// Verify current url update containing search city id
			Logger.assertExtentContains(currentURL, cityId);					
						
			// 3. Verify the current date (E.g. Jun 9), city name and the weather display correct. 
			// (Note: Only validate the temperature display regardless its number)
			Logger.logInfo("3. Verify the current date, city name and the weather display correct.");
			
			// Get current date displays on home page
			String currentDate = homePage.getCurrentDate();
			
			// Get current local date time
			final LocalDateTime localDateTime = LocalDateTime.now();
			
			// Format date time as "MMM dd, yyyy"
			String today = DateTime.format(localDateTime, "MMM dd, yyyy");
			
			// Get only Month and Day "MMM dd"
			String expectedDate = today.split(",")[0];			
		    
			// Verify the current date (E.g. Jun 9)
			Logger.assertExtentContains(
					currentDate, 
					expectedDate);
			
			// Get current city name displays on home page
			String currentCityName = homePage.getCurrentCityName();
			
			// Verify the current city name display correct
			Logger.assertExtentContains(
					currentCityName, 
					cityName);
			
			// Get current temperature displays on home page
			String currentTemperature = homePage.getCurrentTemparature();
			
			// Verify the current temperature display correct regardless its number
			Logger.assertExtentContains(
					currentTemperature, 
					"°");

			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		} finally {
			closeBrowser();
		}
	}
	
}
