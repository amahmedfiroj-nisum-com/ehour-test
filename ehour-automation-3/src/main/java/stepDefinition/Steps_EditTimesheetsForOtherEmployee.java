package stepDefinition;

import PageObjects.Page_Client;
import PageObjects.Page_Header;
import PageObjects.Page_Timesheet;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;

public class Steps_EditTimesheetsForOtherEmployee {

	
	Page_Timesheet pd = new Page_Timesheet(null);
	Page_Client pc = new Page_Client("Anything");
	Page_Header h = new Page_Header("anything");
	
	@Then("^I select a user \"([^\"]*)\" to edit the timesheet$")
	public void i_select_a_user_to_edit_the_timesheet(String ClientName) throws Throwable {
		pc.getMyClient(ClientName);
		
	}
	@Then("^I click on Temporarily sign in and check if i logged in temporarily to that user i should see \"([^\"]*)\"$")
	public void i_click_on_Temporarily_sign_in_and_check_if_i_logged_in_temporarily_to_that_user_i_should_see(String Name) throws Throwable {
		if(pd.TemporarySignIn()) {
			BaseSteps.AssertTrue("User Name on Welcome Page",Name, h.getLoggedInAs(),h.getLoggedInAs().equals(Name));
		}
		
	}

	@Then("^I fill the timesheet of the user as the following$")
	public void i_fill_the_timesheet_of_the_user_as_the_following(DataTable bc) throws Throwable {

		pd.EditTimeSheetData(bc);
	}
	
	@Then("^I enter a comment for that week and click on save$")
	public void i_click_on_save_enter_some_comments() throws Throwable {
		pd.SavingTimesheet();
	}
	
	@Then("^I switch back to the admin account$")
	public void i_switch_back_to_the_admin_account() throws Throwable {

		pd.SwitchingBackToAdmin();
		
	}


	@Then("^I check if the timesheets are filled properly by admin or not$")
	public void i_check_if_the_timesheets_are_filled_properly_by_admin_or_not() throws Throwable {
		pd.VerifyingFilledHours();
	}
	@Then("^I remove the filled timesheet again to delete the user and click on save$")
	public void i_remove_the_filled_timesheet_again_to_delete_the_user_and_click_on_save(DataTable empty) throws Throwable {
		pd.ClearTimeSheetDataTable(empty);
		//pd.ClearTimeSheetData(empty);
		//pd.clearingPTOMondayEntry();
		pd.EmptyingTimesheet();
	}

}
