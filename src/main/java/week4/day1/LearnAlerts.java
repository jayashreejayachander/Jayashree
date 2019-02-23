package week4.day1;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LearnAlerts {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/eticketing/userSignUp.jsf");
		driver.findElementById("userRegistrationForm:checkavail").click();
		//Handling the alert
		Alert alert = driver.switchTo().alert();
		String alerttext = alert.getText();
		System.out.println(alerttext);
		alert.accept();
		//		driver.switchTo().alert().accept();
		driver.findElementById("userRegistrationForm:userName").sendKeys("Balaji");

	}

	/*//WebDriverWait syntax 
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(element));*/

	/*//take screenShot
	File src = driver.getScreenshotAs(OutputType.FILE);
	File des = new File("./snaps/img.png");
	FileUtils.copyFile(src, des); */ 
}


