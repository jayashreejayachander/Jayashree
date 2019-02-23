package week4.day1;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class LearnFrames {

		public static void main(String[] args) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
			//switching to frame
			WebElement frameelement = driver.findElementById("iframeResult");
			driver.switchTo().frame(frameelement);
			driver.findElementByXPath("//button[text()='Try it']").click();
			Alert alert = driver.switchTo().alert();
			String text = alert.getText();
			System.out.println("Message from alert "+text);
			alert.dismiss();
			driver.switchTo().frame("iframeResult");
			String message = driver.findElementById("demo").getText();
			if(message.contains("cancelled")) {
				System.out.println("Message verified");
			}
			driver.switchTo().defaultContent();

		}
	}
