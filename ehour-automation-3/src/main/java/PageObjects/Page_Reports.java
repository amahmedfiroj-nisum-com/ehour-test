package PageObjects;

import org.openqa.selenium.By;

public class Page_Reports {

	By by_Date_ReportFrom = new By.ByXPath("//input[@name='reportCriteria.userSelectedCriteria.reportRange.dateStart']");
	By by_Date_ReportTo = new By.ByXPath("//input[@name='reportCriteria.userSelectedCriteria.reportRange.dateEnd']");
	By by_Select_Week = new By.ByXPath("//select[@name='quickWeek']");
	By by_Select_Month = new By.ByXPath("//select[@name='quickMonth']");
	By by_Select_Quarter = new By.ByXPath("//select[@name='quickQuarter']");
	
	By by_Select_Clients = new By.ByXPath("//select[@id='customerSelect']");
	By by_CheckBox_Clients_Active = new By.ByXPath("//input[@type='checkbox'][@name='customerProjectsBorder:customerProjectsBorder_body:reportCriteria.userSelectedCriteria.onlyActiveCustomers']");
	
	By by_Select_Projects = new By.ByXPath("//select[@id='projectSelect']");
	By by_CheckBox_Projects_Active = new By.ByXPath("//input[@type='checkbox'][@name='customerProjectsBorder:customerProjectsBorder_body:reportCriteria.userSelectedCriteria.onlyActiveProjects']");
	By by_CheckBox_Projects_Billable = new By.ByXPath("//input[@type='checkbox'][@name='customerProjectsBorder:customerProjectsBorder_body:reportCriteria.userSelectedCriteria.onlyBillableProjects']");
	
	By by_Select_FunctionalGroup = new By.ByXPath("//select[@id='departmentSelect']");
	
	By by_Select_User = new By.ByXPath("//select[@id='userSelect']");
	By by_CheckBox_User_Active = new By.ByXPath("//input[@type='checkbox'][@name='userDepartmentPlaceholder:deptUserBorder:deptUserBorder_body:reportCriteria.userSelectedCriteria.onlyActiveUsers']");
	By by_CheckBox_User_ContractorsOnly = new By.ByXPath("//input[@type='checkbox'][@name='userDepartmentPlaceholder:deptUserBorder:deptUserBorder_body:reportCriteria.userSelectedCriteria.onlyContractors']");
	
	
	
	
}
