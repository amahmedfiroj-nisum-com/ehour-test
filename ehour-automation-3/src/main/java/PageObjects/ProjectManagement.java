package PageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;


public class ProjectManagement extends Base{

	public ProjectManagement(String BrowserType) {
		super(BrowserType);
	}
	
	By by_TextBox_Name = new By.ByXPath("//input[@name='project.name']");
	By by_TextBox_ProjectCode = new By.ByXPath("//input[@name='project.projectCode']");
	By by_Dropdown_Client = new By.ByXPath("//select[@name='project.customer']");
	By by_Dropdown_ProjectManager = new By.ByXPath("//select[@name='project.projectManager']");
	By by_TextBox_Description = new By.ByXPath("//textarea[@name='project.description']");
	By by_TextBox_ContactPerson = new By.ByXPath("//input[@name='project.contact']");
	By by_CheckBox_DefaultProject = new By.ByXPath("//input[@type='checkbox'][@name='project.defaultProject']");
	By by_CheckBox_Billable = new By.ByXPath("//input[@type='checkbox'][@name='project.billable']");
	By by_CheckBox_Active = new By.ByXPath("//input[@type='checkbox'][@name='project.active']");
	
	public WebElement getTextBoxName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Name);
		return ReturnWebElement("Text Box - Name", by_TextBox_Name, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	public WebElement getTextBoxProjectCode() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_ProjectCode);
		return ReturnWebElement("Text Box - Project Code", by_TextBox_ProjectCode,GlobalLongWait,GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getDropdownClient() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Dropdown_Client);
		return ReturnWebElement("Dropdown - Client",by_Dropdown_Client,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getDropdownProjectManager() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Dropdown_ProjectManager);
		return ReturnWebElement("Dropdown - Project Manager", by_Dropdown_ProjectManager,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getTextBoxDescription() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Description);
		return ReturnWebElement("Text Box - Description", by_TextBox_Description, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getTextBoxContactPerson() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_ContactPerson);
		return ReturnWebElement("Text Box - Contact Person",by_TextBox_ContactPerson,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getCheckBoxActive() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_DefaultProject);
		return ReturnWebElement("Check Box - Default Project", by_CheckBox_DefaultProject, GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getCheckBoxBillable() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_Billable);
		return ReturnWebElement("Check Box - Billable", by_CheckBox_Billable,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public WebElement getCheckBoxDefaultProject() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_CheckBox_DefaultProject);
		return ReturnWebElement("Check Box - Default Project", by_CheckBox_DefaultProject,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	String xpath_TableRow_Project = "//table[@class='entrySelectorTable']/tbody//tr[@class='filterRow' or @class='selectedRow'][./td[1][text()='REPLACE_NAME']][./td[2][text()='REPLACE_CODE']]";
	By by_TableRow_Project;
	public WebElement getProjectRow(String ProjectName, String ProjectCode) throws Exception{
		by_TableRow_Project = new By.ByXPath(xpath_TableRow_Project.replace("REPLACE_NAME", ProjectName).replace("REPLACE_CODE", ProjectCode));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TableRow_Project);
		return ReturnWebElement("Table Row - Project Name" + ProjectName + " & Project Code" + ProjectCode, by_TableRow_Project,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public void addProject(List<String> DataTableRow) throws Exception{
		//|Name|ProjectCode|Client|ProjectManager|Description|ContactPerson|DefaultProject|Billable|Active|
		String ProjectName = DataTableRow.get(0);
		String ProjectCode = DataTableRow.get(1);
		String Client = DataTableRow.get(2);
		String ProjectManager = DataTableRow.get(3);
		String Description = DataTableRow.get(4);
		String ContactPerson = DataTableRow.get(5);
		String DefaultProject = DataTableRow.get(6);
		String Billable = DataTableRow.get(7);
		String Active = DataTableRow.get(8);
		
		if(getProjectRow(ProjectName, ProjectCode)==null){
			getTextBoxName().sendKeys(ProjectName);
			getTextBoxProjectCode().sendKeys(ProjectCode);
			SelectByVisibleText(getDropdownClient(), Client);
			SelectByVisibleText(getDropdownProjectManager(),ProjectManager);
			getTextBoxDescription().sendKeys(Description);
			getTextBoxContactPerson().sendKeys(ContactPerson);
			CheckOrUncheckTheCheckBox(DefaultProject.equals("true"), getCheckBoxDefaultProject());
			CheckOrUncheckTheCheckBox(Billable.equals("true"), getCheckBoxBillable());
			CheckOrUncheckTheCheckBox(Active.equals("true"), getCheckBoxDefaultProject());
			getButtonSave().click();
		}
		AssertTrue("Project Row", true, getProjectRow(ProjectName,ProjectCode)!=null, getProjectRow(ProjectName,ProjectCode)!=null);
	}
	
	
	
	public void EditProject(List<String> DataTableRow) throws Exception{
		String ProjectName = DataTableRow.get(0);
		String ProjectCode = DataTableRow.get(1);
		String Client = DataTableRow.get(2);
		String ProjectManager = DataTableRow.get(3);
		String Description = DataTableRow.get(4);
		String ContactPerson = DataTableRow.get(5);
		String DefaultProject = DataTableRow.get(6);
		String Billable = DataTableRow.get(7);
		String Active = DataTableRow.get(8);
		
		AssertTrue("Project Row", true, getProjectRow(ProjectName,ProjectCode)!=null, getProjectRow(ProjectName,ProjectCode)!=null);
		getProjectRow(ProjectName, ProjectCode).click();
		
		if(!(Client.length()==0||GetListBoxFirstSelectedElementText(getDropdownClient()).equals(Client))){
			SelectByVisibleText(getDropdownClient(), Client);
		}
		
		if(!(ProjectManager.length()==0||GetListBoxFirstSelectedElementText(getDropdownProjectManager()).equals(ProjectManager))){
			SelectByVisibleText(getDropdownProjectManager(), ProjectManager);
		}

		if(!(Description.length()==0||GetTextBoxValue(getTextBoxDescription()).equals(Description))){
			getTextBoxDescription().clear();
			getTextBoxDescription().sendKeys(Description);
		}
		if(!(ContactPerson.length()==0||GetTextBoxValue(getTextBoxContactPerson()).equals(ContactPerson))){
			getTextBoxContactPerson().clear();
			getTextBoxContactPerson().sendKeys(ContactPerson);
		}
		
		if(!(DefaultProject.length()==0||IsCheckBoxChecked(getCheckBoxDefaultProject())==DefaultProject.equals("true"))){
			CheckOrUncheckTheCheckBox(DefaultProject.equals("true"), getCheckBoxDefaultProject());
		}

		if(!(Billable.length()==0||IsCheckBoxChecked(getCheckBoxBillable())==Billable.equals("true"))){
			CheckOrUncheckTheCheckBox(Billable.equals("true"), getCheckBoxBillable());
		}
		
		if(!(Active.length()==0||IsCheckBoxChecked(getCheckBoxActive())==Active.equals("true"))){
			CheckOrUncheckTheCheckBox(Active.equals("true"), getCheckBoxActive());
		}
		
		getButtonSave().click();
		getError().getText().equals("Data saved");
		
		getProjectRow(ProjectName, ProjectCode).click();
		//Thread.sleep(2000);
		System.out.println(ProjectName);
		System.out.println(GetTextBoxValue(getTextBoxName()));
		AssertTrue("Text Box - Name", ProjectName, GetTextBoxValue(getTextBoxName()),GetTextBoxValue(getTextBoxName()).equals(ProjectName));
		AssertTrue("Text Box - Code", ProjectCode, GetTextBoxValue(getTextBoxProjectCode()),GetTextBoxValue(getTextBoxProjectCode()).equals(ProjectCode));
		AssertTrue("Dropdown - Client", Client, GetListBoxFirstSelectedElementText(getDropdownClient()),GetListBoxFirstSelectedElementText(getDropdownClient()).equals(Client));
		AssertTrue("Dropdown - ProjectManager", ProjectManager, GetListBoxFirstSelectedElementText(getDropdownProjectManager()),GetListBoxFirstSelectedElementText(getDropdownProjectManager()).equals(ProjectManager));
		AssertTrue("Text Box - Description", Description, GetTextBoxValue(getTextBoxDescription()),GetTextBoxValue(getTextBoxDescription()).equals(Description));
		AssertTrue("Text Box - ContactPerson", ContactPerson, GetTextBoxValue(getTextBoxContactPerson()),GetTextBoxValue(getTextBoxContactPerson()).equals(ContactPerson));
		AssertTrue("Check Box - Default Project", DefaultProject.equals("true"), IsCheckBoxChecked(getCheckBoxDefaultProject()),IsCheckBoxChecked(getCheckBoxDefaultProject())==DefaultProject.equals("true"));
		AssertTrue("Check Box - Billable", Billable.equals("true"), IsCheckBoxChecked(getCheckBoxBillable()),IsCheckBoxChecked(getCheckBoxBillable())==Billable.equals("true"));
		AssertTrue("Check Box - Active", Active.equals("true"), IsCheckBoxChecked(getCheckBoxActive()),IsCheckBoxChecked(getCheckBoxActive())==Active.equals("true"));
	}
	
	public void DeleteProject(List<String> DataTableRow) throws Exception{
		String ProjectName = DataTableRow.get(0);
		String ProjectCode = DataTableRow.get(1);

		AssertTrue("Project Row", "Not Null", getProjectRow(ProjectName, ProjectCode)==null?"Null":"Not Null", getProjectRow(ProjectName, ProjectCode)!=null);
		getProjectRow(ProjectName, ProjectCode).click();
		getButtonDelete().click();
		
		getDeleteAlert().accept();
		getError().getText().equals("Data saved");
		AssertTrue("Project Row", "Null", getProjectRow(ProjectName, ProjectCode)==null?"Null":"Not Null", getProjectRow(ProjectName, ProjectCode)==null);
	}
}
