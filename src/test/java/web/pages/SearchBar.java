package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import web.base.PageObject;
import web.utils.Logger;

/*
 * SearchResults class
 *
 */
public class SearchBar extends PageObject {

	// Page elements locators
	final By desktopMenu = By.xpath("//*[@id='desktop-menu']");
	final By searchCityField = By.xpath("//*[@id='nav-website']//div[@id='desktop-menu']//input[@name='q']");
	final By searchCityFieldMobile = By.xpath("//*[@id='nav-website']//div[@id='mobile-menu']//input[@name='q']");
	final By topNavigation = By.xpath("//*[@id='nav-website']");
	final By firstLevelNav = By.xpath("//*[@id='first-level-nav']");
	final By mobileMenu = By.xpath("//*[@id='mobile-menu']");
	

	// Constructor
	public SearchBar() {
		super();
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
    	pressEnterKeyOnSearchBox();
    	//waitLoadingIconVisibleThenInvisible();
    }

    /**
	 * This method is intended to enter city name 
	 * in the Search city field on Search Bar in Home page
	 *
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void enterSearchCityName(final String city) {
    	Logger.logInfo("Enter '" + city + "' in the Search city field on Search Bar in Home page");
    	this.setText(searchCityField, city); 
    }
    
    /**
   	 * This method is intended to get input text on Search Box 
   	 *  on Search Bar in Home page
   	 */
    public String getSearchBoxText() {
    	return this.getValue(searchCityField);
    }
    
    /**
   	 * This method is intended to press Enter key on Search Box 
   	 *  in Search Bar
   	 */
    public void pressEnterKeyOnSearchBox() {
    	this.getElement(searchCityField).sendKeys(Keys.ENTER);
    }
}

