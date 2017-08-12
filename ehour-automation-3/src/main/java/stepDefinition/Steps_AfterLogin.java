package stepDefinition;

import PageObjects.Base;
import PageObjects.Page_Header;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_AfterLogin {

	Page_Header h = new Page_Header(null);
	
	
	@When("^I select menu item \"([^\"]*)\"$")
	public void i_select_menu_item(String MenuItem) throws Throwable {
		h.SelectMenuItem(MenuItem);
		System.out.println("Selected Menu Item:"+ MenuItem);
	}
	
	@Then("^I should be able see \"([^\"]*)\" on Welcome Page$")
    public void I_should_be_able_see_on_Welcome_Page(String Name) throws Throwable {
        BaseSteps.AssertTrue("User Name on Welcome Page",Name, h.getLoggedInAs(),h.getLoggedInAs().equals(Name));
        System.out.println("Welcome Page Verification");
    }



    @When("^I logout from eHour$")
	public void i_logout_from_eHour() throws Throwable {
	    h.SignOut();
	    System.out.println("Log out of E-Hour");
	}



	@Then("^I am successfully logged out$")
	public void i_am_successfully_logged_out() throws Throwable {
	    h.VerifySignOut();
	    System.out.println("Successfully Logged out of E-Hour");
	}


	//below i added
	@When("^I click on Approve Timesheet$")
	public void I_click_on_Approve_Timesheet()throws Throwable{
    h.buttonApproveTimeSheet();
	}
   //below i added
	@When("^I click on approve Anchor$")
	public void i_click_on_approve_Anchor() throws Throwable {
		h.buttonApproveAnchor();
	}
    //below i added

	@When("^I click on reject Anchor$")
	public void i_click_on_reject_Anchor() throws Throwable {
		h.buttonRejectAnchor();
	}
}
