package bl.framework.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import bl.framework.base.Browser;
import bl.framework.base.Element;
import week5.day1.AdvanceReport;

public class SeleniumBase extends AdvanceReport implements Browser, Element{

	public RemoteWebDriver driver;
	public int i =1;
	@Override
	public void startApp(String url) {
		try {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logStep("The browser launched successfully","pass");     
		} catch (Exception e) {
			System.err.println("Error in getting url");
			logStep("Application could not be started","fail");
		}	finally {
			takeSnap();
		}

	}

	@Override
	public void startApp(String browser, String url) {
		try {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver(); 
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logStep("The browser "+browser+" launched successfully", "pass");
		}catch (IllegalStateException e) {
			System.err.println("Error in opening browser");
			logStep("the browser"+browser+"not launched", "fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch (locatorType) {
		case "id": return driver.findElementById(value);
		case "name": return driver.findElementByName(value);
		case "class": return driver.findElementByClassName(value);
		case "xpath": return driver.findElementByXPath(value);
		case "LinkText": return driver.findElementByLinkText(value);
		default:
			break;
			}
		} catch (NotFoundException e) {
		System.err.println("The element value "+value+"not found");
		}catch (WebDriverException e) {
			System.err.println("WebDriverException occured");	
		}return null;
	}

	@Override
	public WebElement locateElement(String value) {
		try {
			driver.findElementById(value);
			logStep("Element located successfully","pass");
		}catch(NoSuchElementException e) {
			System.err.println("element not located");
			logStep("Element not located","fail");
		}
		return null;
	}

	@Override
	public List<WebElement> locateElements(String type, String value) {
		try {
			driver.findElementById(value);
			logStep("Element located successfully","pass");
		}catch(NoSuchElementException e) {
			System.err.println("element not located");
			logStep("Element not located","fail");
		}

		return null;
	}

	@Override
	public void switchToAlert() {
		try {
		Alert alert = driver.switchTo().alert();
		logStep("Focus switched to alert","pass");
		}catch(NoAlertPresentException e) {
			System.err.println("Alert not present");
			logStep("Alert not present","fail");
		}
	}

