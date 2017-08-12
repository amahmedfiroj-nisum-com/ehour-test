package PageObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
//import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Base{
	
	public static WebDriver driver;
	public static WebElement element;
	public static Integer GlobalExtraLongWait;
	public static Integer GlobalLongWait;
	public static Integer GlobalShortWait;
	public static Integer GlobalNoWait;
	public static Integer GlobalStalenessWait;
	public static Integer GlobalLongRetry;
	public static Integer GlobalShortRetry;
	public static Integer GlobalNoRetry;
	public static String OS;
	public static String GridHubURL;
	public static ExpectedCondition<WebElement> expectedcondition;
	static DesiredCapabilities capabilities;
	
	public Base(String BrowserType) {
		
		//This ensures the driver is not reinitialized unnecessarily.
		if(driver !=null ){
			return;
		}else{
		
			//Initialize configuration variables.
			try{
				GlobalExtraLongWait = Integer.parseInt(FetchPropertyValues("GlobalExtraLongWait"));
				GlobalLongWait = Integer.parseInt(FetchPropertyValues("GlobalLongWait"));
				GlobalShortWait = Integer.parseInt(FetchPropertyValues("GlobalShortWait"));
				GlobalNoWait = Integer.parseInt(FetchPropertyValues("GlobalNoWait"));
				GlobalStalenessWait = Integer.parseInt(FetchPropertyValues("GlobalStalenessWait"));
				GlobalLongRetry = Integer.parseInt(FetchPropertyValues("GlobalLongRetry"));
				GlobalShortRetry = Integer.parseInt(FetchPropertyValues("GlobalShortRetry"));
				GlobalNoRetry = Integer.parseInt(FetchPropertyValues("GlobalNoRetry"));
				GridHubURL = FetchPropertyValues("URL_GridHub");
			}catch(Exception e){
				System.out.println(e.getMessage());
				GlobalExtraLongWait = 10;
				GlobalLongWait = 5;
				GlobalShortWait = 2;
				GlobalNoWait = 0;
				GlobalStalenessWait = 3;
				GlobalLongRetry = 5;
				GlobalShortRetry=2;
				GlobalNoRetry=0;
				GridHubURL = "http://localhost:4444/wd/hub";
			}
	
			//Selecting the driver binary according to the OS.
			OS = System.getProperty("os.name");
			System.out.println(OS);
			if (OS.contains("Mac")){	
				System.setProperty("webdriver.chrome.driver", "Dependencies/chromedriver_mac");
				System.setProperty("webdriver.gecko.driver", "Dependencies/geckodriver_mac");
			}else if(OS.contains("Windows")){
				System.setProperty("webdriver.chrome.driver", "Dependencies/chromedriver.exe");
				System.setProperty("webdriver.gecko.driver", "Dependencies/geckodriver.exe");
			}else if(OS.contains("Linux")){
				//System.setProperty("webdriver.chrome.driver", "Dependencies/chromedriver_linux");
				//System.setProperty("webdriver.gecko.driver", "Dependencies/geckodriver_linux");
			}
		
			//Select browser specific drivers.
			if(BrowserType.equalsIgnoreCase("chrome")){
				ChromeOptions chromeOptions = new ChromeOptions();
				if(OS.contains("Windows")){
					chromeOptions.addArguments("--start-maximized");
				}else{
					chromeOptions.addArguments("--start-fullscreen");
				}
					
				
				//chromeOptions.addArguments("--disable-popup-blocking");
				chromeOptions.addArguments("disable-popup-blocking");
				//chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
				//chromeOptions.addArguments("--headless");
				//chromeOptions.addArguments("--disable-gpu");
				//chromeOptions.addArguments("--remote-debugging-port=9222");
				//chromeOptions.addArguments("--window-size=1260x768");
				//this.driver = new ChromeDriver();
				this.driver = new ChromeDriver(chromeOptions);
			}else if(BrowserType.equalsIgnoreCase("chrome-headless")){
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("start-maximized");
				//chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				//chromeOptions.addArguments("--remote-debugging-port=9222");
				//chromeOptions.addArguments("--window-size=1260x768");
				this.driver = new ChromeDriver(chromeOptions);
			}else if(BrowserType.equalsIgnoreCase("firefox")){
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("acceptInsecureCerts", true);
				capabilities.setCapability(FirefoxDriver.MARIONETTE, true);
				this.driver = new FirefoxDriver(capabilities);
			}else if(BrowserType.equalsIgnoreCase("safari")){
				this.driver = new SafariDriver();
			}else if(BrowserType.equalsIgnoreCase("phantomjs-headless")){
				capabilities = DesiredCapabilities.phantomjs();
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("acceptInsecureCerts", true);
				capabilities.setCapability("phantomjs.binary.path", "Dependencies/phantomjs_mac");
				this.driver = new PhantomJSDriver(capabilities);
			}else if(BrowserType.equalsIgnoreCase("remote-chrome")){
				ChromeOptions chromeOptions = new ChromeOptions();
				if(OS.contains("Windows")){
					chromeOptions.addArguments("--start-maximized");
				}else{
					chromeOptions.addArguments("--start-fullscreen");
				}
				chromeOptions.addArguments("disable-popup-blocking");
				capabilities= DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				try{
					this.driver = new RemoteWebDriver(new URL(GridHubURL), capabilities);
				}catch(Exception e){
					System.out.println("Exception initializing the remote web driver");
				}
		        
			}
			this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	By by_Table_Records = new ByXPath("//div[@id='listContents']/table[@class='entrySelectorTable']");
	By by_Table_Records_DisplayedRows = new By.ByXPath(".//tbody/tr[@class='filterRow' and (not(@style) or @style='display: table-row;')]");
	//below i changed xpath
	By by_Error = new By.ByXPath("//span[@class='formValidationError'][text()='Data saved']");
	By by_TextBox_Filter = new By.ById("listFilter");
	By by_Button_Save = new By.ByName("submitButton");
	By by_Button_Delete = new By.ByXPath("//a[@class='button'][./span[text()='delete']]");
	By by_TableOfRecords_Rows = new By.ByXPath(".//tbody/tr[@class='filterRow']");
	
	

	public WebElement getTextBoxFilter() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_TextBox_Filter);
		return ReturnWebElement("TextBox - Filter", by_TextBox_Filter, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	
	public List<WebElement> getAllRows() throws Exception{
		return ReturnSubWebElements("Table - Rows", by_Table_Records, by_TableOfRecords_Rows, GlobalLongWait, GlobalShortRetry);
	}

	public List<WebElement> getDisplayedFilteredRows() throws Exception{
		return ReturnSubWebElements("Table - Filtered Displayed Rows",by_Table_Records, by_Table_Records_DisplayedRows,GlobalLongWait,GlobalShortRetry);
	}
	
	public WebElement getTableOfRecords() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Table_Records);
		return ReturnWebElement("Table of Existing Records", by_Table_Records, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getButtonSave() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Save);
		return ReturnWebElement("Button - Save or Lock", by_Button_Save, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	public WebElement getButtonDelete() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Delete);
		return ReturnWebElement("Button Delete", by_Button_Delete, GlobalLongWait,GlobalShortRetry,expectedcondition); 
	}

	public WebElement getError() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error", by_Error,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}	
	
	
	public void SwitchToWindow(String WindowTitle){
		try{
			Thread.sleep(2000);
			Set<String> s = driver.getWindowHandles();
			Iterator i = s.iterator();
			while(i.hasNext()){
				if(driver.switchTo().window(i.next().toString()).getTitle().equalsIgnoreCase(WindowTitle)){
					break;
				}
			}
					
			//driver.switchTo().window(WindowTitle);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public void CloseWindow(String WindowTitle){
		try{
			driver.switchTo().window(WindowTitle).close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public Alert getDeleteAlert(){
		Alert a = (new WebDriverWait(driver, GlobalLongWait).until(ExpectedConditions.alertIsPresent()));
		System.out.println( a.getText());
		return a;
	}
	
	public void SearchRecords(String SearchString) throws Exception{
		getTextBoxFilter().clear();
		getTextBoxFilter().sendKeys(SearchString);
	}
	
	public void MoveToElement(WebElement e){
		try{
			Actions a = new Actions(driver);
			a.moveToElement(e).build().perform();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void JSclearField(WebElement e){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '')",e);
	}
	
	public WebElement GetListBoxFirstSelectedElement(WebElement ListBox){
		return new Select(ListBox).getFirstSelectedOption();
	}
	
	public String GetListBoxFirstSelectedElementText(WebElement ListBox){
		return new Select(ListBox).getFirstSelectedOption().getText();
	}
	
	public boolean VerifyListBoxSelectedValues(WebElement ListBox, String ExpectedSelection){
		List<WebElement> li_SelectedValues = new Select(ListBox).getAllSelectedOptions();
		
		String[] ExpectedSelectionArray = ExpectedSelection.split(",");
		for(WebElement we: li_SelectedValues){
			if(!Arrays.asList(ExpectedSelectionArray).contains(we.getText())){
				return false;
			}
		}
		return true;
	}
	
	
	public boolean IsCheckBoxChecked(WebElement e){
		Boolean ReturnValue = false;
		if(e.getAttribute("checked")==null || !e.getAttribute("checked").equals("true")){
			ReturnValue = false;
		}else if(e.getAttribute("checked").equals("true")){
			ReturnValue = true;
		}
		return ReturnValue;
	}
	
	public String GetTextBoxValue(WebElement e){
		return e.getAttribute("value");
	}
	
	public void AssertTrue(String FieldName, Object ExpectedValue, Object ActualValue, Boolean Condition) throws InterruptedException{
		//Assert.assertTrue(FieldName + ": Expected Value is " + ExpectedValue + ". Actual Value is " + ActualValue, Condition);
		System.out.println("Expected Value: " + ExpectedValue.toString());
		System.out.println("Actual Value:" + ActualValue.toString());
		//Thread.sleep(5000);
		Assert.assertTrue(Condition, FieldName + ": Expected Value is " + ExpectedValue!=null || ExpectedValue.toString().length() > 0?ExpectedValue.toString():"NULL / Blank" + ". Actual Value is " + ActualValue!=null || ActualValue.toString().length() > 0?ActualValue.toString():"NULL / Blank");
	}
	
	public Date GetFirstDateOfCurrentWeek() throws ParseException{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}
	
	public String ConvertDateToString(Date date, String ExpectedDateFormat){
		DateFormat df = new SimpleDateFormat(ExpectedDateFormat);
		return df.format(date);
	}
	
	public Date GetLastDateOfCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}
	
	public Date ConvertStringToDate(String DateString, String ExpectedDateFormat) throws ParseException{
		DateFormat df = new SimpleDateFormat(ExpectedDateFormat);
		return df.parse(DateString);
	}
	
	public boolean CompareDates(Date Date1, Date Date2){
		return Date1.equals(Date2);
	}
	
	public boolean IsDateBetweenTwoDates(Date date1, Date date2, Date GivenDate){
		if ( (GivenDate.after(date1) || GivenDate.equals(date1) ) && (GivenDate.before(date2)) || GivenDate.equals(date2)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void CheckOrUncheckTheCheckBox(Boolean Check,WebElement e){
		if (Check.equals(false)){
			if(IsCheckBoxChecked(e)){
				e.click();
			}
		}else{
			if(!IsCheckBoxChecked(e)){
				e.click();
			}
		}
	}
	
	public void SelectByVisibleText(WebElement Select,String SelectOption){
		new Select(Select).selectByVisibleText(SelectOption);
	}
	
	public static WebElement ReturnWebElement(String FriendlyElementName, By by,Integer WaitForSeconds,Integer RetryCount, ExpectedCondition<WebElement> expectedCondition) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, WaitForSeconds);
		WebDriverWait StalenessWait = new WebDriverWait(driver,GlobalStalenessWait);
		WebElement we = null;
		
		for(Integer i=0;i<RetryCount;i++){
			try{
				if (expectedCondition == null){
					if(WaitForSeconds==0){
						we = driver.findElement(by);
						System.out.println(we.getTagName());
						break;
					}else{
						we = wait.until(ExpectedConditions.presenceOfElementLocated(by));
						System.out.println(we.getTagName());
						break;
					}
				}else{
					we = wait.until(expectedCondition);
					System.out.println(we.getTagName());
					break;
				}
				
			}catch(Exception e){
				System.out.println("Error finding the element " + "'" + FriendlyElementName + "'" + e.getMessage());
			}
			
		}
		if(we!=null){
			try{
				StalenessWait.until(ExpectedConditions.stalenessOf(we));
				we = driver.findElement(by);
			}catch(Exception e){
				//System.out.println("Inside final catch for stale exception");// + e.getMessage());
			}
		}
		return we;
	}

	public static WebElement ReturnSubWebElement(String FriendlyElementName, WebElement ParentElement, By ChildElementBy, Integer WaitForSeconds,Integer RetryCount) throws Exception{
		WebElement we = null;
		WebDriverWait wait = new WebDriverWait(driver, WaitForSeconds);
		for(Integer i=0;i<RetryCount;i++){
			try{
				if(WaitForSeconds==0){
					return(ParentElement.findElement(ChildElementBy));	
				}else{
					return(wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(ParentElement, ChildElementBy)));	
				}
			}catch(Exception e){
					System.out.println("Error finding the element" + "'" + FriendlyElementName + "'" );
					continue;
			}
		}
		return we;
	}

	public static List<WebElement> ReturnSubWebElements(String FriendlyElementName, By byParentElement, By byChildElement, Integer WaitForSeconds, Integer RetryCount) throws Exception{
		List<WebElement> li = null;
		for(int i=0;i<RetryCount;i++){
			Long StartTime = GetTimeinMs();
			WhileLoop:
			while(!((GetTimeinMs() - StartTime) >= WaitForSeconds * 1000)){
				try{
					WebElement ParentElement = ReturnWebElement("Parent", byParentElement, WaitForSeconds, 3,null);
					li = ParentElement.findElements(byChildElement);
					if( li.size()==0 || li == null){
						System.out.println("Size of List of Sub Web Elements is Zero, hence retrying");
						continue WhileLoop;
					}else{
						System.out.println("Size of List - " + li.size());
						return li;
					}
				}catch(Exception e){
					System.out.println("Error finding - " + FriendlyElementName + " due to error " + e.getMessage());
				}
			}
		}
		return li;
	}

	public static List<WebElement> ReturnWebElements(String FriendlyElementName, By by,Integer WaitForSeconds, Integer RetryCount) throws Exception{
		List<WebElement> we = null;
		WebDriverWait wait = new WebDriverWait(driver, WaitForSeconds);
		for(Integer i=0;i<RetryCount;i++){
			if (WaitForSeconds == 0 ){
				we = driver.findElements(by);
				System.out.println(we.size());
			}else{
				we = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((by)));
			}
			if (we.size()!=0){
				return we;
			}
		}
		return we;
	}

	public static Long GetTimeinMs(){
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static String GetDateTimeInYYYYMMDDHHMMSS(){
		//return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
	
	public static int GetIntFromString(String StringValue){
		String temp =StringValue.replaceAll("[^0-9]", ""); 
		if(temp.length() > 0){
			return Integer.parseInt(temp);
		}else{
			return 0;
		}	
	}

	public String FetchPropertyValues(String PropertyKey) throws IOException{
		System.out.println(new File(".").getCanonicalPath());
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("config.properties");
		if (is != null){
			prop.load(is);
		}else{
			throw new FileNotFoundException("Property file couldn't be found");
		}
		return prop.getProperty(PropertyKey);
	}	
}
