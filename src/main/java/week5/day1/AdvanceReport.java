package week5.day1;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class AdvanceReport {

	static ExtentHtmlReporter html;
	static ExtentReports extent;
	ExtentTest test;
	public void startReport() {
		html = new ExtentHtmlReporter("./report/extentReport.html");
		extent = new ExtentReports();
	    html.setAppendExisting(true); 
		extent.attachReporter(html); 
	}
	public void initializeTest(String testcaseName,String testDec, String author, String category) {
	    test = extent.createTest(testcaseName, testDec);
	    test.assignAuthor(author);
	    test.assignCategory(category);
	   
	}
	public void logStep(String des, String status) {
		if (status.equalsIgnoreCase("pass")) {
			test.pass(des);
		} else if (status.equalsIgnoreCase("fail")) {
			test.fail(des);
			throw new RuntimeException();
		} else if (status.equalsIgnoreCase("warning")) {
			test.warning(des);
		}
	
	}
	public void endReport() {
		extent.flush();
	}
}

