package PageObjects;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.*;
import org.junit.Assert;
import PageObjects.Page_Assignments;

public class Page_UserManagement extends Base {

	public Page_UserManagement(String BrowserType) {
		super(BrowserType);
	}
	By by_DropDown_Country = new By.ByXPath("//select[@name='countryId:user.country']");
	By by_DropDown_Location = new By.ByXPath("//select[@name='locationId:user.location']");
	By by_Dropdown_EmployeeType = new By.ByXPath("//select[@name='employeeTypeId:user.employeeType']");


	By by_Timesheet_Approver=new By.ByXPath("//select[@name='user.approver']");
	public WebElement getDropDownTimesheetApprover() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Timesheet_Approver);
		return ReturnWebElement("Timesheet-Approver", by_Timesheet_Approver, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	By by_Table_UserList = new ByXPath("//div[@id='listContents']/table[@class='entrySelectorTable']");
	public WebElement getTableUserList() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Table_UserList);
		return ReturnWebElement("Table - User List", by_Table_UserList, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	public WebElement getDropDownCountry() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_DropDown_Country);
		return ReturnWebElement("Dropdown - Country']", by_DropDown_Country, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	public WebElement getDropDownLocation() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_DropDown_Location);
		return ReturnWebElement("Dropdown - Location", by_DropDown_Location, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getDropDownEmployeeType() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Dropdown_EmployeeType);
		return ReturnWebElement("Dropdown - Employee Type", by_Dropdown_EmployeeType, GlobalShortWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_Toggle_ShowInactive = new By.ByXPath("//span[contains(@class,'fa fa-toggle-')]");
	public void ToggleInactiveFlag(Boolean ShowInactive) throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Toggle_ShowInactive);
		WebElement we = ReturnWebElement("Toggle Inactive Flag", by_Toggle_ShowInactive, GlobalShortWait,GlobalShortRetry,expectedcondition);
		if(we.getAttribute("class").equals("fa fa-toggle-on") ){
			if(ShowInactive==true){
				we.click();
			}
		}
		
		if(we.getAttribute("class").equals("fa fa-toggle-off") ){
			if(ShowInactive==false){
				we.click();
			}
		}
	}
	
	String xpath_Row_DesiredEmployee = ("./tbody//tr[@class='filterRow' or @class='selectedRow'][./td[2][text()='USERNAME_REPLACE']]");
	By by_Row_DesiredEmployee;
	public WebElement getDesiredUser(String UserName) throws Exception{
		Thread.sleep(5000);
		by_Row_DesiredEmployee = new By.ByXPath(xpath_Row_DesiredEmployee.replace("USERNAME_REPLACE", UserName));
		WebElement we = ReturnSubWebElement("User - Row : User Name - " + UserName, getTableOfRecords(), by_Row_DesiredEmployee, GlobalLongWait, GlobalShortRetry);
		if(we==null){
			return null;
		}
		MoveToElement(we);
		return we;
	}
	
	
	

	public void EditUser(List<String> DataTableRow) throws Exception{
		if(!(DataTableRow.get(1).length()==0 || GetTextBoxValue(getInputFirstName()).equals(DataTableRow.get(1)))){
			getInputFirstName().clear();
			getInputFirstName().sendKeys(DataTableRow.get(1));
		}
		
		if(!(DataTableRow.get(2).length()==0 || GetTextBoxValue(getInputLastName()).equals(DataTableRow.get(2)))){
			getInputLastName().clear();
			getInputLastName().sendKeys(DataTableRow.get(2));
		}
		

		if(!(DataTableRow.get(3).length()==0 || GetTextBoxValue(getInputEmail()).equals(DataTableRow.get(3)))){
			getInputEmail().clear();
			getInputEmail().sendKeys(DataTableRow.get(3));
		}
		
		if(!(DataTableRow.get(4).length()>0)){
			getInputPassword().sendKeys(DataTableRow.get(4));
		}
		
		if(!(DataTableRow.get(5).length()>0)){
			getInputCofirmPassword().sendKeys(DataTableRow.get(5));
		}
				
		
		if(!(DataTableRow.get(6).length()==0 || new Select(getDropDownDepartment()).getFirstSelectedOption().getText().equals(DataTableRow.get(6)))){
			new Select(getDropDownDepartment()).selectByVisibleText(DataTableRow.get(6));
		}

		
		if(!(DataTableRow.get(7).length()==0 ||VerifyListBoxSelectedValues(getListBoxUserRoles(), DataTableRow.get(7)))){
			SelectUserRoles(DataTableRow.get(7));		
		}
		
		if(!(DataTableRow.get(8).length()==0 || IsCheckBoxChecked(getCheckBoxActive()) == DataTableRow.get(8).equals("true") )){
			CheckOrUncheckTheCheckBox(DataTableRow.get(8).equals("true"), getCheckBoxActive());
		}
		
		if(!(DataTableRow.get(9).length()==0 || new Select(getDropDownCountry()).getFirstSelectedOption().getText().equals(DataTableRow.get(9)))){
			new Select(getDropDownCountry()).selectByVisibleText(DataTableRow.get(9));
		}

		if(!(DataTableRow.get(10).length()==0 || new Select(getDropDownLocation()).getFirstSelectedOption().getText().equals(DataTableRow.get(10)))){
			new Select(getDropDownLocation()).selectByVisibleText(DataTableRow.get(10));
		}
		
		if(!(DataTableRow.get(11).length()==0 || new Select(getDropDownEmployeeType()).getFirstSelectedOption().getText().equals(DataTableRow.get(11)))){
			//new Select(getDropDownEmployeeType()).selectByVisibleText(DataTableRow.get(10));
			SelectByVisibleText(getDropDownEmployeeType(),DataTableRow.get(11));
		}
		
		getButtonSave().click();
        AssertTrue("Message - Data Saved", "Data saved", getError().getText(),getError().getText().equals("Data saved"));
		getDesiredUser(DataTableRow.get(2)).click();
        
        //Verifying entered data matches the retrieved data
        

        if(!(DataTableRow.get(1).length()==0)){
            AssertTrue("TextBox - First Name", DataTableRow.get(1), GetTextBoxValue(getInputFirstName()), GetTextBoxValue(getInputFirstName()).equals(DataTableRow.get(1))); 
        }
		
		if(!(DataTableRow.get(2).length()==0)){
			AssertTrue("TextBox - Last Name", DataTableRow.get(2), GetTextBoxValue(getInputLastName()), GetTextBoxValue(getInputLastName()).equals(DataTableRow.get(2))); 
		}
		

		if(!(DataTableRow.get(3).length()==0 )){
			AssertTrue("TextBox - Email",DataTableRow.get(3),GetTextBoxValue(getInputEmail()),GetTextBoxValue(getInputEmail()).equals(DataTableRow.get(3)));
		}

		if(!(DataTableRow.get(6).length()==0 )){
			AssertTrue("ListBox - Department - Selected Value",DataTableRow.get(6),GetListBoxFirstSelectedElementText(getDropDownDepartment()),GetListBoxFirstSelectedElementText(getDropDownDepartment()).equals(DataTableRow.get(6)));
		}
        
		if(!(DataTableRow.get(7).length()==0 )){
			AssertTrue("ListBox - User Roles",DataTableRow.get(7),getListBoxUserRoles(),VerifyListBoxSelectedValues(getListBoxUserRoles(), DataTableRow.get(7)));
		}
		
		if(!(DataTableRow.get(8).length()==0 )){
			AssertTrue("Check Box - Is Active",DataTableRow.get(8).equals("true"),IsCheckBoxChecked(getCheckBoxActive()),IsCheckBoxChecked(getCheckBoxActive()) == DataTableRow.get(8).equals("true"));
		}
		
		if(!(DataTableRow.get(9).length()==0 )){
			AssertTrue("ListBox - Country - Selected Value",DataTableRow.get(9),GetListBoxFirstSelectedElementText(getDropDownCountry()),GetListBoxFirstSelectedElementText(getDropDownCountry()).equals(DataTableRow.get(9)));
		}

		if(!(DataTableRow.get(10).length()==0 )){
			AssertTrue("ListBox - Location - Selected Value",DataTableRow.get(10),GetListBoxFirstSelectedElementText(getDropDownLocation()),GetListBoxFirstSelectedElementText(getDropDownLocation()).equals(DataTableRow.get(10)));
		}

		if(!(DataTableRow.get(11).length()==0 )){
			AssertTrue("Dropdown - Employee Type - Selected Value",DataTableRow.get(11),GetListBoxFirstSelectedElementText(getDropDownEmployeeType()),GetListBoxFirstSelectedElementText(getDropDownEmployeeType()).equals(DataTableRow.get(11)));
		}
		
	}
	
	
	
	By by_ListBox_UserRoles_Selected = new By.ByXPath("./option[@selected='selected']");
	public String getSelectedUserRoles() throws Exception{
		List<WebElement> li_we_SelectedUserRoles = ReturnSubWebElements("User Roles - Selected", by_ListBox_UserRoles, by_ListBox_UserRoles_Selected, GlobalShortWait, GlobalShortRetry);
		String s = "";
		for(WebElement we:li_we_SelectedUserRoles){
			s = s + we.getText() + ",";
		}
		
		if(s.length()>0){
			s.substring(0, s.length()-2);
		}
		return s;
	}
	
	
	By by_Input_UserName = new ByXPath("//input[@name='user.username']");
	public WebElement getInputUsername() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_UserName);
		return ReturnWebElement("By Input User Name", by_Input_UserName, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_Input_FirstName = new ByXPath("//input[@name='user.firstName']");
	public WebElement getInputFirstName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_FirstName);
		return ReturnWebElement("TextBox - First Name", by_Input_FirstName, GlobalLongWait, GlobalShortRetry, expectedcondition);
		
	}
	
	By by_Input_LastName = new ByXPath("//input[@name='user.lastName']");
	public WebElement getInputLastName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_LastName);
		return ReturnWebElement("TextBox - Last Name",by_Input_LastName,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Input_Email = new ByXPath("//input[@name='user.email']");
	public WebElement getInputEmail() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_Email);
		return ReturnWebElement("TextBox - Email",by_Input_Email,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Input_Password = new ByXPath("//input[@name='password']");
	public WebElement getInputPassword() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_Password);
		return ReturnWebElement("TextBox - Password",by_Input_Password,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Input_ConfirmPassword = new ByXPath("//input[@name='confirmPassword']");
	public WebElement getInputCofirmPassword() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_ConfirmPassword);
		return ReturnWebElement("TextBox - Confirm Password",by_Input_ConfirmPassword,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	By by_CheckBox_AssignProjectAfterSave = new By.ByXPath("//input[@name='showAssignments']");
	By by_CheckBox_Active = new ByXPath("//input[@name='user.active']");
	By by_ListBox_UserRoles = new ByXPath("//select[@name='user.userRoles']");
	By by_Select_FunctionalGroup = new ByXPath("//select[@name='dept:user.userDepartment']");
	public WebElement getDropDownDepartment() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_FunctionalGroup);
		return ReturnWebElement("DropDownBox - Department",by_Select_FunctionalGroup,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getListBoxUserRoles() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_ListBox_UserRoles);
		return ReturnWebElement("ListBox - User Roles",by_ListBox_UserRoles,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getCheckBoxActive() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_Active);
		return ReturnWebElement("CheckBox - Active",by_CheckBox_Active,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	
	public WebElement getCheckBoxAssignProjectAfterSave() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_AssignProjectAfterSave);
		return ReturnWebElement("CheckBox - Assign Project After Save",by_CheckBox_AssignProjectAfterSave,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	By by_Button_Save = new By.ByName("submitButton");
	public WebElement getButtonSave() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Save);
		return ReturnWebElement("Button - Save",by_Button_Save,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	By by_Error = new By.ByXPath("//span[@class='formValidationError']");
	public WebElement getError() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error / Message",by_Error,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	By by_Input_Filter = new By.ById("listFilter");
	public WebElement getInputFilter() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_Filter);
		return ReturnWebElement("TextBox - Filter",by_Input_Filter,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	public void SelectUserRoles(String UserRolesCommaSeparated) throws Exception{
		new Select(getListBoxUserRoles()).deselectAll();
		String[] arrUserRoles = UserRolesCommaSeparated.split(",");
		for(String s:arrUserRoles){
			new Select(getListBoxUserRoles()).selectByVisibleText(s);
			getListBoxUserRoles().sendKeys(Keys.CONTROL);
		}
	}
	
	
	By by_ButtonDelete = new By.ByXPath("//a[@class='button'][./span[text()='delete']]");
	public WebElement getButtonDelete() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_ButtonDelete);
		return ReturnWebElement("Button - Delete",by_ButtonDelete,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}

	
	//Harish
	By by_displayedProject = new ByXPath("//body/div[2]/div/div/div/div/div/div/div[2]/div/form/div[2]/div/div[2]/table/tbody/tr[2]/td[2]");
	public WebElement displayedProject() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_displayedProject);
		return ReturnWebElement("Displayed Project",by_displayedProject,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}

	By by_Select_project = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr/td[2]/select[@name='formComponents:projectSelection:customer']");
	public WebElement SelectProject() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_project);
		return ReturnWebElement("Select Project",by_Select_project,GlobalLongWait,GlobalShortRetry,expectedcondition);
	
	}
	
	By by_Select_streaminProject = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[2]/td[2]/select/optgroup/option");
	public WebElement SelectstreaminProject() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_streaminProject);
		return ReturnWebElement("Select Stream In Project",by_Select_streaminProject,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_startdate_checkbox = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[9]/td[2]/span/input");
	public WebElement getstartcheckboxstatus() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_startdate_checkbox);
		return ReturnWebElement("Start Date Checkbox",by_startdate_checkbox,GlobalLongWait,GlobalShortRetry,expectedcondition);
	
	}
	By by_enddate_checkbox = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[10]/td[2]/span/input");
	public WebElement getendcheckboxstatus() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_enddate_checkbox);
		return ReturnWebElement("End Date Checkbox",by_enddate_checkbox,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_start_date = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[9]/td[2]/span/input");
	public WebElement getstartDate() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_start_date);
		return ReturnWebElement("Start Date",by_start_date,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Select_Role = new ByXPath("//*[@name='formComponents:rateRole:projectAssignment.role']");
	public WebElement SelectroleinProject() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_Role);
		return ReturnWebElement("Select Role",by_Select_Role,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	
	By by_Select_HourlyRate = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[13]/td[2]/input");
	public WebElement SelectHourlyRate() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_HourlyRate);
		return ReturnWebElement("Select Hourly Rate",by_Select_HourlyRate,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Select_AssignmentType = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[4]/td[2]/select");
	public WebElement SelectAssignmentType() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Select_AssignmentType);
		return ReturnWebElement("Select Assignment Type",by_Select_AssignmentType,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_Saving_AssignedProject = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[17]/td[2]/a[@name='submitButton']");
	public WebElement SavingAssignedproject() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Saving_AssignedProject);
		return ReturnWebElement("Select Assignment Type",by_Saving_AssignedProject,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_end_date = new ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[10]/td[2]/span/input");
	public WebElement getendDate() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_end_date);
		return ReturnWebElement("Select Assignment Type",by_end_date,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	
	By by_logOut = new ByXPath("//body/div/div[2]/div/a[2]");
	public WebElement LogOut() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_logOut);
		return ReturnWebElement("Select Assignment Type",by_logOut,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_previous_month = new ByXPath("//a[@title='Previous month']");
	public WebElement previous_month() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_previous_month);
		return ReturnWebElement("Select Assignment Type",by_previous_month,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	//By week_in_previous_month = new ByXPath("//div[@class='CalendarWeek other LastWeek']");
	By week_in_previous_month = new ByXPath("html/body/div[2]/div/div[2]/div[1]/div/div[2]/div[2]/div[5]/div[7]");
	public WebElement week_in_previous_month() throws Exception{	
		//return driver.findElement(week_in_previous_month);
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(week_in_previous_month);
		return ReturnWebElement("Select Assignment Type",week_in_previous_month,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By by_next_month = new ByXPath("//a[@title='Next month']");
	public WebElement next_month() throws Exception{
		//return driver.findElement(by_next_month);
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_next_month);
		return ReturnWebElement("Select Assignment Type",by_next_month,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	By week_in_after_month = new ByXPath("//div[@class='CalendarWeek other LastWeek']");
	public WebElement week_in_after_month() throws Exception{
		//return driver.findElement(week_in_after_month);
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(week_in_after_month);
		return ReturnWebElement("Select Assignment Type",week_in_after_month,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	//Harish
	
	public void CreateUser(List<String> DataTableRow) throws Exception{
		getInputUsername().sendKeys(DataTableRow.get(0));
		getInputFirstName().sendKeys(DataTableRow.get(1));
		getInputLastName().sendKeys(DataTableRow.get(2));
		getInputEmail().sendKeys(DataTableRow.get(3));
		getInputPassword().sendKeys(DataTableRow.get(4));
		getInputCofirmPassword().sendKeys(DataTableRow.get(5));
		SelectByVisibleText(getDropDownDepartment(),DataTableRow.get(6));
		
		SelectUserRoles(DataTableRow.get(7));
		CheckOrUncheckTheCheckBox(DataTableRow.get(8).equals("true"), getCheckBoxActive());
		CheckOrUncheckTheCheckBox(DataTableRow.get(9).equals("true"), getCheckBoxAssignProjectAfterSave());
		//Thread.sleep(2000);
		SelectByVisibleText(getDropDownCountry(), DataTableRow.get(10));
		//Thread.sleep(5000);
		SelectByVisibleText(getDropDownLocation(),DataTableRow.get(11));
		SelectByVisibleText(getDropDownEmployeeType(),DataTableRow.get(12));
		SelectByVisibleText(getDropDownTimesheetApprover(),DataTableRow.get(13));
		
		getButtonSave().click();
        AssertTrue("Error - Data Saved","Data saved", getError().getText(),getError().getText().equals("Data saved"));
		//Assert.assertTrue(getError().getText().equals("Data saved"));
	}
	public void CreateUserAssignProject(List<String> DataTableRow) throws Exception{
		getInputUsername().sendKeys(DataTableRow.get(0));
		getInputFirstName().sendKeys(DataTableRow.get(1));
		getInputLastName().sendKeys(DataTableRow.get(2));
		getInputEmail().sendKeys(DataTableRow.get(3));
		getInputPassword().sendKeys(DataTableRow.get(4));
		getInputCofirmPassword().sendKeys(DataTableRow.get(5));
		SelectByVisibleText(getDropDownDepartment(),DataTableRow.get(6));

		SelectUserRoles(DataTableRow.get(7));
		CheckOrUncheckTheCheckBox(DataTableRow.get(8).equals("true"), getCheckBoxActive());
		CheckOrUncheckTheCheckBox(DataTableRow.get(9).equals("true"), getCheckBoxAssignProjectAfterSave());
		//Thread.sleep(2000);
		SelectByVisibleText(getDropDownCountry(), DataTableRow.get(10));
		//Thread.sleep(2000);
		SelectByVisibleText(getDropDownLocation(),DataTableRow.get(11));
		SelectByVisibleText(getDropDownEmployeeType(),DataTableRow.get(12));
		SelectByVisibleText(getDropDownTimesheetApprover(),DataTableRow.get(13));

		getButtonSave().click();
	}

		public void DeleteUser(String UserName) throws Exception{
			if(getDesiredUser(UserName)!=null){
				getDesiredUser(UserName).click();
				getButtonDelete().click();
				getDeleteAlert().accept();
				AssertTrue("User Row", "NULL", getDesiredUser(UserName)!=null?"NOT NULL":"NULL", getDesiredUser(UserName)==null);
			}
		}
	
		public void PressDeleteButton() throws Exception{
			getButtonDelete().click();
			getDeleteAlert().accept();
		}
	
	public int getUserSearchResultCount(String UserName) throws Exception{
		getInputFilter().clear();
		getInputFilter().sendKeys(UserName);
		
		List<String> ListOfUsers = getListOfUsers();
		
		if(ListOfUsers==null){
			return 0;
		}else{
			return ListOfUsers.size();
		}
	}

	public WebElement getUserRowFromSearchResultsByUserName(String UserName) throws Exception{
		//return ReturnWebElement("User Row From Search Results", by_SearchResults_UserRow, GlobalShortWait, GlobalShortRetry, null);
		return getTableUserList().findElement(By.xpath(".//tbody/tr[@class='filterRow' and ./td[text()='"+ UserName +"'] and (not(@style) or @style='display: table-row;')]"));
	}
	
	public void SelectUserRowFromSearchResults(String UserName) throws Exception{
		getUserRowFromSearchResultsByUserName(UserName).click();
		//Thread.sleep(2000);
		System.out.println("Input Attribute Value - " + getInputUsername().getAttribute("value"));
		Assert.assertTrue(getInputUsername().getAttribute("value").equals(UserName));
	}
	
	//Harish
	
public boolean Verify_start_end_date() throws InterruptedException {
		
		
		if(driver.getPageSource().contains("Scotiabank")/*displayedProject().getText().contains("BCI Autenticacion")*/) {
			//Thread.sleep(3000);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!");
			return true;
		} else {
			return false;
		}
		
	}


private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
public void SetDate(List<String> DataTableRow) throws Exception{
	
	Select dropdown = new Select(SelectProject());
	dropdown.selectByVisibleText("SB - Scotiabank");
	//Thread.sleep(5000);
	SelectstreaminProject().click();
	//Thread.sleep(5000);
	Select AssignmentTypedropdown = new Select(SelectAssignmentType());
	AssignmentTypedropdown.selectByVisibleText(DataTableRow.get(0));

	if(getstartcheckboxstatus().isSelected()){
		System.out.println("Start date checkbox is in selected state");
		getstartcheckboxstatus().click();
	} else {
		System.out.println("Start date checkbox is already unselected");
	}
	//Thread.sleep(10000);
	if(getendcheckboxstatus().isSelected()) {
		System.out.println("End date checkbox is in selected state");
		getendcheckboxstatus().click();
	} else {
		System.out.println("End date checkbox is already unselected");
	}



	Date currentDate = new Date();
	getstartDate().sendKeys(dateFormat.format(currentDate));
	//Thread.sleep(3000);
    
    // convert date to calendar
    Calendar c = Calendar.getInstance();
    c.setTime(currentDate);

    // manipulate date
    c.add(Calendar.YEAR, 0);
    c.add(Calendar.MONTH, 1);
    c.add(Calendar.DATE, 0); //same with c.add(Calendar.DAY_OF_MONTH, 1);
    c.add(Calendar.HOUR, 0);
    c.add(Calendar.MINUTE, 0);
    c.add(Calendar.SECOND, 0);

    // convert calendar to date
    Date currentDatePlusOne = c.getTime();
    getendDate().sendKeys(dateFormat.format(currentDatePlusOne));
    //Thread.sleep(3000);
	

	SelectroleinProject().sendKeys("QA Engineer");
	SelectHourlyRate().sendKeys("60");
	SavingAssignedproject().click();
	LogOut().click();
	//Thread.sleep(10000);
}

/*public boolean check_project_in_other_dates() throws Exception {
	int i=0;
	try {
		previous_month().click();
		System.out.println("Navigating to the previous month");
		//SelectingWeek().click();
		if(!(displayedProject().getText().contains("BCI Cells"))) {
			i++;
			for(int m=1;m<3;m++) {
				System.out.println("Setting the date to "+m+" month later");
				next_month().click();
				if(m==2) {
					SelectingWeek().click();
				}
			}
			
			if(!(displayedProject().getText().contains("BCI Cells"))) {
				i++;
			} 
		} 
		
		if(i==2) {
			LogOut().click();
			return true;
		} else {
			LogOut().click();
			return false;
		}
	} catch (Exception e) {
		System.out.println(e);
		return false;
	}
	
}*/
	

public boolean check_project_in_other_dates() throws Exception {
	int i=0;
	try {
		previous_month().click();
		System.out.println("Navigating to the previous month");
		//Thread.sleep(4000);
		week_in_previous_month().click();
		System.out.println("Selecting week in previous month");
		//Thread.sleep(4000);
		if(driver.getPageSource().contains("Scotiabank")) {
			System.out.println("Project is displayed in the dates other than the assigned dates!!");
		} else {
			i++;
			//wait.until(ExpectedConditions.visibilityOfElementLocated(by_next_month));
			for(int m=1;m<3;m++) {
				System.out.println("Setting the date to "+m+" month later");
				//Thread.sleep(4000);
				System.out.println("QQQQ"+m);
				next_month().click();
				if(m==2) {
					//SelectweekinAFterMonth().click();
					System.out.println(week_in_after_month().getText());
					week_in_after_month().click();
					System.out.println("Selecting week in after month");
				}
			}
			//Thread.sleep(5000);
			if(driver.getPageSource().contains("Scotiabank")) {
				System.out.println("Project is displayed in the dates other than the assigned dates!!!");
			} else {
				i++;
			}
		}
		//Thread.sleep(5000);
		if(i==2) {
			LogOut().click();
			return true;
		} else {
			return false;
		}
	} catch (Exception e) {
		System.out.print("The exception is :"+e);
		return false;
	}
	
}



	//Harish

	By by_Week_in_aftermonth = new By.ByXPath("//div[@class='CalendarWeek other LastWeek']");
	public WebElement SelectweekinAFterMonth() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Week_in_aftermonth);
		return ReturnWebElement("Week selection",by_Week_in_aftermonth,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	By by_Table_UserList_TableRows = new By.ByXPath(".//tbody/tr[@class='filterRow' and (not(@style) or @style='display: table-row;')]");
	public List<String> getListOfUsers() throws Exception{
		List<String> listofusers = new ArrayList<String>();
		//List<WebElement> rows_user = getTableUserList().findElements(By.xpath(".//tbody/tr[@class='filterRow' and (not(@style) or @style='display: table-row;')]"));
		List<WebElement> rows_user = ReturnSubWebElements("User Rows", by_Table_UserList, by_Table_UserList_TableRows, GlobalShortWait,GlobalShortRetry); 
				
		if (rows_user.size()==0) return null;
			
		for(WebElement e: rows_user){
			String s = "";
			s = s + e.findElement(By.xpath("./td[1]")).getText();
			s = s + "|" + e.findElement(By.xpath("./td[2]")).getText();
			s = s + "|" + e.findElement(By.xpath("./td[3]")).getText();
			listofusers.add(s);
		}
		return listofusers;
	}
	
	By by_passwordfield= By.xpath("*//tr/td[text()='Password:']");
	public void passwordField() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_passwordfield);
		ReturnWebElement("Password Field", by_passwordfield, GlobalLongWait, GlobalShortWait, expectedcondition);
	}
	
	By by_enterpassword= By.xpath("//input[@name='password']");
	public void enterPasssword(String password) throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_enterpassword);
		ReturnWebElement("Enter Password", by_enterpassword, GlobalLongWait, GlobalShortWait, expectedcondition);
	}
	
	By by_confirmpassword= By.xpath("//input[@name='password']");
	public void enterConfirmPassword(String confirmpassword) throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_confirmpassword);
		ReturnWebElement("Confirm Password", by_confirmpassword, GlobalLongWait, GlobalShortWait, expectedcondition).sendKeys(confirmpassword);
	}
	

	public void userSaved() throws Exception{
		
		Assert.assertTrue(getError().getText().equals("Data saved"));
	}
	
	
}
