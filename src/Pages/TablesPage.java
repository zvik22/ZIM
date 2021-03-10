package Pages;


import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class TablesPage  {

	static final Logger log = LogManager.getLogger(TablesPage.class.getName());
	public WebDriver driver;
	public WebDriverWait wait;
	
	
	//user
	@FindBy(id = "customers")
	WebElement table;
	
	
	
	
	public TablesPage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);				
		wait = new WebDriverWait(driver, 10);
		
	}
	
	public boolean verifyPageLoad() {
		wait.until(ExpectedConditions.visibilityOf(table));	
		return true;
	}
	
	public WebElement getCustomersTables() {
		return table;
	}
	
	
	public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
		
		String tableResultText = null;
		boolean res = false;
		
		try {
			tableResultText = getTableCellTextByXpath(table,searchColumn,searchText,returnColumnText);			
			res = tableResultText.equalsIgnoreCase(expectedText);
			if (!res) {
				log.info("Failed while searching the table");
				log.info("table's target column actual value:" + tableResultText);
				log.info("expected value:" + expectedText);
				return res;
			}
			
			return res;
		}
		catch(Exception e) {
			log.info("Failed to Search the table");
			e.printStackTrace();
			return res;
		}
			
		
	}
	
	public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception 
	{
		String table_xpath_by_id = "//*[@id=\"" + table.getAttribute("id") + "\"]";
		String returnTextXpath = "";
		
		//example: //*[@id="customers"]/tbody//tr/td[3][text()='Germany']/ancestor::tr/td[1]
		returnTextXpath = table_xpath_by_id + "/tbody//tr/td["+ searchColumn +"][text()='"+searchText+"']/ancestor::tr/td["+returnColumnText +"]";		
		
		return driver.findElement(By.xpath(returnTextXpath)).getText();
		
	}
	

	


}
