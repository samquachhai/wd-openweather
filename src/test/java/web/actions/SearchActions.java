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
	 * @param city the city to search for (E.g. 'Ha Noi', or 'Vung Tau, VN')
	 * 
	 */
    public void searchCity(final String city) {
    	final HomePage homePage = new HomePage();
    	homePage.searchCity(city);
    }
	
}
