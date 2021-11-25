/*
 * PageOject class
 *
 */

package web.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import web.utils.Constants;
import web.utils.WebDrivers;

/**
 * The PageObject
 * 
 */
public class PageObject {

	//==================== Click methods ===========================
	
	/**
	 * This method is intended to click on visible Web Element
	 *
	 * @param locator used to find the element 
	 * 
	 */
	
	public void click(By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			// Click on element
			element.click();
			
		} catch (Exception e) {
		
			clickJScript(locator);
		}
	}
	
	/**
	 * This method is intended to click on WebElement using Jscript
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void clickJScript(By locator) {
		try {
			
			JavascriptExecutor executor = (JavascriptExecutor) WebDrivers.getCurrentWebDriver();
			executor.executeScript("arguments[0].click();", 
					WebDrivers.getCurrentWebDriver().findElement(locator));

		} catch (Exception e) {
			
		}
	}

	/**
	 * This method is intended to click on WebElement using Jscript
	 *
	 * @param element the element to click on 
	 * 
	 */
	public void clickJScript(WebElement element) {
		try {
			
			JavascriptExecutor executor = (JavascriptExecutor) WebDrivers.getCurrentWebDriver();
			executor.executeScript("arguments[0].click();", element);

		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to click on visible Web Element using Mouse Actions
	 *
	 * @param locator used to find the element 
	 * 
	 */
	
	public void clickUsingActions(By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
						
			// Click on element using Actions
			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			
		} catch (Exception e) {
		
		}
	}
	
	/**
	 * This method is intended to scroll to visible element then click on element
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void scrollToThenClick(By locator) {
		
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			((JavascriptExecutor) WebDrivers.getCurrentWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            
			// Click on element using Actions
			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			
		} catch (Exception e) {
			
		}
        
    }
	
	//==================== Set methods ===========================
	
	/**
	 * This method is intended to enter text to the active Web Element
	 *
	 * @param locator used to find the element 
	 * @param keysToSend character sequence to send to the element
	 * 
	 */
	
	public void setText(By locator, CharSequence... keysToSend)  {
		
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			element.clear();
			element.sendKeys(keysToSend);
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to enter text to the active Web Element using Mouse Actions
	 *
	 * @param locator used to find the element 
	 * @param keysToSend character sequence to send to the element
	 * 
	 */
	public void setTextUsingActions(By locator, CharSequence... keysToSend) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			element.clear();
			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			actions.moveToElement(element).sendKeys(keysToSend).perform();
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to simulate typing into an element having random wait time. 
	 *
	 * @param locator used to find the element 
	 * @param text the text to send to the element
	 * 
	 */
    public void sendKeysAsHuman(By locator, String text) {
    	
    	try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
	    	Random r = new Random();
		    for(int i = 0; i < text.length(); i++) {
		        try {
		            Thread.sleep((int)(r.nextGaussian() * 15 + 100));
		        } catch(InterruptedException e) {
		        	
		        }
		        
		        String s = new StringBuilder().append(text.charAt(i)).toString();
		        element.sendKeys(s);
		    }
    	}catch (Exception e) {
    		
    	}
	}
    
    /**
	 * This method is intended to simulate typing into an element having random wait time. 
	 *
	 * @param locator used to find the element 
	 * @param text the text to send to the element
	 * 
	 */
    public void sendKeys(By locator, String value) {
              
    	try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
        	element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("Error in sending [%s] to the following element: [%s]", value, locator.toString()));
        }
    }
 
    /**
	 * This method is intended to clear the value of a text entry element. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void clearText(By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
            element.clear();
        } catch (Exception e) {
            
        }
    }
	
	//==================== Select methods ===========================
	
	/**
	 * This method is intended to wait and select the dropdown option at the given index. 
	 *
	 * @param locator used to find the element 
	 * @param index the option at this index will be selected
	 * 
	 */
	public void selectByIndex(By locator, int index) {
		try {
			// Wait for web element to be clickable
			waitUntilVisible(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			// Create object of the Select class
			Select se = new Select(element);
						
			// Select the option by index
			se.selectByIndex(index);
			
		} catch (Exception e) {
		
		}
		
	}
	
	/**
	 * This method is intended to select the dropdown option at the given index. 
	 *
	 * @param locator used to find the element 
	 * @param index the option at this index will be selected
	 * 
	 */
	public void selectOptionsByIndex(By locator, int index) {
		try {
			// Wait for web element to be clickable
			waitUntilVisible(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			// Create object of the Select class
			Select se = new Select(element);
						
			// Select the option by index
			se.selectByIndex(index);
			
		} catch (Exception e) {
		
		}
		
	}
	
	/**
	 * This method is intended to wait the select all the dropdown options that have a value matching. 
	 *
	 * @param locator used to find the element 
	 * @param value the value option to match against
	 * 
	 */
	public void selectByValue(By locator, String value) {
		// Wait for web element to be clickable
		waitUntilClickable(locator);
		
		try {
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			// Create object of the Select class
			Select se = new Select(element);
						
			// Select the option with value
			se.selectByValue(value);
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to select all the dropdown options that have a value matching. 
	 *
	 * @param locator used to find the element 
	 * @param value the value option to match against
	 * 
	 */
	public void selectOptionsByValue(By locator, String value) {
		
		try {
			// Create object of the Select class
			Select se = new Select(getElement(locator));
						
			// Select the option with value
			se.selectByValue(value);
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to wait then select all the dropdown options that display text matching. 
	 *
	 * @param locator used to find the element 
	 * @param text the visible text to match against
	 * 
	 */
	public void selectByVisibleText(By locator, String text) {
		try {
			// Wait for web element to be visible
			waitUntilVisible(locator);
			
			// Find element
			WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
			// Create object of the Select class
			Select se = new Select(element);
						
			// Select the option using the visible text
			se.selectByVisibleText(text);
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to select all the dropdown options that display text matching. 
	 *
	 * @param locator used to find the element 
	 * @param text the visible text to match against
	 * 
	 */
	public void selectOptionsByText(By locator, String text) {
		
		try {
			// Create object of the Select class
			Select se = new Select(getElement(locator));
						
			// Select the option using the visible text
			se.selectByVisibleText(text);
			
		} catch (Exception e) {
			
		}
	}

	 /**
	 * This method is intended to select an item under given parent element that display text matching. 
	 *
	 * @param locator used to find the parent element 
	 * @param text the visible text to match against
	 * 
	 */
	public void selectListItemContainsText(By locator, String text) {
		
		List<WebElement> elements = getElements(locator);

        for (WebElement element : elements) {
            if (element == null) {
                throw new TestException("Some elements in the list do not exist");
            }
            if (element.isDisplayed()) {
            	// Click on element using Actions
    			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
    			actions.moveToElement(element).click().perform();
            	
    			break;
            }
        }
        
	}
    
	    
	//==================== Get methods ===========================
	
	/**
	 * This method is intended to check if element present in DOM of current page
	 *
	 * @param locator used to find the element 
	 * 
	 * @return true if element presented , false if not presence 
	 */
	public boolean isElementPresent(By locator) {
		try {
			WebDrivers.getCurrentWebDriver().findElement(locator)
					.getLocation();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * This method is intended to get the visible text of Web Element
	 *
	 * @param locator used to find the element 
	 * 
	 * @return The visible text of this element , null if element not visible 
	 */
	
	public String getText(By locator) {	
		
		// Wait for web element to be visible
		waitUntilVisible(locator);
		WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
		
		// Get web element text or innerText
		if(element != null) {
			String text = "";
			try {
				text = element.getText();
			} catch (Exception e) {
				text = element.getAttribute("innerText");
			}
			return text;
		}
		
		return null;
			
	}
	
	/**
	 * This method is intended to get all elements within current context
	 *
	 * @param parentLocator used to find the parent element
	 * @param childLocator the sequence to search for. 
	 *        Use ".//" to limit your search to the children of parent element.
	 * 
	 * @return A list of all WebElements, or empty list if nothing matches 
	 */
	public List<WebElement> getElements(By parentLocator, By childLocator) {
		// Wait for parent web element to be visible
		waitUntilVisible(parentLocator);
		WebElement parent = WebDrivers.getCurrentWebDriver().findElement(parentLocator);
		
		// Get get all elements within current context
		return parent.findElements(childLocator);
	}
	
	/**
	 * This method is intended to get first child element within current context
	 *
	 * @param parentLocator used to find the parent element
	 * @param childLocator the sequence to search for. 
	 *        Use ".//" to limit your search to the children of parent element.
	 * 
	 * @return the last matching element on current context 
	 */
	public WebElement getLastChildElement(By parentLocator, By childLocator) {
		// Wait for parent web element to be visible
		waitUntilVisible(parentLocator);
		WebElement parent = WebDrivers.getCurrentWebDriver().findElement(parentLocator);
		
		// Get get all elements within current context
		List<WebElement> elements= parent.findElements(childLocator);

		return elements.get(elements.size() - 1);

	}
	
	/**
	 * This method is intended to get last child element within current context
	 *
	 * @param parentLocator used to find the parent element
	 * @param childLocator the sequence to search for. 
	 *        Use ".//" to limit your search to the children of parent element.
	 * 
	 * @return the first matching element on current context 
	 */
	public WebElement getFirstChildElement(By parentLocator, By childLocator) {
		// Wait for parent web element to be visible
		waitUntilVisible(parentLocator);
		WebElement parent = WebDrivers.getCurrentWebDriver().findElement(parentLocator);
			
		// Get get all elements within current context
		return parent.findElement(childLocator);
	}
	
	/**
	 * This method is intended to get all dropdown values of current select element within current context
	 *
	 * @param locator used to find the select element
	 * 
	 * @return the list of dropdown values 
	 */
	public List<String> getDropdownValues(By locator) {

    	// Wait for web element to be clickable
    	waitUntilClickable(locator);
    	
    	WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
    	
        Select dropdown = new Select(element);
        List<String> elementList = new ArrayList<String>();

        List<WebElement> allValues = dropdown.getOptions();

        if (allValues == null) {
            throw new TestException("Some elements in the list do not exist");
        }

        for (WebElement value : allValues) {
            if (value.isDisplayed()) {
                elementList.add(value.getText().trim());
            }
        }
        return elementList;
    }
	
	/**
	 * This method is intended to get the first element within the current page. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public WebElement getElement(By locator) {
        try {
            return WebDrivers.getCurrentWebDriver().findElement(locator);
        } catch (Exception e) {
            
        }
        return null;
    }

    /**
	 * This method is intended to get all the elements within the current page. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public List<WebElement> getElements(By locator) {
    	
        try {
        	waitForPresenceOfElementLocated(locator);
        	
            return WebDrivers.getCurrentWebDriver().findElements(locator);
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element did not display: [%s] ", locator.toString()));
        }
    }

    /**
	 * This method is intended to get list of all elements text. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public List<String> getElementsText(By locator) {
        List<String> elementList = new ArrayList<String>();
        List<WebElement> elements = getElements(locator);

        for (WebElement element : elements) {
            if (element == null) {
                throw new TestException("Some elements in the list do not exist");
            }
            
            elementList.add(element.getText());
            
        }
        return elementList;
    }

	//==================== Wait methods ===========================
	
	/**
	 * This method is intended to wait for a web element to be visible
	 * in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * 
	 * @return the WebElement once it is visible
	 */
	public void waitUntilVisible(By locator) {
		waitUntilVisible(locator, Constants.WAIT_LONG_SECONDS);
		
	}

	/**
	 * This method is intended to wait for a web element to be visible
	 * in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * @param timeOutInSeconds The timeout in seconds
	 * 
	 * @return the WebElement once it is visible
	 */
	public void waitUntilVisible(By locator, int timeOutInSeconds) {
		
		try {
        	WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), Constants.WAIT_LONG_SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not visible: %s", locator));
        }
	}
	
	/**
	 * This method is intended to wait for a web element to be visible 
	 * and enable such that you can click
	 *  
	 * @param locator used to find the element
	 * 
	 * @return the WebElement once it is located and clickable (visible and enabled)
	 */
	public void waitUntilClickable(By locator) {
		waitUntilClickable(locator, Constants.WAIT_LONG_SECONDS);
		
	}
	
	/**
	 * This method is intended to wait for a web element to be visible 
	 * and enable such that you can click
	 *  
	 * @param locator used to find the element
	 * @param timeOutInSeconds the timeout in seconds
	 * 
	 * @return the WebElement once it is located and clickable (visible and enabled)
	 */
	public void waitUntilClickable(By locator, int timeOutInSeconds) {
		try {
        	WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), Constants.WAIT_LONG_SECONDS);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not clickable: %s", locator));
        }
	}
	
	/**
	 * This method is intended to wait for a web element contains given text 
	 *  
	 * @param locator used to find the element
	 * @param text to be present in the element
	 * 
	 */
	public void waitUntilTextDisplay(By locator, Object text) {
		waitUntilTextDisplay(locator, text, Constants.WAIT_LONG_SECONDS);
	}
	
	/**
	 * This method is intended to wait for a web element contains given text 
	 *  
	 * @param locator used to find the element
	 * @param text to be present in the element
	 * @param timeOutInSeconds the timeout in seconds
	 * 
	 */
	public void waitUntilTextDisplay(By locator, Object text, int timeOutInSeconds) {
		try {
			
			WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.textToBePresentInElement(
					WebDrivers.getCurrentWebDriver().findElement(locator), text.toString()));
			
		} catch (Exception e) {
			throw new NoSuchElementException(String.format("The following element was not visible: %s", locator));
		}
	}
	
	
	/**
	 * This method is intended to wait for page loading ready complete
	 * 
	 */
	public void waitUntilPageLoad() {
		try {
			WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
					Constants.WAIT_VERY_LONG_SECONDS);
			
		    wait.until(new ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver wd) {
		            return ((JavascriptExecutor) WebDrivers.getCurrentWebDriver())
		            		.executeScript("return document.readyState").equals("complete");
		        }
		    });
		    
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * This method is intended to wait for Javascript loading ready complete
	 * 
	 */
	public void waitUntilJsLoad() {
		try {
		    JavascriptExecutor executor = (JavascriptExecutor) WebDrivers
					.getCurrentWebDriver();;
		    if((Boolean) executor.executeScript("return window.jQuery != undefined")) {
		        while(!(Boolean) executor.executeScript("return jQuery.active == 0")) {
		            Thread.sleep(1000);
		        }
		    }
		    Thread.sleep(1000);
		} catch(Exception e) {
			
		}
	}

	/**
	 * This method is intended to wait for element present on DOM of current page
	 * 
	 */
   public void waitForPresenceOfElementLocated(By selector) {
        try {
        	WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), Constants.WAIT_LONG_SECONDS);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));
        }
    }

   //==================== Browser methods ===========================

   /**
	 * This method is intended to navigate to a given URL in current browser
	 * 
	 * @param URL the URL to load
	 */
	public void navigateToURL(String URL) {
        try {
            WebDrivers.getCurrentWebDriver().navigate().to(URL);
        } catch (Exception e) {
            throw new TestException("URL did not load");
        }
    }

	/**
	 * This method is intended to move back a single item in browser history 
	 * 
	 */
    public void navigateBack() {
        try {
            WebDrivers.getCurrentWebDriver().navigate().back();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not navigate back to previous page.");
            throw new TestException("Could not navigate back to previous page.");
        }
    }

    /**
	 * This method is intended to get the title of current page
	 * 
	 */
    public String getPageTitle() {
        try {
            return WebDrivers.getCurrentWebDriver().getTitle();
        } catch (Exception e) {
            throw new TestException(String.format("Current page title is: %s", WebDrivers.getCurrentWebDriver().getTitle()));
        }
    }

    /**
	 * This method is intended to get the current URL of current page
	 * 
	 */
    public String getCurrentURL() {
        try {
            return WebDrivers.getCurrentWebDriver().getCurrentUrl();
        } catch (Exception e) {
            throw new TestException(String.format("Current URL is: %s", WebDrivers.getCurrentWebDriver().getCurrentUrl()));
        }
    }
    
    /**
	 * This method is intended  to cause current executing thread to sleep
	 * 
	 */
    public void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
           
        }
    }

   

	/**
	 * This method is intended to focus on top window/first frame
	 * 
	 */
	public void switchToDefaultContent() {
		try {
			TimeUnit.MILLISECONDS.sleep(250);
			WebDrivers.getCurrentWebDriver().switchTo().defaultContent();
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			
		}
		
	}

}
