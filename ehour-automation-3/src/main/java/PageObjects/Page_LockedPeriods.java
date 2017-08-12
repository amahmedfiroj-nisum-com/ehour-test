package PageObjects;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

public class Page_LockedPeriods extends Base {
	
	public Page_LockedPeriods(String BrowserType) {
		super(BrowserType);
	}

	
	By by_Error = new By.ByXPath("//span[@class='formValidationError']");
	public WebElement getError() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error Message upon save or delete", by_Error,GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	By by_TableOfRecords_Rows_DateFrom = new By.ByXPath("./td[2]");
	public WebElement getTableRowsDateFromColumn(WebElement Row) throws Exception{
		return ReturnSubWebElement("Column - Date From", Row, by_TableOfRecords_Rows_DateFrom, GlobalShortWait, GlobalShortRetry);
	}
	
	By by_TableOfRecords_Rows_DateTo = new By.ByXPath("./td[3]");
	public WebElement getTableRowsDateToColumn(WebElement Row) throws Exception{
		return ReturnSubWebElement("Column - Date To", Row, by_TableOfRecords_Rows_DateTo, GlobalShortWait, GlobalShortRetry);
	}
	
	By by_TableOfRecords_Rows_LockedPeriodName = new By.ByXPath("./td[1]");
	public WebElement getTableRowsLockedPeriodName(WebElement Row) throws Exception{
		return ReturnSubWebElement("Column - Date To", Row, by_TableOfRecords_Rows_LockedPeriodName, GlobalShortWait, GlobalShortRetry);
	}
	
	By by_Input_DateFrom = new By.ByXPath("//input[@name='startDate']");
	public WebElement getInputDateFrom() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_DateFrom);
		return ReturnWebElement("Text Box - Date From", by_Input_DateFrom, GlobalLongWait, GlobalShortRetry,expectedcondition);
	}
	
	By by_Input_DateTo = new By.ByXPath("//input[@name='endDate']");
	public WebElement getInputDateTo() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_DateTo);
		return ReturnWebElement("Text Box - Date To", by_Input_DateTo, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_Input_LockedPeriodName = new By.ByXPath("//input[@name='p::name']");
	public WebElement getInputLockedPeriodName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_LockedPeriodName);
		WebElement we = ReturnWebElement("Text Box - Locked Period Name",by_Input_LockedPeriodName,GlobalLongWait, GlobalShortRetry, expectedcondition); 
		return we;
	}

	
	
	
	
	public void CreateLockedPeriod(Date StartDate, Date EndDate, String LockedPeriodName) throws Exception{
		String DateFrom,DateTo;
		DateFrom = ConvertDateToString(StartDate, "M/d/yy");
		DateTo = ConvertDateToString(EndDate, "M/d/yy");
		
		getInputDateFrom().clear();
		getInputDateFrom().sendKeys(DateFrom + Keys.TAB);
		
		getInputDateTo().clear();
		getInputDateTo().sendKeys(DateTo + Keys.TAB);
		
		
		getInputLockedPeriodName().clear();
		getInputLockedPeriodName().sendKeys(LockedPeriodName );
		
		getButtonLock().click();
		Assert.assertTrue(getError().getText().equals("Data saved"));
		getTextBoxFilter().sendKeys(LockedPeriodName);
		
		Assert.assertTrue(getDisplayedFilteredRows().size() == 1);
		
		getDisplayedFilteredRows().get(0).click();
		//Thread.sleep(3000);
		Assert.assertTrue(GetTextBoxValue(getInputDateFrom()).equals(DateFrom));
		Assert.assertTrue(GetTextBoxValue(getInputDateTo()).equals(DateTo));
		Assert.assertTrue(GetTextBoxValue(getInputLockedPeriodName()).equals(LockedPeriodName));
	}
	
	
	
	public void SelectLockedPeriod(Date StartDate, Date EndDate, String LockedPeriodName) throws ParseException, Exception{
		List<WebElement> li_AllRows = getAllRows();
		System.out.println("The size of list is" + li_AllRows.size());
		InnerFor:
		for(WebElement we: li_AllRows){
			Date ActualDateFrom = ConvertStringToDate(getTableRowsDateFromColumn(we).getText(),"dd MMM yy");
			Date ActualDateTo = ConvertStringToDate(getTableRowsDateToColumn(we).getText(),"dd MMM yy");
			if(ActualDateFrom.equals(StartDate) && ActualDateTo.equals(EndDate) && getTableRowsLockedPeriodName(we).equals(LockedPeriodName)){
				MoveToElement(we);
				we.click();
			}
		}
	}
	
	public void SearchLockedPeriods(Date StartDate, Date EndDate) throws ParseException, Exception{
		OuterWhile:
		while(true){
			Boolean IsRecordFound = false;
			
			
			List<WebElement> li_AllRows = getAllRows();
			System.out.println("The size of list is" + li_AllRows.size());
			if(li_AllRows.size()==0){
				break OuterWhile;
			}
			InnerFor:
			for(WebElement we: li_AllRows){
				Date DateFrom = ConvertStringToDate(getTableRowsDateFromColumn(we).getText(),"dd MMM yy");
				Date DateTo = ConvertStringToDate(getTableRowsDateToColumn(we).getText(),"dd MMM yy");
				if(IsDateBetweenTwoDates(DateFrom, DateTo, StartDate) || IsDateBetweenTwoDates(DateFrom, DateTo, EndDate)){
					System.out.println(DateFrom + "..." + DateTo);
					MoveToElement(we);
					we.click();
					DeleteSelectedPeriod();
					IsRecordFound = true;
					break InnerFor;
				}
			}
			if (IsRecordFound == false){
				break;
			}else{
				Thread.sleep(15000);
			}
			
		}
	}
	
	
	By by_Button_Lock = new By.ByXPath("//a[@class='button floatRight'][./span[text()='Lock']]");

	
	public WebElement getButtonLock() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Lock);
		return ReturnWebElement("Button - Lock", by_Button_Lock, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	By by_Button_Unlock = new By.ByXPath("//a[@class='button floatLeft'][./span[text()='Unlock']]");
	public WebElement getButtonUnlock() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Unlock);
		return ReturnWebElement("Button - Unlock", by_Button_Unlock, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	
	public void DeleteSelectedPeriod() throws Exception{
		getButtonUnlock().click();
		getDeleteAlert().accept();
	}
}
