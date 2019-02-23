package week5.day1;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BasicReport {
		ExtentHtmlReporter html;
		ExtentReports extent;
		ExtentTest test;
		@Test
		public void runReport() {
			html= new ExtentHtmlReporter("./report/extentReport.html");
			extent = new ExtentReports();
			html.setAppendExisting(true);
			extent.attachReporter(html);
			test = extent.createTest("TC001_Login","Login to LeafTaps");
			test.assignAuthor("Jayashree");
			test.assignCategory("Smoke Test");
			try {
				test.pass("Username successfully entered", MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/img1").build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			extent.flush();
			}
		
	}


