package PageObjects;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.text.SimpleDateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;

public class Page_Assignments extends Base{

	public Page_Assignments(String BrowserType) {
		super(BrowserType);
	}
	
	By by_Error = new By.ByXPath("//span[@class='formValidationError']");
	public WebElement getError() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error Message upon save or delete", by_Error,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	String xpath_Row_DesiredEmployee = ("./tbody//tr[@class='filterRow' or @class='selectedRow'][./td[1][text()='LASTNAME_REPLACE']][./td[2][text()='FIRSTNAME_REPLACE']]");
	By by_Row_DesiredEmployee;
	public WebElement getDesiredUser(String FirstName,String LastName) throws Exception{
		by_Row_DesiredEmployee = new By.ByXPath(xpath_Row_DesiredEmployee.replace("LASTNAME_REPLACE", LastName).replace("FIRSTNAME_REPLACE", FirstName));
		WebElement we = ReturnSubWebElement("User - Row - First Name - " + FirstName + " & Last Name " + LastName, getTableOfRecords(), by_Row_DesiredEmployee, GlobalLongWait,GlobalShortRetry);
		MoveToElement(we);
		return we;
	}
	
	
	String xpath_ListBox_Project = "//select[@name='formComponents:projectSelection:projectAssignment.project']//option[text()='PROJECT_REPLACE'][./parent::optgroup[@label='ACCOUNT_REPLACE']]";
	By by_ListBox_Project;
	
