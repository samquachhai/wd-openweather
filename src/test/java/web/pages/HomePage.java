/*
 * HomePage class
 *
 */

package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Constants;
import web.utils.Logger;

public class HomePage extends PageObject {

	// Page elements locators
	public By searchCityField = By.xpath("//input[@placeholder='Search city' and @type='text']");
	public By searchButton = By.xpath("//button[text() = 'Search']");
	public By searchResults = By.xpath("//div[@class='search-container']/ul[contains(@class,'search-dropdown-menu')]");
	public By firstItemOfSearchResults = By.xpath("//div[@class='search-container']/ul[contains(@class,'search-dropdown-menu')]/li");
	public By currentDate = By.xpath("//div[contains(@class,'current-container')]//span[@class='orange-text']");
	public By currentCityName = By.xpath("//div[contains(@class,'current-container')]/descendant::span[@class='orange-text']/following-sibling::h2");
	public By currentTemperature = By.cssSelector("div.current-temp > span");
	public By searchInlineMessage = By.xpath("//div[@class='search-block']/div[contains(@class,'sub not-found')]");	
	final By searchNotification = By.xpath("//div[contains(@class,'current-container')]/div[contains(@class,'widget-notification')]/span");

	// Constructor
	public HomePage() {
		super();
	}
	
   /**
   	 * This method is intended to navigate to Home page
     * 
   	 */
    public void navigateToHomePage() {
        navigateToURL(Constants.URL);
        waitUntilPageLoad();
        
    }
    
    /**
	 * This method is intended to search weather for a city of your choice
	 *
	 * @param cityName city to search for (E.g. Ha Noi)
	 * 
	 */
    public void searchCity(String cityName) {
        // Searching for a city of your choice (E.g. Ha Noi)
    	enterSearchCityName(cityName);
    	clickSearchButton();
    	this.waitUntilJsLoad();
    }

    /**
	 * This method is intended to enter city name in the Search city field on Home page
	 *
	 * @param cityName city to search for (E.g. Ha Noi)
	 * 
	 */
    public void enterSearchCityName(String cityName) {
    	Logger.logInfo("Enter '" + cityName + "' in the Search city field on Home page");
    	setText(searchCityField, cityName); 
    }
    
    /**
   	 * This method is intended to click Search button on Home page
     *  
   	 */
    public void clickSearchButton() {  	
		Logger.logInfo("Click Search button on Home page");
		click(searchButton);      
    	
    }
    
    /**
   	 * This method is intended to click first item of search results after searching a city on Home page
     * 
   	 */
    public void clickFirstItemOfSearchResults() {
 
    	Logger.logInfo("Click first item of search results");
    	click(firstItemOfSearchResults);
    	
    }
    
    /**
   	 * This method is intended to click first item of search results after searching a city on Home page
     * 
   	 */
    public String getSearchButtonText() {
    	return getText(searchButton);
    }
    
    /**
   	 * This method is intended to click first item of search results after searching a city on Home page
     * 
   	 */
    public String getFirstItemOfSearchResults() {
    	return getText(firstItemOfSearchResults);
    } 

    /**
   	 * This method is intended to get current city name after searching valid city on Home page
     * 
   	 */
    public String getCurrentCityName() {
        return getText(currentCityName);
    }

    /**
   	 * This method is intended to get current temperature after searching valid city on Home page
     * 
   	 */
    public String getCurrentTemparature() {
    	return getText(currentTemperature);
    }

    /**
   	 * This method is intended to get current date after searching valid city on Home page
     * 
   	 */
    public String getCurrentDate() {
    	return getText(currentDate);
    }

    /**
   	 * This method is intended to get inline message after searching invalid city on Home page
     * 
   	 */
    public String getSearchInlineMessage() {
    	return getText(searchInlineMessage);
    }

    /**
   	 * This method is intended to get notification message after searching invalid city on Home page
     * 
   	 */
    public String getSearchNotification() {
    	return getText(searchNotification);
    }
}

