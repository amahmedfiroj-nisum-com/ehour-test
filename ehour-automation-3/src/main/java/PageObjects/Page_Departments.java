package PageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Page_Departments extends Base{

	public Page_Departments(String BrowserType) {
		super(BrowserType);
	}
	
	
	By by_Header = new By.ByXPath("//h1/span");
	public WebElement getHeaderOfPage() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Header);
		return ReturnWebElement("Header", by_Header,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_TextBox_Name= new By.ByXPath("//input[@name='department.name']");
	public WebElement getTextBoxName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Name);
		return ReturnWebElement("Text Box - Name", by_TextBox_Name, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_TextBox_Code = new By.ByXPath("//input[@name='department.code']");
	public WebElement getTextBoxCode() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Code);
		return ReturnWebElement("Text Box - Code", by_TextBox_Code,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	//below i added
	  By by_select_FunctionalManager=new By.ByXPath("//select[@name='department.manager']");
	  public WebElement getDropDownFunctionalManager() throws Exception{
		  expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_select_FunctionalManager);
		  return ReturnWebElement("DropDownBox - FunctionalManager",by_select_FunctionalManager,GlobalLongWait,GlobalShortRetry,expectedcondition);
	 }


//Harish

	By by_Func_Manager = new By.ByXPath("//input[@name='department.code']");
	public WebElement SelectFuncManager() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Func_Manager);
		return ReturnWebElement("Text Box - Code", by_Func_Manager,GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	//Harish


	String xpath_TableRow_Department = "//tbody//tr[@class='filterRow' or @class='selectedRow'][./td[1][text()='REPLACE_NAME']][./td[2][text()='REPLACE_CODE']]";
	By by_TableRow_Department;
	public WebElement getDepartmentRow(String DepartmentName, String DepartmentCode) throws Exception{
		by_TableRow_Department = new By.ByXPath(xpath_TableRow_Department.replace("REPLACE_CODE", DepartmentCode).replace("REPLACE_NAME", DepartmentName));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TableRow_Department);
		return ReturnWebElement("Department Row", by_TableRow_Department, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	//



	public void AddDepartment(List<String> TableRow) throws Exception{
		if(getDepartmentRow(TableRow.get(0),TableRow.get(1))==null){
			getTextBoxName().sendKeys(TableRow.get(0));
			getTextBoxCode().sendKeys(TableRow.get(1));
			//selectRandom();												//Harish
            //below i added
			getDropDownFunctionalManager().sendKeys(TableRow.get(2));
			getButtonSave().click();
			AssertTrue("Department Row with Name" + TableRow.get(0) + " & Code " + TableRow.get(1) , "Not Null",getDepartmentRow(TableRow.get(0),TableRow.get(1))==null? "Null":"Not Null" , getDepartmentRow(TableRow.get(0),TableRow.get(1))!=null);
		}
	}

	//Harish


	/*public void selectRandom()
	{
		WebElement drpDwnList = driver.findElement(By.xpath("//select[@name='department.manager'])"));
		Select objSel = new Select(drpDwnList);
		List <WebElement> weblist = objSel.getOptions();

		//Taking the count of items
		int iCnt = weblist.size();

		//Using Random class to generate random values
		Random num = new Random();
		int iSelect = num.nextInt(iCnt);

		//Selecting value from DropDownList
		objSel.selectByIndex(iSelect);

		//Selected Value
		System.out.println(drpDwnList.getAttribute("value"));

	}*/
	//Harish
	public void EditDepartment(List<String> TableRow) throws Exception{
		String OriginalName = TableRow.get(0);
		String OriginalCode = TableRow.get(1);
		String NewName = TableRow.get(2);
		String NewCode = TableRow.get(3);
        //below one i added
		String NewFunctionalManager=TableRow.get(4);
		
		getDepartmentRow(OriginalName, OriginalCode).click();
		
		getTextBoxName().clear();
		getTextBoxName().sendKeys(NewName);
		getTextBoxCode().clear();
		getTextBoxCode().sendKeys(NewCode);
	    //below one i added
		getDropDownFunctionalManager().sendKeys(NewFunctionalManager);
		getButtonSave().click();
		AssertTrue("Department Row with Name" + NewName + " & Code " + NewCode , "Not Null",getDepartmentRow(NewName,NewCode)==null? "Null":"Not Null" , getDepartmentRow(NewName, NewCode)!=null);
	}
	
	public void DeleteDepartment(List<String> s) throws Exception{
		String DepartmentName = s.get(0);
		String DepartmentCode = s.get(1);
		getDepartmentRow(DepartmentName, DepartmentCode).click();
		getButtonDelete().click();
		getDeleteAlert().accept();
		AssertTrue("Department Row with Name" + DepartmentName + " & Code " + DepartmentCode , "Null",getDepartmentRow(DepartmentName,DepartmentCode)==null? "Null":"Not Null" , getDepartmentRow(DepartmentName, DepartmentCode)==null);
	}
	

}
