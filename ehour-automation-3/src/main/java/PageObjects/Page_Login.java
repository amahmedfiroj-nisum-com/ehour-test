package PageObjects;

import java.beans.Visibility;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.remote.adapter.RemoteResultListener;

public class Page_Login extends Base{

	ArrayList<String> tabs;


	
	public Page_Login(String BrowserType) {
		super(BrowserType);
	}
	
	public void NavigateTo() throws IOException{
		String BaseURL = FetchPropertyValues("URL_Base");
		String LoginURI = FetchPropertyValues("URI_LoginPage");
		driver.get(BaseURL + LoginURI);
	}

	By by_username= new By.ByXPath(".//*[@id='username']");
	public WebElement getUserName() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_username);
		return ReturnWebElement("User Name", by_username, GlobalLongWait, GlobalShortRetry, expectedcondition);
		
	}
	By by_password= new By.ByXPath(".//*[@id='password']");
	public WebElement getPassword() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_password);
		return  ReturnWebElement("Password", by_password, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	By by_loginsubmit= new By.ByXPath(".//*[@id='loginSubmit']");
	public WebElement getSubmit() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_loginsubmit);
		return  ReturnWebElement("Submit", by_loginsubmit, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}
	
	
	
	public void Login(String UserName, String Password) throws Exception{
		getUserName().clear();
		getUserName().sendKeys(UserName);
		getPassword().clear();
		getPassword().sendKeys(Password);
		getSubmit().click();
	}

	public void NavigateToGoogle() throws IOException{
		driver.manage().deleteAllCookies();
		String GoogleURL = FetchPropertyValues("URL_Google");
		driver.get(GoogleURL);
	}
	public void GetTabsCount(){

		((JavascriptExecutor)driver).executeScript("window.open()");
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	
	//SSO implementation begins
	By by_Input_NisumUser = new By.ById("identifierId");
	By by_Button_Next = new By.ByXPath("//span[text()='Next']");
	By by_Input_NisumPassword = new By.ByName("password");
	By by_Button_GooglePlus = new By.ByXPath("//div[@class='google-signIn']/a/i");
	By by_Email;
	By by_Button_Compose = new By.ByXPath("//div[text()='COMPOSE']");
	By by_Link_SignIn = new By.ByXPath("//a[text()='Sign in']");
	By by_Error = new By.ByXPath("//span[@class='errorlevel feedbackPanelERROR']");
	//By by_Link_SSO_GoogleAccount = new By.ByXPath("//span[@class='gb_8a gbii']");
	By by_Link_SSO_GoogleAccount = new By.ByCssSelector("span[class$='gbii']");
	//By by_Link_SSO_SignOut = new By.ByXPath("//a[@id='gb_71']");
	By by_Link_SSO_SignOut = new By.ByCssSelector("a[id^='gb_']");

	public WebElement getInputNisumUser() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_NisumUser);
		return ReturnWebElement("Nisum User", by_Input_NisumUser, GlobalLongWait, GlobalShortRetry, expectedcondition); // driver.findElement(By.id("identifierId"));
	}
	

	public WebElement getButtonNext() throws Exception {
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Next);
		return ReturnWebElement("Next Button", by_Button_Next, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	public WebElement getInputNisumPwd() throws Exception 
	{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Input_NisumPassword);
		return ReturnWebElement("Password", by_Input_NisumPassword, GlobalLongWait, GlobalShortRetry, expectedcondition);
	}

	//public WebElement pwdNext() {return driver.findElement(By.xpath("//span[text()='Next']"));}

	public WebElement getButtonGooglePlus() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_GooglePlus);
		return ReturnWebElement("Button - Google Plus", by_Button_GooglePlus, GlobalLongWait, GlobalShortRetry,expectedcondition);
	}

	String xpath_Email = "//div[./div/p[text()='REPLACE_EMAIL']]";
	
	public WebElement getLabelEmail(String Email) throws Exception{
		by_Email = new By.ByXPath(xpath_Email.replace("REPLACE_EMAIL", Email));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Email);
		return ReturnWebElement("User Email",by_Email,GlobalShortWait, GlobalLongWait,expectedcondition);
	}

	public WebElement getButtonCompose() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Button_Compose);
		return ReturnWebElement("Compose", by_Button_Compose,GlobalLongWait,GlobalShortRetry, expectedcondition);
	}

	//	public WebElement  getSignout(){return driver.findElement(By.xpath("//div[@class='LoggedInAs']/a[2])"));}

	public WebElement getButtonSignIn() throws Exception{
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Link_SignIn);
		return ReturnWebElement("Sign In", by_Link_SignIn,GlobalLongWait, GlobalShortRetry, expectedcondition);
		
	}

//	public WebElement getError(){return driver.findElement(By.xpath("//span[text()='incorrect username and password combination']"));}
	
	String xpath_SignedInGoogle = "//a[contains(@title,'REPLACE_EMAIL')]";
	
	By by_Link_SignedInGoogle;  
	public WebElement getLinkSignedInGoogle(String Email) throws Exception{
		by_Link_SignedInGoogle = new By.ByXPath(xpath_SignedInGoogle.replace("REPLACE_EMAIL", Email));
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Link_SignedInGoogle);
		return ReturnWebElement("Signed In as Google User Name" + Email, by_Link_SignedInGoogle,GlobalLongWait,GlobalShortRetry,expectedcondition);
	}
	
	public void SignInToGoogle(String UN, String Pwd ) throws Exception {
		getButtonSignIn().click();
		
		if (getInputNisumUser() == null && getInputNisumPwd()!=null){
			getInputNisumPwd().sendKeys(Pwd);
			getButtonNext().click();
		}else{
			getInputNisumUser().sendKeys(UN);
			getButtonNext().click();
			getInputNisumPwd().sendKeys(Pwd);
			getButtonNext().click();
		}
		AssertTrue("Signed in Google as Username" + UN, true , getLinkSignedInGoogle(UN).isDisplayed(), getLinkSignedInGoogle(UN).isDisplayed() == true);
	}
	
	public void SSOLogin(String Email) throws Exception{
		getButtonGooglePlus().click();
		SwitchToWindow("Sign in - Google Accounts");
		getLabelEmail(Email).click();
		Thread.sleep(5000);
		SwitchToWindow("eHour - month overview");
	}

	
	public WebElement getErrorMessage() throws Exception{
		//Thread.sleep(5000);
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Error);
		return ReturnWebElement("Error", by_Error, GlobalLongWait,GlobalShortRetry,expectedcondition);
	}

	public WebElement getSSOGoogleAccount() throws Exception {
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Link_SSO_GoogleAccount);
		return ReturnWebElement("Link - SSO Google Account", by_Link_SSO_GoogleAccount, GlobalLongWait, GlobalShortRetry, expectedcondition);

	}

	public WebElement getSSOSignOut() throws Exception {
		expectedcondition=ExpectedConditions.visibilityOfElementLocated(by_Link_SSO_SignOut);
		return ReturnWebElement("Link - SSO Sign Out", by_Link_SSO_SignOut, GlobalLongWait, GlobalShortRetry, expectedcondition);

	}

	public void SSOSignOut() throws Exception{
		getSSOGoogleAccount().click();
		getSSOSignOut().click();
	}



	public void CloseDriver(){
		driver.switchTo().window(tabs.get(0)).close();
	}
	
}
