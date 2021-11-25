package web.actions;

import web.pages.HomePage;

/**
 * SearchActions
 *  
 */
public class SearchActions {

	/**
   	 * This method is intended to navigate to Home page, open Language Settings, select prefer language and save changes
     * 
   	 */
	public void searchPreparation() {
		navigateToHomePage();
		
	}
	
	/**
   	 * This method is intended to select prefer language and save changes
     * 
   	 */
	public void selectAndSavePreferLanguage(String languageCountryCode){
    	
    	
    }
	
	/**
   	 * This method is intended to navigate to Home page
     * 
   	 */
	public void navigateToHomePage(){
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();      
    }

	 
    /**
	 * This method is intended to search weather for a city of your choice
	 *
	 * @param cityName city to search for (E.g. Ha Noi)
	 * 
	 */
    public void searchCity(String cityName) {
    	HomePage homePage = new HomePage();
    	homePage.searchCity(cityName);
    }
	
}
