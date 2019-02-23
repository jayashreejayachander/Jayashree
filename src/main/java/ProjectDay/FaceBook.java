package ProjectDay;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaceBook {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.findElementById("email").sendKeys("jayashreejayachander1000@gmail.com");
		driver.findElementById("pass").sendKeys("arun868109");
		driver.findElementByXPath("//input[@value='Log In']").click();
		Thread.sleep(3000);
		
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		Alert ale = wait.until(ExpectedConditions.alertIsPresent());*/
		
		
			Alert alert = driver.switchTo().alert();
			String alerttext = alert.getText();
			System.out.println(alerttext);
			alert.dismiss();
	
		
		driver.findElementByXPath("//input[@name='q']").sendKeys("TestLeaf");
		driver.findElementByXPath("//i[@class='_585_']").click();
	}

}
