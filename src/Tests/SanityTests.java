package Tests;


import org.testng.Assert;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DataProviders.ExcelDataProvider;
import Framework.CustomAssertions;
import Framework.GeneralUtility;
import Pages.TablesPage;



public class SanityTests {

	
	

	private WebDriver driver;
	private TablesPage tp;
	
	private String baseUrl;
	String environment, browser;
	
	
	static final Logger log = LogManager.getLogger(SanityTests.class.getName());
	
	@BeforeClass(alwaysRun = true)
	public void Test_Prepration() throws Exception {
		baseUrl = GeneralUtility.getProperty("url");
		browser = GeneralUtility.getProperty("browser");
		driver = GeneralUtility.getWebDriver(browser, baseUrl);

	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void Before_Method() throws Exception {
		
		tp = new TablesPage(driver);

	}
	

	@Test(enabled = true, dataProvider = "getDataForTableTest", dataProviderClass = ExcelDataProvider.class, priority = 1, groups = "sanity")			
	public void tableTest(String searchColumn, String searchValue, String returnColumn, String expectedValue)  {
		try {
		log.info("Started tableTest with inputs: " + searchColumn  + ","  + searchValue  + ","  +   returnColumn  + ","  +  expectedValue);
			
		//navigate to base page	
		CustomAssertions.assertTrue(tp.verifyPageLoad(),"Failed to verify page load");
		
		//check the table if expected value is equals to actual return column value
		CustomAssertions.assertTrue(tp.verifyTableCellText(tp.getCustomersTables(), Integer.parseInt(searchColumn), searchValue, Integer.parseInt(returnColumn), expectedValue));
		
				
		}
		//Catching failed assertions or unhandled Exceptions
		catch(Exception e) {
			log.info("Test Failed");
			e.printStackTrace();			
			
			Assert.fail(e.getMessage());
		}
		
		log.info("tableTest finished Successfully");   
	}

	@AfterMethod(alwaysRun = true)
	public void Exit_Clean_After_Test() throws Exception {
				

	}

	@AfterClass(alwaysRun = true)
	public void End_Exectuion() throws Exception  {		
		GeneralUtility.tearDown(driver);
	}

}
