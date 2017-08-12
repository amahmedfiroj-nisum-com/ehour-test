package PageObjects;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.DataTable;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page_Client extends Base {
	
	public Page_Client(String BrowserType) {
		super(BrowserType);
	}
	By by_TextBox_CustomerName = new By.ByXPath("//input[@name='customer.name']");
	By by_TextBox__CustomerCode = new By.ByXPath("//input[@name='customer.code']");
	By by_TextBox_CustomerDescription = new By.ByXPath("//textarea[@name='customer.description']");
	By by_CheckBox_CustomerActive = new By.ByXPath("//input[@name='customer.active']");
	By by_TextBox_Filter = new By.ById("listFilter");
	By by_Table_ClientList = new ByXPath("//div[@id='listContents']/table[@class='entrySelectorTable']");
	By by_Table_ClientList_DisplayedRows = new By.ByXPath(".//tbody/tr[@class='filterRow' and (not(@style) or @style='display: table-row;')]");
	String xpath_ClientList_SelectRowByClientName=".//tbody/tr[@class='filterRow' and (not(@style) or @style='display: table-row;') and (./td[text()='REPLACE'][1])]";
	By by_Table_ClientList_SelectRowByClientName;

	By by_Error = new By.ByXPath("//span[@class='formValidationError']");
	
	
	
	String xpath_Row_DesiredClient = ("./tbody//tr[@class='filterRow' or @class='selectedRow'][./td[1][text()='CLIENTNAME_REPLACE']]");
	By by_Row_DesiredClient;
	public WebElement getDesiredClient(String ClientName) throws Exception{
		getTextBoxFilter().clear();
		
		by_Row_DesiredClient = new By.ByXPath(xpath_Row_DesiredClient.replace("CLIENTNAME_REPLACE", ClientName));
		WebElement we = ReturnSubWebElement("Row : Client Name - " + ClientName, getTableOfRecords(), by_Row_DesiredClient, GlobalShortWait, GlobalShortRetry);
		if(we==null){
			return null;
		}
		MoveToElement(we);
		return we;
	}
	
	//Harish
		public void getMyClient(String ClientName) throws Exception{
			
			Enteringusername().clear();
			Enteringusername().sendKeys(ClientName);
			selectusername(ClientName).click();
		}
		
		By by_Entering_username = new ByXPath("//*[@id='listFilter']");
		public WebElement Enteringusername(){
			
			return driver.findElement(by_Entering_username);
		}
		String username=("//*[text()='CLIENTNAME_REPLACE']");
		public WebElement selectusername(String ClientName){
			By by_select_username= new ByXPath(username.replace("CLIENTNAME_REPLACE",ClientName));
			return driver.findElement(by_select_username);
		}
		
		//Harish
		
	public WebElement getTableClientList() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Table_ClientList);
		return ReturnWebElement("Table - Client List", by_Table_ClientList, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getSelectDisplayedRowByClientName(String UserName) throws Exception{
		xpath_ClientList_SelectRowByClientName = xpath_ClientList_SelectRowByClientName.replace("REPLACE", UserName);
		by_Table_ClientList_SelectRowByClientName = new By.ByXPath(xpath_ClientList_SelectRowByClientName);
		return ReturnSubWebElement("", getTableClientList(), by_Table_ClientList_SelectRowByClientName, GlobalShortWait, GlobalShortRetry);
	}
	
	public WebElement getTextBoxClientName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_CustomerName);
		return ReturnWebElement("Text Box - Client Name", by_TextBox_CustomerName, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getTextBoxClientCode() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox__CustomerCode);
		return ReturnWebElement("Text Box - Client Code", by_TextBox__CustomerCode, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getTextBoxClientDescription() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_CustomerDescription);
		return ReturnWebElement("Text Box - Client Description", by_TextBox_CustomerDescription,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	
	public WebElement getCheckBoxClientActive() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_CustomerActive);
		return ReturnWebElement("CheckBox - Is Client Active", by_CheckBox_CustomerActive,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	public WebElement getError() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error Message", by_Error,GlobalLongWait, GlobalLongWait, expectedcondition);
	}	
	
	public WebElement getTextBoxFilter() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Filter);
		return ReturnWebElement("TextBox - Filter", by_TextBox_Filter, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public Integer getDisplayedFilteredClientCount() throws Exception{
		List<WebElement> we = ReturnSubWebElements("Filtered Client Rows", by_Table_ClientList,by_Table_ClientList_DisplayedRows, GlobalShortWait, GlobalShortRetry); 
		return (we == null?0:we.size());
	}


	By by_Button_Save = new By.ByName("submitButton");
	public WebElement getButtonSave(){
		return driver.findElement(by_Button_Save);
	}
	
	public void CreateClient(DataTable table) throws Exception{
		String ClientName,ClientCode,ClientDescription;
		Boolean IsActive;
		ClientName= table.raw().get(1).get(0);
		ClientCode = table.raw().get(1).get(1);
		ClientDescription = table.raw().get(1).get(2);
		IsActive = table.raw().get(1).get(3).equals("true")?true:false;
		
		getTextBoxClientName().sendKeys(ClientName);
		getTextBoxClientCode().sendKeys(ClientCode);
		getTextBoxClientDescription().sendKeys(ClientDescription);
		CheckOrUncheckTheCheckBox(IsActive, getCheckBoxClientActive());
		getButtonSave().click();
		AssertTrue("Data Saved Message", "Data saved", (getError().getText()!=null ||getError().getText().length()>0)?getError().getText():"<Blank Value>" , getError().getText().equals("Data saved"));
		//Assert.assertTrue(getError().getText().equals("Data saved"));
	}
	
	
	public void SearchClients(String ClientName) throws Exception{
		getTextBoxFilter().clear();
		getTextBoxFilter().sendKeys(ClientName);
		
	}
	

	By by_Button_Delete = new By.ByXPath("//a[@class='button'][./span[text()='delete']]");
	public WebElement getButtonDelete() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Delete);
		return ReturnWebElement("Button Delete", by_Button_Delete, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	

	public Alert getDeleteAlert(){
		Alert a = (new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent()));
		System.out.println( a.getText());
		return a;
	}
	
	public void DeleteClient(String ClientName) throws Exception{
		getButtonDelete().click();
		getDeleteAlert().accept();
		AssertTrue("Data Saved Message", "Data saved", (getError().getText()!=null ||getError().getText().length()>0)?getError().getText():"<Blank Value>" , getError().getText().equals("Data saved"));
	}
	
	public void VerifyIfClientRecordCanBeFoundInSearchResults(String ClientName) throws Exception{
		SearchClients(ClientName);
		AssertTrue("Client Count", 1, getDisplayedFilteredClientCount()!=null?getDisplayedFilteredClientCount():"null", getDisplayedFilteredClientCount().equals(1));
		//Assert.assertTrue("The client count is not 1",getDisplayedFilteredClientCount().equals(1));
	}
	
	public void SelectClientRowFromSearchResults(String ClientName) throws Exception{
		getSelectDisplayedRowByClientName(ClientName).click();
		//Thread.sleep(3000);
		AssertTrue("TextBox - Client Name", ClientName, GetTextBoxValue(getTextBoxClientName()), GetTextBoxValue(getTextBoxClientName()).equals(ClientName));
		//Assert.assertTrue("The client value is " + GetTextBoxValue(getTextBoxClientName()) + " instead of " + ClientName , GetTextBoxValue(getTextBoxClientName()).equals(ClientName));
	}
	
	public void VerifySelectedClient(DataTable table) throws Exception{
		String ClientName,ClientCode,ClientDescription;
		Boolean IsActive;
		ClientName= table.raw().get(1).get(0);
		ClientCode = table.raw().get(1).get(1);
		ClientDescription = table.raw().get(1).get(2);
		IsActive = table.raw().get(1).get(3).equals("true")?true:false;
		
		AssertTrue("TextBox - Client Name", ClientName, GetTextBoxValue(getTextBoxClientName()), GetTextBoxValue(getTextBoxClientName()).equals(ClientName));
		//Assert.assertTrue(GetTextBoxValue(getTextBoxClientName()).equals(ClientName));
		
		AssertTrue("TextBox - Client Code", ClientCode, GetTextBoxValue(getTextBoxClientCode()), GetTextBoxValue(getTextBoxClientCode()).equals(ClientCode));
		//Assert.assertTrue(GetTextBoxValue(getTextBoxClientCode()).equals(ClientCode));
		
		AssertTrue("TextBox - Client Description", ClientDescription, GetTextBoxValue(getTextBoxClientDescription()), GetTextBoxValue(getTextBoxClientDescription()).equals(ClientDescription));
		//Assert.assertTrue(GetTextBoxValue(getTextBoxClientDescription()).equals(ClientDescription));
		
		
		AssertTrue("CheckBox - Active",IsActive,IsCheckBoxChecked(getCheckBoxClientActive()),IsCheckBoxChecked(getCheckBoxClientActive())==IsActive);
		//Assert.assertTrue(IsCheckBoxChecked(getCheckBoxClientActive())==IsActive);
	
	}
}
