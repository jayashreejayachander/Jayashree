package ProjectDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.analysis.function.Max;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ZoomCar {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.zoomcar.com/chennai/");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.findElementByClassName("search").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[contains(text(),'Thuraipakkam')]").click();
		driver.findElementByXPath("//button[text()='Next']").click();
		
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("dd");
		String today = sdf.format(date);
		int tomorrow=Integer.parseInt(today)+2;
		System.out.println(tomorrow);
		
		
		WebElement tomo = driver.findElementByXPath("//div[contains(text(),today)]");
		String text = tomo.getText();
		tomo.click();
		WebElement nex = driver.findElementByXPath("//button[text()='Next']");
		nex.click();
		WebElement conftomo =driver.findElementByXPath("//div[contains(text(),tomorrow)]");
		String text1 = conftomo.getText();
		conftomo.click();
		
		Thread.sleep(2000);
		
		if(text1.contains(text)) {
			System.out.println("Tomo is selected");
		}else {
			System.out.println("tomo is not selected");
		}
		//String text= driver.findElementByXPath("//div[contains(text(),'14')]").getText();
		/*if(text.contains(tomo.getText())) {
			System.out.println("Tomo is selected");
			
		}else {
			System.out.println("tomo is not selected");
		}*/
		driver.findElementByXPath("//button[text()='Done']").click();
		Thread.sleep(5000);
		List<WebElement> results =driver.findElementsByClassName("car-listing");
		System.out.println(results.size());
		
		List<WebElement> price =driver.findElementsByXPath("//div[@class='price']");
		List<Integer> pri = new ArrayList<>();
		for (WebElement ele : price) {
			int pr= Integer.parseInt(ele.getText().replaceAll("\\D", ""));
			pri.add(pr);
			//System.out.println(pr);
		}
		
		Integer max = Collections.max(pri);
		System.out.println(max+" is maximum price ");
		int i=0;
		if( i==max) {
		String brand = driver.findElementByXPath("//div[@class='price']/../../following::h3").getText();
		System.out.println(brand);
		WebElement book = driver.findElementByXPath("//div[@class='price']/following::button");
		book.click();
		}
		
		
			
		
		
		/*if(max.equals(driver.findElementByXPath("//div[@class='details']/following::h3"))) {
			String name = driver.findElementByXPath("//div[@class='details']/following::h3").getText();
			System.out.println(name+" is brand name");
		}*/
		
		
		
			
		}
		
		
		//driver.close();
	}
	
	
