package bl.framework.testcases;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bl.framework.design.ProjectMethods;

public class TC001_CreateLead extends ProjectMethods{
	
	@BeforeTest
	public void setData() {
		testcaseName = "TC001_CreateLead";
		testDec = "Create a new Lead in leaftaps";
		author = "Gayatri";
		category = "Smoke";
	} 
	
	@DataProvider(name="getData")
	public String[][] fetchData(){
		String[][] data = new String[2][3];
		data[0][0] = "TestLeaf";
		data[0][1] = "jsri";
		data[0][2] = "j";
		
		data[1][0] = "TestLeaf";
		data[1][1] = "poo";
		data[1][2] = "r";
		return data;
	}
	//@Test(invocationCount=2, invocationTimeOut=30000)
	@Test(dataProvider="getData")
	public void createLead(String cname, String fname, String lname) {
		click(locateElement("xpath", "//a[text()='Leads']"));
		click(locateElement("xpath", "//a[text()='Create Lead']"));
		clearAndType(locateElement("id", "createLeadForm_companyName"), cname);
		clearAndType(locateElement("id", "createLeadForm_firstName"), fname);
		clearAndType(locateElement("id", "createLeadForm_lastName"), lname);
		click(locateElement("name", "submitButton")); 
	}
	
	
	/*//	@Test(priority = 2)
//	@Test(enabled = false)
	public void createLead1() {
		click(locateElement("link", "Leads"));
		click(locateElement("link", "Create Lead"));
		clearAndType(locateElement("id", "createLeadForm_companyName"), "TL");
		clearAndType(locateElement("id", "createLeadForm_firstName"), "Koushik");
		clearAndType(locateElement("id", "createLeadForm_lastName"), "Ch");
		click(locateElement("name", "submitButton")); 
	}*/
	
	
	
}