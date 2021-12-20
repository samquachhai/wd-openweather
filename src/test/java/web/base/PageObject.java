package web.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import web.utils.Constants;
import web.utils.WebDrivers;

/**
 * The PageObject
 * 
 */
public class PageObject {
	
	/**
	 * Constructor
	 */
	public PageObject() {
		
	}
	//==================== Click methods ===========================
	
	/**
	 * This method is intended to click on visible Web Element
	 *
	 * @param locator used to find the element 
	 * 
	 */
	
	public void click(final By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Click on element
			element.click();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		} catch (StaleElementReferenceException e) {
		
			throw new StaleElementReferenceException(String.format("The following element did not display: [%s] ", 
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to click on WebElement using Jscript
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void clickJScript(final By locator) {
		try {
			// Find element
			final WebElement element = getElement(locator);
						
			final JavascriptExecutor executor = (JavascriptExecutor) WebDrivers.getCurrentWebDriver();
			executor.executeScript("arguments[0].click();", element);

		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to click on visible Web Element using Mouse Actions
	 *
	 * @param locator used to find the element 
	 * 
	 */
	
	public void clickUsingActions(final By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Click on element using Actions
			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to scroll to visible element then click on element
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void scrollToThenClick(final By locator) {
		
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			final JavascriptExecutor executor = (JavascriptExecutor) WebDrivers.getCurrentWebDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
            
			// Click on element using Actions
			final Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
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
	
	public void setText(final By locator, final CharSequence... keysToSend)  {
		
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Clear and send keys to element
			element.clear();
			element.sendKeys(keysToSend);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to enter text to the active Web Element 
	 * using Mouse Actions
	 *
	 * @param locator used to find the element 
	 * @param keysToSend character sequence to send to the element
	 * 
	 */
	public void setTextUsingActions(final By locator, final CharSequence... keysToSend) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Clear and send keys to element
			element.clear();
			final Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
			actions.moveToElement(element).click().perform();
			actions.moveToElement(element).sendKeys(keysToSend).perform();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to simulate typing into an element having random wait time. 
	 *
	 * @param locator used to find the element 
	 * @param text the text to send to the element
	 * 
	 */
    public void sendKeysAsHuman(final By locator, final String text) {
    	
    	try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			
	    	Random random = new Random();
		    for(int i = 0; i < text.length(); i++) {
		        try {
		            Thread.sleep((int)(random.nextGaussian() * 15 + 100));
		        } catch(InterruptedException e) {
		        	
		        }
		        
		        String s = new StringBuilder().append(text.charAt(i)).toString();
		        element.sendKeys(s);
		    }
    	}catch (NoSuchElementException e) {
    		throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
    	}
	}
    
    /**
	 * This method is intended to simulate typing into an element having random wait time. 
	 *
	 * @param locator used to find the element 
	 * @param text the text to send to the element
	 * 
	 */
    public void sendKeys(final By locator, final String value) {
              
    	try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
        	element.clear();
            element.sendKeys(value);
        } catch (NoSuchElementException e) {
        	throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
        }
    }
 
    /**
	 * This method is intended to clear the value of a text entry element. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
	public void clearText(final By locator) {
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
            element.clear();
        } catch (NoSuchElementException e) {
        	throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
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
	public void selectByIndex(final By locator, final int index) {
		try {
			// Wait for web element to be clickable
			waitUntilVisible(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option by index
			select.selectByIndex(index);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
		
	}
	
	/**
	 * This method is intended to select the dropdown option at the given index. 
	 *
	 * @param locator used to find the element 
	 * @param index the option at this index will be selected
	 * 
	 */
	public void selectOptionsByIndex(final By locator, final int index) {
		try {
			// Find element
			final WebElement element = getElement(locator);
			
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option by index
			select.selectByIndex(index);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
		
	}
	
	/**
	 * This method is intended to wait the select all the dropdown options that have a value matching. 
	 *
	 * @param locator used to find the element 
	 * @param value the value option to match against
	 * 
	 */
	public void selectByValue(final By locator, final String value) {
		
		try {
			// Wait for web element to be clickable
			waitUntilClickable(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option with value
			select.selectByValue(value);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to select all the dropdown options that have a value matching. 
	 *
	 * @param locator used to find the element 
	 * @param value the value option to match against
	 * 
	 */
	public void selectOptionsByValue(final By locator, final String value) {
		
		try {
			// Find element
			final WebElement element = getElement(locator);
						
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option with value
			select.selectByValue(value);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to wait then select all the dropdown options that display text matching. 
	 *
	 * @param locator used to find the element 
	 * @param text the visible text to match against
	 * 
	 */
	public void selectByVisibleText(final By locator, final String text) {
		try {
			// Wait for web element to be visible
			waitUntilVisible(locator);
			
			// Find element
			final WebElement element = getElement(locator);
			
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option using the visible text
			select.selectByVisibleText(text);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}
	
	/**
	 * This method is intended to select all the dropdown options that display text matching. 
	 *
	 * @param locator used to find the element 
	 * @param text the visible text to match against
	 * 
	 */
	public void selectOptionsByText(final By locator, final String text) {
		
		try {
			// Find element
			final WebElement element = getElement(locator);
						
			// Create object of the Select class
			final Select select = new Select(element);
						
			// Select the option using the visible text
			select.selectByVisibleText(text);
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(String.format("The following element did not display: [%s] ",
					locator.toString()));
		}
	}

	 /**
	 * This method is intended to select an item under given parent element that display text matching. 
	 *
	 * @param locator used to find the parent element 
	 * @param text the visible text to match against
	 * 
	 */
//	public void selectListItemContainsText(final By locator, final String text) {
//		
//		List<WebElement> elements = getElements(locator);
//
//        for (WebElement element : elements) {
//            if (element == null) {
//                
//            }
//            if (element.isDisplayed()) {
//            	// Click on element using Actions
//    			Actions actions = new Actions(WebDrivers.getCurrentWebDriver());
//    			actions.moveToElement(element).click().perform();
//            	
//    			break;
//            }
//        }
//        
//	}
//    
	    
	//==================== Get methods ===========================
	
	/**
	 * This method is intended to check if element present in DOM of current page
	 *
	 * @param locator used to find the element 
	 * 
	 * @return true if element presented , false if not presence 
	 */
	public boolean isElementPresent(final By locator) {
		try {
			WebDrivers.getCurrentWebDriver().findElement(locator)
					.getLocation();
			return true;
		} catch (NoSuchElementException e) {
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
	public String getText(final By locator) {	
		try {
			// Wait for web element to be visible
			waitUntilVisible(locator);
			
			// Find element
			final WebElement element = getElement(locator);
						
			return element.getText();
		} catch(NoSuchElementException e) {
			
			return null;		
		}
			
	}
	
	/**
	 * This method is intended to get the value of the given attribute of the element
	 *
	 * @param locator used to find the element 
	 * @param attribute the name of attribute
	 * 
	 * @return The attribute current value or null if current value is not set 
	 */
	public String getAttribute(final By locator, final String attribute)
	{
		return getElement(locator).getAttribute(attribute);
	}
	
	/**
	 * This method is intended to get the value of the attribute 'value' of the element
	 *
	 * @param locator used to find the element
	 * 
	 * @return The attribute current value or null if current value is not set 
	 */
	public String getValue(final By locator)
	{
		return getElement(locator).getAttribute("value");
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
		
		// Find element
		final WebElement parent = getElement(parentLocator);
		
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

		try {
	    	// Wait for web element to be clickable
	    	waitUntilClickable(locator);
	    	
	    	WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
	    	
	        Select dropdown = new Select(element);
	        List<String> elementList = new ArrayList<String>();
	
	        List<WebElement> optionValues = dropdown.getOptions();
	
	        for (WebElement value : optionValues) {
	            if (value.isDisplayed()) {
	                elementList.add(value.getText().trim());
	            }
	        }
	        return elementList;
		} catch (NoSuchElementException e) {
			return Collections.emptyList();
		}
    }
	
	/**
	 * This method is intended to get the first element within the current page. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public WebElement getElement(final By locator) {
       return WebDrivers.getCurrentWebDriver().findElement(locator);       
    }

    /**
	 * This method is intended to get all the elements within the current page. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public List<WebElement> getElements(final By locator) {
    	return WebDrivers.getCurrentWebDriver().findElements(locator);
    }

    /**
	 * This method is intended to get list of all elements text. 
	 *
	 * @param locator used to find the element 
	 * 
	 */
    public List<String> getElementsText(final By locator) {
    	try {
	        List<String> elementList = new ArrayList<String>();
	        List<WebElement> elements = getElements(locator);
	
	        for (WebElement element : elements) {
	            elementList.add(element.getText());	            
	        }
	        return elementList;
	        
    	} catch(NoSuchElementException e) {
    		return Collections.emptyList();
    	}
    }

	//==================== Wait methods ===========================
	
	/**
	 * This method is intended to wait for a web element to be visible
	 * in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * 
	 */
	public void waitUntilVisible(final By locator) {
		waitUntilVisible(locator, Constants.WAIT_LONG_SECONDS);
		
	}

	/**
	 * This method is intended to wait for a web element to be visible
	 * in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * @param timeOutInSeconds The timeout in seconds
	 * 
	 */
	public void waitUntilVisible(final By locator, final int timeOutInSeconds) {
		
		try {
        	final WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
        			Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            
        }
	}
	
	/**
	 * This method is intended to wait for a web element to be visible 
	 * and enable such that you can click
	 *  
	 * @param locator used to find the element
	 * 
	 */
	public void waitUntilClickable(final By locator) {
		waitUntilClickable(locator, Constants.WAIT_LONG_SECONDS);
		
	}
	
	/**
	 * This method is intended to wait for a web element to be visible 
	 * and enable such that you can click
	 *  
	 * @param locator used to find the element
	 * @param timeOutInSeconds the timeout in seconds
	 * 
	 */
	public void waitUntilClickable(final By locator, final int timeOutInSeconds) {
		try {
        	final WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
        			Duration.ofSeconds(Constants.WAIT_LONG_SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (NoSuchElementException e) {
            
        }
	}
	
	/**
	 * This method is intended to wait for a web element contains given text 
	 *  
	 * @param locator used to find the element
	 * @param text to be present in the element
	 * 
	 */
	public void waitUntilTextDisplay(final By locator, final Object text) {
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
	public void waitUntilTextDisplay(final By locator, final Object text, final int timeOutInSeconds) {
		try {
			
			final WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
					Duration.ofSeconds(timeOutInSeconds));
			wait.until(ExpectedConditions.textToBePresentInElement(
					WebDrivers.getCurrentWebDriver().findElement(locator), text.toString()));
			
		} catch (NoSuchElementException e) {
			
		}
	}
	
	
	/**
	 * This method is intended to wait for page loading ready complete
	 * 
	 */
	public void waitUntilPageLoad() {
		try {
			WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
					Duration.ofSeconds(Constants.WAIT_VERY_LONG_SECONDS));
			
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
	public void waitForPresence(final By selector) {
        try {
        	final WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
        			Duration.ofSeconds(Constants.WAIT_LONG_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (NoSuchElementException e) {
            
        }
    }

	/**
	 * This method is intended to wait for a web element to be invisible
	 * or not present on DOM in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * @param timeOutInSeconds The timeout in seconds
	 * 
	 */
	public void waitUntilInvisible(By locator, int timeOutInSeconds) {
		
		try {
			final WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(), 
       			Duration.ofSeconds(timeOutInSeconds));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
           
		}
	}
	
	/**
	 * This method is intended to wait for a web element to be invisible
	 * or not present on DOM in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * 
	 */
	public void waitUntilInvisible(By locator) {
		waitUntilInvisible(locator, Constants.WAIT_LONG_SECONDS);
		
	}
	
	/**
	 * This method is intended to wait for a web element to be visible
	 * and then invisible or not present on DOM in a specific timeout in seconds
	 * 
	 * @param locator used to find the element
	 * @param timeOutInSeconds The timeout in seconds
	 * 
	 */
	public void waitUntilVisibleThenInvisible(By locator, int timeOutInSeconds) {
		
		try {
			
			waitIgnoringException(ExpectedConditions.visibilityOfElementLocated(locator),
					timeOutInSeconds);

            WebElement element = WebDrivers.getCurrentWebDriver().findElement(locator);
			if (element != null) {
				WebDriverWait wait = new WebDriverWait(WebDrivers.getCurrentWebDriver(),
						Duration.ofSeconds(timeOutInSeconds));
				wait.until(ExpectedConditions.invisibilityOf(element));
			}
		} catch (Exception e) {
			
		}

	}
	
	private FluentWait<WebDriver> newFluentWait(int timedOutInSeconds) {
        Duration timeout = Duration.ofSeconds(timedOutInSeconds);
        Duration pollingInMillis = Duration.ofMillis(500);
        return new FluentWait<>(WebDrivers.getCurrentWebDriver())
                .withTimeout(timeout).pollingEvery(pollingInMillis)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }
	
	private WebElement waitIgnoringException(
			Function<? super WebDriver, WebElement> expectedCondition, 
			int timedOutInSeconds) {
		FluentWait<WebDriver> fluentWait = newFluentWait(timedOutInSeconds);
        WebElement element = null;
		try {			
			element = fluentWait.until(expectedCondition);
		} catch (Exception e) {
			
		}
		return element;	
	}
	
	
   //==================== Browser methods ===========================

   /**
	 * This method is intended to navigate to a given URL in current browser
	 * 
	 * @param url the URL to load
	 */
	public void navigateToUrl(String url) {
        try {
            WebDrivers.getCurrentWebDriver().navigate().to(url);
        } catch (Exception e) {
            
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
            
        }
    }

    /**
	 * This method is intended to get the title of current page
	 * 
	 */
    public String getPageTitle() {
       return WebDrivers.getCurrentWebDriver().getTitle();
    }

    /**
	 * This method is intended to get the current URL of current page
	 * 
	 */
    public String getCurrentUrl() {
        return WebDrivers.getCurrentWebDriver().getCurrentUrl();
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