	public void SelectProject(String Account, String Project) throws Exception{
		by_ListBox_Project = new By.ByXPath(xpath_ListBox_Project.replace("PROJECT_REPLACE", Project).replace("ACCOUNT_REPLACE", Account));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_ListBox_Project);
		WebElement we = ReturnWebElement("List Box - Account - " + Account + " & Project - " + Project , by_ListBox_Project, GlobalLongWait, GlobalShortRetry, expectedcondition);
		MoveToElement(we);
		we.click();
	}
	
	By by_Dropdown_AssignmentType = new By.ByXPath("//select[@name='formComponents:assignmentType:projectAssignment.assignmentType']");
	public WebElement getDropdownAssignmentType() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Dropdown_AssignmentType);
		return ReturnWebElement("Drop Down List Box - Assignment Type", by_Dropdown_AssignmentType, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	public void SelectAssignmentType(String AssignmentTypeText) throws Exception{
		SelectByVisibleText(getDropdownAssignmentType(), AssignmentTypeText);
	}
	
	By by_CheckBox_StartDate = new By.ByXPath("//input[@type='checkbox'][@name='formComponents:assignmentType:dateStart:updateTarget:infiniteDate']");
	public WebElement getCheckBoxStartDate() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_StartDate);
		return ReturnWebElement("Checkbox - Start Date",by_CheckBox_StartDate,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_CheckBox_EndDate = new By.ByXPath("//input[@type='checkbox'][@name='formComponents:assignmentType:dateEnd:updateTarget:infiniteDate']");
	public WebElement getCheckBoxEndDate() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_EndDate);
		return ReturnWebElement("CheckBox - Start Date",by_CheckBox_EndDate,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	
	
	By by_TextBox_Role = new By.ByXPath("//input[@type='text'][@name='formComponents:rateRole:projectAssignment.role']");
	public WebElement getTextBoxRole() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Role);
		return ReturnWebElement("TextBox - Role", by_TextBox_Role,GlobalLongWait, GlobalLongWait, expectedcondition);
	}
	
	By by_TextBox_HourlyRate = new By.ByXPath("//input[@type='text'][@name='formComponents:rateRole:projectAssignment.hourlyRate']");
	public WebElement getTextBoxHourlyRate() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_HourlyRate);
		return ReturnWebElement("TextBox - HourlyRate",by_TextBox_HourlyRate,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_CheckBox_Active = new By.ByXPath("//input[@type='checkbox'][@name='formComponents:projectAssignment.active']");
	public WebElement getCheckBoxActive() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_Active);
		return ReturnWebElement("CheckBox - Active",by_CheckBox_Active,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	//Harish
	
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");	
	public void AssignDateBasedProject(List<String> DataTableRow) throws Exception{
		
		String Account = DataTableRow.get(2);
		String Project = DataTableRow.get(3);
		String AssignmentType = DataTableRow.get(4);
		String Role = DataTableRow.get(5);
		String HourlyRate = DataTableRow.get(6);
		//Boolean Active = DataTableRow.get(7)==null?false:DataTableRow.get(9).equals("true");
		
		if(getAssignmentRowAccountName(Account,Project)!=null )
		{
			
				System.out.println(getAssignmentRowAccountName(Account,Project).getText());
				return;
		}
		
		SelectProject(Account, Project);
		SelectAssignmentType(AssignmentType);
		if(getstartcheckboxstatus().isSelected()){
			System.out.println("Start date checkbox is in selected state");
			getstartcheckboxstatus().click();
		} else {
			System.out.println("Start date checkbox is already unselected");
		}
		if(getendcheckboxstatus().isSelected()) {
			System.out.println("End date checkbox is in selected state");
			getendcheckboxstatus().click();
		} else {
			System.out.println("End date checkbox is already unselected");
		}
		
		Date currentDate = new Date();
		getstartDate().sendKeys(dateFormat.format(currentDate));
		//Thread.sleep(3000);
	    //System.out.println("######"+dateFormat.format(currentDate));
	    
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

	    //System.out.println("$$$$$$$$$$$"+dateFormat.format(currentDatePlusOne));
	    getendDate().sendKeys(dateFormat.format(currentDatePlusOne));
	    //Thread.sleep(3000);
		getTextBoxRole().sendKeys(Role);
		getTextBoxHourlyRate().sendKeys(HourlyRate);
		getButtonSave().click();
		AssertTrue("Data Saved Message","Data saved", getError().getText(),getError().getText().equals("Data saved"));
		AssertTrue("Assigned Project", "not null", getAssignmentRowAccountName(Account,Project)==null?"null":"not null", getAssignmentRowAccountName(Account,Project)!=null);
		
		
		//Assert.assertTrue(getError().getText().equals("Data saved"));
		//Assert.assertTrue(getMainProject(Account,Project)!=null);
	}
	
	By by_startdate_checkbox = new By.ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[9]/td[2]/span/input");
	public WebElement getstartcheckboxstatus() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_startdate_checkbox);
		return ReturnWebElement("StartDate - Checkbox",by_startdate_checkbox,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	By by_enddate_checkbox = new By.ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[10]/td[2]/span/input");
	public WebElement getendcheckboxstatus() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_enddate_checkbox);
		return ReturnWebElement("EndDate - Checkbox",by_enddate_checkbox,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_start_date = new By.ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[9]/td[2]/span/input");
	public WebElement getstartDate () throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_start_date);
		return ReturnWebElement("EndDate - Checkbox",by_start_date,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	By by_end_date = new By.ByXPath("//body/div[2]/div/div[2]/div/div[2]/div/div/div/div/form/table/tbody/tr[10]/td[2]/span/input");
	public WebElement getendDate() throws Exception{
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_end_date);
		return ReturnWebElement("EndDate - Checkbox",by_end_date,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	//Harish
	
	
	public void AssignProject(List<String> DataTableRow) throws Exception{
		
		String Account = DataTableRow.get(2);
		String Project = DataTableRow.get(3);
		String AssignmentType = DataTableRow.get(4);
		Boolean StartDate = DataTableRow.get(5)==null?false:DataTableRow.get(5).equals("true");
		Boolean EndDate = DataTableRow.get(6)==null?false:DataTableRow.get(6).equals("true");
		String Role = DataTableRow.get(7);
		String HourlyRate = DataTableRow.get(8);
		Boolean Active = DataTableRow.get(9)==null?false:DataTableRow.get(9).equals("true");
		
		if (getAssignmentComponents(Account,Project)!=null)
		{
			System.out.println(getAssignmentRowAccountName(Account,Project).getText());
			return;
		}
		
		SelectProject(Account, Project);
		SelectAssignmentType(AssignmentType);
		CheckOrUncheckTheCheckBox(StartDate, getCheckBoxStartDate());
		CheckOrUncheckTheCheckBox(EndDate, getCheckBoxEndDate());
		getTextBoxRole().clear();
		getTextBoxRole().sendKeys(Role);
		getTextBoxHourlyRate().clear();
		getTextBoxHourlyRate().sendKeys(HourlyRate);
		getButtonSave().click();
		AssertTrue("Data Saved Message","Data saved", getError().getText(),getError().getText().equals("Data saved"));
		AssertTrue("Assigned Project", "not null", getAssignmentRowAccountName(Account,Project)==null?"null":"not null", getAssignmentRowAccountName(Account,Project)!=null);
		
		
		//Assert.assertTrue(getError().getText().equals("Data saved"));
		//Assert.assertTrue(getMainProject(Account,Project)!=null);
	}
	
	String xpath_Cell_MainProject = "//table[@class='contentTable']/tbody//td[@class='main project'][./a/span[text()='PROJECT_REPLACE']]/span[text()='ACCOUNT_REPLACE']";
	By by_Cell_MainProject;
	public WebElement getMainProject(String AccountName,String ProjectName) throws Exception{
		by_Cell_MainProject = new By.ByXPath(xpath_Cell_MainProject.replace("PROJECT_REPLACE", ProjectName).replace("ACCOUNT_REPLACE", AccountName));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Cell_MainProject);
		return ReturnWebElement("Assignments - Account & Project",by_Cell_MainProject,GlobalLongWait,GlobalLongRetry,expectedcondition);
	}
	
	String xpath_AssignmentRow;// = "//table[@class='contentTable']/tbody/tr[./td[./a/span[text()='"+ project +"'][./span[text()='"+ account +"']]]";
	By by_AssignmentRow = null;
	String xpath_AssignmentRow_Project; //= "./a/span[text()='PROJECT_NAME']";
	By by_AssignmentRow_Project = null;
	
	String xpath_AssignmentRow_Account; //= "./span[text()='ACCOUNT_NAME']";
	By by_AssignmentRow_Account = null;
	
	String xpath_AssignmentRow_EditButton = ".//i[@class='fa fa-pencil-square-o']";
	By by_AssignmentRow_EditButton = new By.ByXPath(xpath_AssignmentRow_EditButton);
	
	String xpath_AssignmentRow_StartDate = "./td[@class='main date'][1]";
	By by_AssignmentRow_StartDate=new By.ByXPath(xpath_AssignmentRow_StartDate); 
	
	String xpath_AssignmentRow_EndDate = "./td[@class='main date'][2]";
	By by_AssignmentRow_EndDate = new By.ByXPath(xpath_AssignmentRow_EndDate);
	
	By by_AssignmentRow_AssignmentType = new By.ByXPath("./following-sibling::tr[1]//td/text()[contains(.,'type')]/following-sibling::span[1]");
	By by_AssignmentRow_Role = new By.ByXPath("./following-sibling::tr[1]//td/text()[contains(.,'role')]/following-sibling::span[1]");
	By by_AssignmentRow_Rate = new By.ByXPath("./following-sibling::tr[1]//td/text()[contains(.,'rate')]/following-sibling::span[2]");
	
	public Map<String,WebElement> getAssignmentComponents(String AccountName, String ProjectName) throws Exception{
	
		xpath_AssignmentRow = "//table[@class='contentTable']/tbody/tr[./td[./a/span[text()='"+ ProjectName +"']][./span[text()='"+ AccountName +"']]]";
		xpath_AssignmentRow_Project = "./td/a/span[text()='"+ ProjectName +"']";
		xpath_AssignmentRow_Account = "./td/span[text()='"+ AccountName +"']";
		
		
		by_AssignmentRow = new By.ByXPath(xpath_AssignmentRow);
		by_AssignmentRow_Project = new By.ByXPath(xpath_AssignmentRow_Project);
		by_AssignmentRow_Account = new By.ByXPath(xpath_AssignmentRow_Account);
		
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_AssignmentRow);
		WebElement we_AssignmentRow = ReturnWebElement("WebElement - Assignment Row", by_AssignmentRow, GlobalShortRetry,GlobalShortWait, expectedcondition);
		if(we_AssignmentRow!=null){
			WebElement we_Account = ReturnSubWebElement("WebElement Account - " + AccountName, we_AssignmentRow,by_AssignmentRow_Account,GlobalShortWait, GlobalShortRetry);
			WebElement we_Project = ReturnSubWebElement("WebElement Project - " + ProjectName, we_AssignmentRow,by_AssignmentRow_Project,GlobalShortWait, GlobalShortRetry);
			WebElement we_EditButton = ReturnSubWebElement("WebElement - Edit Button", we_AssignmentRow, by_AssignmentRow_EditButton, GlobalShortWait, GlobalShortRetry);
			WebElement we_StartDate = ReturnSubWebElement("WebElement - Start Date", we_AssignmentRow, by_AssignmentRow_StartDate, GlobalShortWait, GlobalShortRetry);
			WebElement we_EndDate = ReturnSubWebElement("WebElement - Start Date", we_AssignmentRow, by_AssignmentRow_EndDate, GlobalShortWait, GlobalShortRetry);
			WebElement we_AssignmentType = ReturnSubWebElement("WebElement - Type", we_AssignmentRow,by_AssignmentRow_AssignmentType,GlobalShortWait,GlobalShortRetry);
			WebElement we_Role = ReturnSubWebElement("WebElement - Role", we_AssignmentRow,by_AssignmentRow_Role,GlobalShortWait, GlobalShortRetry);
			WebElement we_Rate = ReturnSubWebElement("WebElement - Rate", we_AssignmentRow,by_AssignmentRow_Rate,GlobalShortWait, GlobalShortRetry);
			
			Map<String, WebElement> AssignmentComponents = new HashMap<String, WebElement>();
			AssignmentComponents.put("Account", we_Account);
			AssignmentComponents.put("Project", we_Project);
			AssignmentComponents.put("EditButton", we_EditButton);
			AssignmentComponents.put("StartDate", we_StartDate);
			AssignmentComponents.put("EndDate", we_EndDate);
			AssignmentComponents.put("AssignmentType", we_AssignmentType);
			AssignmentComponents.put("Role", we_Role);
			AssignmentComponents.put("Rate", we_Rate);
			return AssignmentComponents;
		}else{
			return null;
		}
		
	}
	
	public WebElement getAssignmentRowAccountName(String AccountName,String ProjectName) throws Exception{
		Map<String, WebElement> AssignmentComponents = getAssignmentComponents(AccountName, ProjectName);
		return AssignmentComponents.get("Account");
	}
	
	By by_Link_EditProjectAssignment = new By.ByXPath(".//a[./i[@class='fa fa-pencil-square-o']]");
	public WebElement getEditButtonForProjectAssignment(String AccountName, String ProjectName) throws Exception{
		Map<String, WebElement> AssignmentComponents = getAssignmentComponents(AccountName, ProjectName);
		return AssignmentComponents.get("EditButton");
	}
	
	public void DeleteProjectAssignment(String AccountName, String ProjectName) throws Exception{
		Map<String, WebElement> we_AssignmentComponents = getAssignmentComponents(AccountName,ProjectName);
		
		if(we_AssignmentComponents !=null){
			we_AssignmentComponents.get("EditButton").click();
			getButtonDelete().click();
			getDeleteAlert().accept();
			AssertTrue("Message - Data Saved", "Data saved", getError().getText(), getError().getText().equals("Data saved"));
		}
		
	}
	
	public void EditProjectAssignment(List<String> Row) throws Exception{
		
		String FirstName = Row.get(0); 
		String LastName = Row.get(1);
		String AccountName = Row.get(2);
		String ProjectName = Row.get(3);
		String AssignmentType = Row.get(4);
		String StartDate = Row.get(5);
		String EndDate = Row.get(6);
		String Role = Row.get(7);
		String HourlyRate = Row.get(8);
		String Active = Row.get(9);
		
		
		if(getAssignmentRowAccountName(AccountName, ProjectName)!=null){
			getEditButtonForProjectAssignment(AccountName, ProjectName).click();
			
			String Actual_AssignmentType = GetListBoxFirstSelectedElementText(getDropdownAssignmentType());
			String Actual_StartDate = IsCheckBoxChecked(getCheckBoxStartDate())==true?"true":"false";
			String Actual_EndDate = IsCheckBoxChecked(getCheckBoxEndDate())==true?"true":"false";
			String Actual_Role = GetTextBoxValue(getTextBoxRole());
			String Actual_HourlyRate = GetTextBoxValue(getTextBoxHourlyRate());
			String Actual_Active = IsCheckBoxChecked(getCheckBoxActive())==true?"true":"false";
			
			if(AssignmentType != Actual_AssignmentType){
				SelectAssignmentType(AssignmentType);
			}
			
			if(Role!=Actual_Role){
				getTextBoxRole().clear();
				getTextBoxRole().sendKeys(Role);
			}
			
			if(HourlyRate != Actual_HourlyRate){
				getTextBoxHourlyRate().clear();
				getTextBoxHourlyRate().sendKeys(HourlyRate);
			}
			
			if(Active != Actual_Active){
				CheckOrUncheckTheCheckBox(Boolean.valueOf(Active), getCheckBoxActive());
			}
		
			getButtonSave().click();
			AssertTrue("Message - Data Saved", "Data saved", getError().getText(), getError().getText().equals("Data saved"));
			Map<String, WebElement> AssignmentRow = getAssignmentComponents(AccountName, ProjectName);

			AssignmentRow.get("EditButton").click();
			
			
			AssertTrue("AssignmentType",AssignmentType,GetListBoxFirstSelectedElementText(getDropdownAssignmentType()), GetListBoxFirstSelectedElementText(getDropdownAssignmentType()).equals(AssignmentType) );
			AssertTrue("Role", Role, GetTextBoxValue(getTextBoxRole()),GetTextBoxValue(getTextBoxRole()).equals(Role) );
			AssertTrue("Rate", HourlyRate, GetTextBoxValue(getTextBoxHourlyRate()),GetTextBoxValue(getTextBoxHourlyRate()).equals(HourlyRate));

		}
		
	}
	
	
}
