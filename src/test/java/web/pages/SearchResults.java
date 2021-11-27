package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Constants;
import web.utils.Logger;

/*
 * SearchResults class
 *
 */
public class SearchResults extends PageObject {

	// Page elements locators
	final By resultTopNavigation = By.xpath("//*[@id='nav-website']");
	final By resultFirstLevelNav = By.xpath("//*[@id='first-level-nav']");
	final By resultMobileMenu = By.xpath("//*[@id='mobile-menu']");
	final By resultSearchBox = By.xpath("//input[@id='search_str']");
	final By resultSearchButton = By.xpath("//*[@id='searchform']/button");
	final By resultForecastList = By.xpath("//div[@id='forecast_list_ul']");
	final By resultForecast = By.xpath("//div[@id='forecast_list_ul']/descendant::p[1]");
	final By resultCity = By.xpath("/div[@id='forecast_list_ul']/descendant::a[1]");
	final By resultTemperature = By.xpath("//div[@id='forecast_list_ul']//span");
	final By resultGeoCoords = By.xpath("//div[@id='forecast_list_ul']/descendant::a[2]");	
	final By resultNotification = By.xpath("//div[@id='forecast_list_ul']/div");

	// Constructor
	public SearchResults() {
		super();
	}
    
    /**
	 * This method is intended to enter city in the Search box  
	 *  on Weather in your city page
	 *
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void searchCity(final String city) {
        // Searching for a city of your choice (E.g. Ha Noi)
    	enterSearchCityName(city);
    	clickSearchButton();
    }

    /**
	 * This method is intended to enter city in the Search box  
	 *  on Weather in your city page
	 *
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void enterSearchCityName(final String city) {
    	Logger.logInfo("Enter '" + city + "' in the Search city field on 'Weather in your city' page");
    	setText(resultSearchBox, city); 
    }
    
    /**
   	 * This method is intended to click Search button
     *   on Weather in your city page 
   	 */
    public void clickSearchButton() {  	
		Logger.logInfo("Click Search button on Home page");
		click(resultSearchButton);      
		this.waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to click City of search results 
     *   on Weather in your city page
   	 */
    public void clickResultCity() {
    	Logger.logInfo("Click city name on search results");
    	click(resultCity);
    	this.waitUntilJsLoad();
    }
    
    /**
   	 * This method is intended to get input text on Search Box 
   	 *   on Weather in your city page
   	 */
    public String getSearchBoxText() {
    	return getText(resultSearchBox);
    }
    
    /**
   	 * This method is intended to get City of search results 
   	 *   on Weather in your city page
   	 */
    public String getResultCity() {
    	return getText(resultCity);
    } 

    /**
   	 * This method is intended to get Temperature of search results 
   	 *   on Weather in your city page
   	 */
    public String getResultTemperature() {
        return getText(resultTemperature);
    }

    /**
   	 * This method is intended to get resultGeoCoords of search results
   	 *   on Weather in your city page
     * 
   	 */
    public String getResultGeoCoords() {
    	return getText(resultGeoCoords);
    }

    /**
   	 * This method is intended to get notice message 
   	 *   on Weather in your city page
     * 
   	 */
    public String getResultNotification() {
    	return getText(resultNotification);
    }

}