	@Override
	public void acceptAlert() {
		String text="";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			logStep("Alert is accepted","pass");
			}catch(NoAlertPresentException e) {
				System.err.println("Alert not present");
				logStep("Alert not present","fail");
			}catch(WebDriverException e) {
				System.err.println("Alert not present");
				logStep("Alert not present","fail");
			}
	}

	@Override
	public void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			logStep("Alert is cancelled","pass");
			}catch(NoAlertPresentException e) {
				System.err.println("Alert not present");
				logStep("Alert not present","fail");
			}catch (WebDriverException e) {
				System.out.println("WebDriverException : "+e.getMessage());
				logStep("Alert not present","fail");
			}
	}

	@Override
	public String getAlertText() {
		String text="";
		try {
			Alert alert = driver.switchTo().alert();
			text=alert.getText();
			logStep("Alert text retrieved successfully","pass");
			}catch(NoAlertPresentException e) {
				System.err.println("Alert not present"+e.getMessage());
				logStep("Alert not present","fail");
			}catch (WebDriverException e) {
				System.err.println("WebDriverException : "+e.getMessage());
			} 
		return text;
	}

	@Override
	public void typeAlert(String data) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(data);
			logStep("Value passed to the alert","pass");
			}catch(NoAlertPresentException e) {
				System.err.println("Alert not present");
				logStep("Alert not present","fail");
			}
	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<>(allWindows);
			String exWindow = allhandles.get(index);
			driver.switchTo().window(exWindow);
			System.out.println("The Window With index: "+index+
					" switched successfully");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With index: "+index+
					" not found");	
		}

	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String eachWindow : allWindows) {
				driver.switchTo().window(eachWindow);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
			System.out.println("The Window With Title: "+title+
					"is switched ");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With Title: "+title+
					" not found");
		} finally {
			takeSnap();
		}
	}

	@Override
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			System.out.println("The window is switched");
			logStep("Window switched","pass");
		}catch(NoSuchWindowException e){
			System.err.println("The window not found");
			logStep("Window not present","fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			System.out.println("The frame is switched");
			logStep("Frame switched","pass");
		}catch(NoSuchFrameException e){
			System.err.println("The frame not found");
			logStep("Frame not present","fail");
		}finally {
			takeSnap();
		}

	}

	@Override
	public void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			System.out.println("The frame is switched");
			logStep("Frame switched","pass");
		}catch(NoSuchFrameException e){
			System.err.println("The frame not found");
			logStep("Frame not present","fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public void defaultContent() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("The first frame is switched");
			logStep("Frame switched","pass");
		}finally {
			takeSnap();
		}

	}

	@Override
	public boolean verifyUrl(String url) {
		try {
			if (driver.getCurrentUrl().equals(url)) {
				System.out.println("The url: "+url+" matched successfully");
			return true;
			} else {
				System.out.println("The url: "+url+" not matched");
			}
			return false;
		}finally {
			takeSnap();
		}
	}

	@Override
	public boolean verifyTitle(String title) {
		try {
			if (driver.getTitle().equals(title)) {
				System.out.println("Page title: "+title+" matched successfully");
			return true;
			} else {
				System.out.println("Page url: "+title+" not matched");
			}
			return false;
		}catch(Exception e) {
			logStep("Verify Title", "fail");
		}
		finally {
			takeSnap();
		}
		return false;
		}
	

	@Override
	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./snaps/img"+i+".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}
		i++;
	}

	@Override
	public void close() {
		try {
		driver.close();
		logStep("browser closed", "pass");
		}catch(Exception e) {
			logStep("browser not closed", "fail");
		}

	}

	@Override
	public void quit() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void click(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			logStep("The Element "+ele+" clicked", "pass"); 
		} catch (StaleElementReferenceException e) {
			logStep("The Element "+ele+" could not be clicked", "fail");
			throw new RuntimeException();
		} finally { 
			takeSnap();
		}
	}

	@Override
	public void append(WebElement ele, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			logStep("The Element "+ele+" clicked","pass");
		} catch (StaleElementReferenceException e) {
			logStep("The Element "+ele+" could not be clicked","fail");
			throw new RuntimeException();
		}
		
	}

	@Override
	public void clear(WebElement ele) {
		try {
			ele.clear();
			logStep("The field is cleared Successfully","pass");
		} catch (ElementNotInteractableException e) {
			logStep("The field is not Interactable","fail");
			throw new RuntimeException();
		}finally {
			takeSnap();
		}
		
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			logStep("The Data :"+data+" entered Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			logStep("The Element "+ele+" is not Interactable", "fail");
			throw new RuntimeException();
		}finally {
			takeSnap();
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		try {
		String text = ele.getText();
		logStep("Text returned","pass");
		return text;
		}finally {
			takeSnap();
		}
	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		try {
			String cssValue = ele.getCssValue("color");
			logStep("Background Colour returned","pass");
			return cssValue;
		}finally{
			takeSnap();
		}
	}

	@Override
	public String getTypedText(WebElement ele) {
		try {
		String attributeValue = ele.getAttribute("value");
		logStep("TypedText"+attributeValue+"returned","pass");
		return attributeValue;
		}catch(NoSuchElementException e) {
			logStep("Text not returned","fail");
		}finally {
			takeSnap();
		}
		return null;
		
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
		new Select(ele).selectByVisibleText(value);
		logStep("Dropdown selected", "pass");
		}catch(NoSuchElementException e) {
			logStep("Dropdown not selected", "fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
		new Select(ele).selectByIndex(index);
		logStep("Dropdown selected", "pass");
		}catch(NoSuchElementException e) {
			logStep("Dropdown not selected", "fail");
		}finally {
			takeSnap();
		}
	}
	

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		try {
		new Select(ele).selectByValue(value);
		logStep("Dropdown selected", "pass");
		}catch(NoSuchElementException e) {
			logStep("Dropdown not selected", "fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().equals(expectedText)) {
				System.out.println("The expected text contains the actual "+expectedText);
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().contains(expectedText)) {
				System.out.println("The expected text contains the actual "+expectedText);
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;

	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).equals(value)) {
				System.out.println("The expected attribute :"+attribute+" value contains the actual "+value);
				return true;
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not contains the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		return false;

	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).contains(value)) {
				System.out.println("The expected attribute :"+attribute+" value contains the actual "+value);
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not contains the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		
	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				System.out.println("The element "+ele+" is visible");
			return true;
			} else {
				System.out.println("The element "+ele+" is not visible");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;
	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				System.out.println("The element "+ele+" is visible");
			return true;
			} else {
				System.out.println("The element "+ele+" is not visible");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;

	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		try {
			if(ele.isEnabled()) {
				System.out.println("The element "+ele+" is Enabled");
				return true;
			} else {
				System.out.println("The element "+ele+" is not Enabled");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;	
		}

	@Override
	public boolean verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				System.out.println("The element "+ele+" is selected");
				return true;
			} else {
				System.out.println("The element "+ele+" is not selected");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

}
