package bl.framework.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import bl.framework.api.SeleniumBase;

public class EditLead extends SeleniumBase {
	@Test
	public void login() throws InterruptedException {
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement eleUsername = locateElement("id", "username");
		clearAndType(eleUsername, "DemoSalesManager"); 
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, "crmsfa");
		Thread.sleep(3000);
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin); 
		click(locateElement("LinkText", "CRM/SFA"));
		click(locateElement("LinkText", "Create Lead"));
		clearAndType(locateElement("id", "createLeadForm_companyName"), "CG");
		clearAndType(locateElement("id", "createLeadForm_firstName"), "Jayashree");
		clearAndType(locateElement("id", "createLeadForm_lastName"), "Jayachander");
		click(locateElement("name", "submitButton"));
		click(locateElement("LinkText", "Edit"));
		clearAndType(locateElement("id", "updateLeadForm_companyName"), "CG");
		clearAndType(locateElement("id", "updateLeadForm_firstName"), "Jayashree");
		clearAndType(locateElement("id", "updateLeadForm_lastName"), "Jayachander1");
		click(locateElement("name", "submitButton"));
		
}
}
