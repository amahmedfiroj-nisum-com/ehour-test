package stepDefinition;

import PageObjects.Base;
import PageObjects.Page_Login;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class Steps_Login{
	
	Page_Login lp;
	
	
	
	@Given("^Open \"([^\"]*)\" and start application$")
	public void OpenWebPageInBrowser(String browsername) throws Throwable {
	    lp = new Page_Login(browsername);
	    lp.NavigateTo();
	    System.out.println("Launched Browser");
	    
	}
	
	@When("^I enter valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Login(String username, String password) throws Throwable {
	    lp.Login(username,password);
	    System.out.println("Logged into E-hour Application");
	}


	@When("^I enter valid username \"([^\"]*)\" and  password \"([^\"]*)\"$")
	public void NisumLogin(String un, String pwd) throws Exception {
		lp.SignInToGoogle(un, pwd);
	}

	@Then("^user should see \"([^\"]*)\"$")
	public void userShouldSee(String element) throws Throwable {
	}



	@When("^I Open new tab and launch ehour$")
	public void LaunchEhour() throws Throwable {
		lp.NavigateTo();
	}

	@Then("^user can see and click Google\\+ sign$")
	public void UserClicksGooglesign() throws Throwable {
		

	}

	@Then("^click on loggedin user email$")
	public void click_on_loggedin_user_email() throws Throwable {

	}

	@Then("^user should click on Sign In$")
	public void user_should_click_on_Sign_In() throws Throwable {
		
	}



	
	@Then("^user should see \"([^\"]*)\" on ehour login page$")
	public void userShouldSeeOnEhourLoginPage(String error) throws Throwable {
		
	}
	
	
	@Then("^I should see the error \"([^\"]*)\"$")
	public void i_should_see_the_error(String error) throws Throwable {
		BaseSteps.AssertTrue("Error Text",error, lp.getErrorMessage().getText(),lp.getErrorMessage().getText().equalsIgnoreCase(error));
	}

	
//SSO steps start
	
	
	
	
	@Given("^Open \"([^\"]*)\" and launch google$")
	public void open_and_launch_google(String BrowserName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		lp = new Page_Login(BrowserName);
	    lp.NavigateToGoogle();
	}
	
	@When("^I login to google with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_to_google_with_username_and_password(String username, String password) throws Throwable {
		lp.SignInToGoogle(username, password);
	}
	
	@When("^I load eHour Application Home Page$")
	public void i_load_eHour_Application_Home_Page() throws Throwable {
		lp.NavigateTo();
	}
	
	@When("^I login to eHour using Single Signon as \"([^\"]*)\"$")
	public void i_login_to_eHour_using_Single_Signon_as(String username) throws Throwable {
		lp.SSOLogin(username);
	}


	@And("^I am successfully logged out from Google$")
	public void iAmSuccessfullyLoggedOutFromGoogle() throws Throwable {
		lp.SSOSignOut();
	}
	
	@Then("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
		Base.driver.close();
		Base.driver = null;
	}
}
