package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWindow {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://legacy.crystalcruises.com/");
		
		driver.findElementByPartialLinkText("GUEST CHECK-").click();
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		//Switching control from win 1 to win 2
		
		Set<String> allwindows = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>();
		ls.addAll(allwindows);
		
		driver.switchTo().window(ls.get(1));
		System.out.println(driver.getTitle());		
		
		driver.findElementByPartialLinkText("View Offer").click();
		
		//Switching from win 2 to win 3
		allwindows = driver.getWindowHandles();
		ls = new ArrayList<>();		
		ls.addAll(allwindows);
		
		driver.switchTo().window(ls.get(2));
		System.out.println(driver.getTitle());	
		
		
		
		

	}

}
