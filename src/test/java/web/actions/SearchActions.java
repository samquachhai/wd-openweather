package web.actions;

import web.pages.HomePage;

/**
 * SearchActions
 *  
 */
public class SearchActions {

	/**
   	 * Constructor
   	 * 
   	 */
	public SearchActions() {
		
	}
	
	/**
   	 * This method is intended to navigate to Home page, 
   	 * 
   	 */
	public void searchPreparation() {
		navigateToHomePage();
		
	}
		
	/**
   	 * This method is intended to navigate to Home page
     * 
   	 */
	public void navigateToHomePage(){
        final HomePage homePage = new HomePage();
        homePage.navigateToHomePage();      
    }

	 
    /**
	 * This method is intended to search weather for a city of your choice
	 *
	 * @param cityName city to search for (E.g. Ha Noi)
	 * 
	 */
    public void searchCity(final String cityName) {
    	final HomePage homePage = new HomePage();
    	homePage.searchCity(cityName);
    }
	
}
