package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;



public class Page_Header extends Base{

	
	
	public Page_Header(String BrowserType) {
		super(BrowserType);
		
	}

	By by_String_LoggedInAs = new By.ByXPath("//div[@class='LoggedInAs']/a/span");
	public String getLoggedInAs() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_String_LoggedInAs);
		return ReturnWebElement("Text - Logged In As", by_String_LoggedInAs, GlobalLongWait, GlobalShortRetry, expectedcondition).getText();
	
	}
	
	By by_Link_SignOut = new By.ByXPath("//a[text()='sign out']");
	//By by_Link_SignOut = new By.ByLinkText("sign out");
	public WebElement getLinkSignOut() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Link_SignOut);
		return ReturnWebElement("Link - Sign Out", by_Link_SignOut, GlobalLongWait, GlobalShortRetry, expectedcondition);
		//return driver.findElement(By.linkText("sign out"));
	}

	//below method i added
	By by_Approve_TimeSheetButton=new By.ByXPath("//a[@class='btn approve-btn'][@href='../../eh/consultant/approval']");
	public WebElement btnApproveTimesheet()throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Approve_TimeSheetButton);
		return ReturnWebElement("ApproveTimesheet-Button",by_Approve_TimeSheetButton,GlobalLongWait, GlobalShortRetry, expectedcondition );
	}

	//below i added

	By by_buttonApproveAnchorGreen=new By.ByXPath("//a[@class='approval-anchor']//img[@src='../../img/14/approve_check_mark.png']");

	public WebElement buttonApproveAnchorGreen() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_buttonApproveAnchorGreen);
		return ReturnWebElement("ApproveTimesheet-Button",by_buttonApproveAnchorGreen,GlobalLongWait, GlobalShortRetry, expectedcondition );
	}

	//below i added

	By by_buttonRejectAnchorRed=new By.ByXPath("//a[@class='approval-anchor']//img[@src='../../img/14/reject-cross-mark.png']");

	public WebElement buttonRejectAnchorRed() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_buttonRejectAnchorRed);
		return ReturnWebElement("ApproveTimesheet-Button",by_buttonRejectAnchorRed,GlobalLongWait, GlobalShortRetry, expectedcondition );
	}



	By by_Menu_Manage = new By.ByXPath("//li[a[text()='manage']]");
	public WebElement getMenuManage() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Menu_Manage);
		return ReturnWebElement("Menu - Manage", by_Menu_Manage, GlobalLongWait, GlobalShortRetry, expectedcondition);
		//return driver.findElement(By.xpath("//li[a[text()='manage']]"));
	}
	
	By by_MenuItem_Departments = new By.ByXPath(".//li/a[text()='Functional groups']");
	public WebElement getMenuItemDepartments() throws Exception{
		return ReturnSubWebElement("Menu Item - Departments", getMenuManage(), by_MenuItem_Departments, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Functional groups']"));
	}
	
	By by_MenuItem_Users = new By.ByXPath(".//li/a[text()='Users']");
	public WebElement getMenuItemUsers() throws Exception{
		return ReturnSubWebElement("Menu Item - Users", getMenuManage(), by_MenuItem_Users, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Users']"));
	}
	
	By by_MenuItem_Clients = new By.ByXPath(".//li/a[text()='Clients']");
	public WebElement getMenuItemClients() throws Exception{
		return ReturnSubWebElement("Menu Item - Clients", getMenuManage(), by_MenuItem_Clients, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Clients']"));
	}
	
	By by_MenuItem_Projects = new By.ByXPath(".//li/a[text()='Projects']");
	public WebElement getMenuItemProjects() throws Exception{
		return ReturnSubWebElement("Menu Item - Projects", getMenuManage(), by_MenuItem_Projects, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Projects']"));
	}
	
	By by_MenuItem_Assignments = new By.ByXPath(".//li/a[text()='Assignments']");
	public WebElement getMenuItemAssignments() throws Exception{
		return ReturnSubWebElement("Menu Item - Assignments", getMenuManage(), by_MenuItem_Assignments, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Assignments']"));
	}
	
	By by_MenuItem_LockedPeriods = new By.ByXPath(".//li/a[text()='Locked periods']");
	public WebElement getMenuItemLockedPeriods() throws Exception{
		return ReturnSubWebElement("Menu Item - Locked Periods", getMenuManage(), by_MenuItem_LockedPeriods, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Locked periods']"));
	}
	
	By by_MenuItem_EditTimesheet = new By.ByXPath(".//li/a[text()='Edit timesheet']");
	public WebElement getMenuItemEditTimesheet() throws Exception{
		return ReturnSubWebElement("Menu Item - Edit Timesheet", getMenuManage(), by_MenuItem_EditTimesheet, GlobalShortWait, GlobalShortRetry);
		//return getMenuManage().findElement(By.xpath(".//li/a[text()='Edit timesheet']"));
	}
	
	By by_PageHeader = new By.ByXPath("//h1/span");
	public String getPageHeader() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_PageHeader);
		return ReturnWebElement("Page Header", by_PageHeader, GlobalLongWait, GlobalShortRetry, expectedcondition).getText();
		//return driver.findElement(By.xpath("//h1/span")).getText();
	}
	
	
	public void SelectMenuItem(String MenuItem) throws Exception{
		switch (MenuItem){
		case "Functional groups":
			MoveToElement(getMenuManage());
			getMenuItemDepartments().click();
			break;
		case "Users":
			MoveToElement(getMenuManage());
			getMenuItemUsers().click();
			break;
		case "Clients":
			MoveToElement(getMenuManage());
			getMenuItemClients().click();
			break;
		case "Projects":
			MoveToElement(getMenuManage());
			getMenuItemProjects().click();
			break;
		case "Assignments":
			MoveToElement(getMenuManage());
			getMenuItemAssignments().click();
			break;
		case "Locked periods":
			MoveToElement(getMenuManage());
			getMenuItemLockedPeriods().click();
			break;
		case "Edit timesheet":
			MoveToElement(getMenuManage());
			getMenuItemEditTimesheet().click();
			break;
		default:
			throw new Exception("Invalid selection of menu item");
		}
	}
	
	public void SignOut() throws Exception {
		getLinkSignOut().click();
		
	}
	
	public void VerifySignOut() throws IOException, InterruptedException{
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = FetchPropertyValues("URL_Base") + FetchPropertyValues("URI_LoginPage");
		AssertTrue("Page URL", ExpectedURL, ActualURL, ExpectedURL.equals(ActualURL));
	}

	//below method i added
	public void buttonApproveTimeSheet() throws Exception {
		btnApproveTimesheet().click();
	}
  //below i added
	public void buttonApproveAnchor()throws Exception{
		buttonApproveAnchorGreen().click();
	}

	//below i added
	public void buttonRejectAnchor()throws Exception{
		buttonRejectAnchorRed().click();
	}

}
