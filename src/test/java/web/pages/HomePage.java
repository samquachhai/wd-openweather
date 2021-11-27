package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import web.base.PageObject;
import web.utils.Constants;
import web.utils.Logger;

/*
 * HomePage class
 *
 */
public class HomePage extends PageObject {

	// Page elements locators
	final By searchCityField = By.xpath("//input[@placeholder='Search city' and @type='text']");
	final By searchButton = By.xpath("//*[@id='weather-widget']//div[@class='search']//button");
	final By resultsList = By.xpath("//div[@class='search-container']/ul[contains(@class,'search-dropdown-menu')]");
	final By resultsCityName = By.xpath("//div[@class='search-container']/ul[contains(@class,'search-dropdown-menu')]/li");
	final By currentDate = By.xpath("//div[contains(@class,'current-container')]//span[@class='orange-text']");
	final By currentCityName = By.xpath("//div[contains(@class,'current-container')]/descendant::span[@class='orange-text']/following-sibling::h2");
	final By currentTemperature = By.xpath("//div[@class='current-temp']/span");
	final By searchInlineMessage = By.xpath("//div[@class='search-block']/div[contains(@class,'sub not-found')]");	
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
        navigateToUrl(Constants.URL);
        waitUntilPageLoad();
        
    }
    
    /**
	 * This method is intended to search weather for a city of your choice
	 *
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void searchCity(final String city) {
        // Searching for a city of your choice (E.g. Ha Noi)
    	enterSearchCityName(city);
    	//clickSearchButton();
    	this.getElement(searchCityField).sendKeys(Keys.ENTER);
    	waitUntilPageLoad();
    }

    /**
	 * This method is intended to enter city name 
	 * in the Search city field on Home page
	 *
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void enterSearchCityName(final String city) {
    	Logger.logInfo("Enter '" + city + "' in the Search city field on Home page");
    	setText(searchCityField, city); 
    }
    
    /**
   	 * This method is intended to click Search button on Home page
     *  
   	 */
    public void clickSearchButton() {  	
		Logger.logInfo("Click Search button on Home page");
		this.waitUntilVisible(searchButton);
		click(searchButton);      
		this.waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to click city name on search results 
   	 * after searching a city on Home page
     * 
   	 */
    public void clickCityNameOfSearchResults() {
 
    	Logger.logInfo("Click City name on search results");
    	click(resultsCityName);
    	this.waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to click first item of search results 
   	 * after searching a city on Home page
     * 
   	 */
    public String getSearchButtonText() {
    	return getText(searchButton);
    }
    
    /**
   	 * This method is intended to get City name on search results 
   	 * after searching a city on Home page
     * 
   	 */
    public String getCityNameOfSearchResults() {
    	return getText(resultsCityName);
    } 

    /**
   	 * This method is intended to get current city name 
   	 * after searching valid city on Home page
     * 
   	 */
    public String getCurrentCityName() {
        return getText(currentCityName);
    }

    /**
   	 * This method is intended to get current temperature 
   	 * after searching valid city on Home page
     * 
   	 */
    public String getCurrentTemparature() {
    	return getText(currentTemperature);
    }

    /**
   	 * This method is intended to get current date 
   	 * after searching valid city on Home page
     * 
   	 */
    public String getCurrentDate() {
    	return getText(currentDate);
    }

    /**
   	 * This method is intended to get inline message 
   	 * after searching invalid city on Home page
     * 
   	 */
    public String getSearchInlineMessage() {
    	return getText(searchInlineMessage);
    }

    /**
   	 * This method is intended to get notification message 
   	 * after searching invalid city on Home page
     * 
   	 */
    public String getSearchNotification() {
    	return getText(searchNotification);
    }
}

