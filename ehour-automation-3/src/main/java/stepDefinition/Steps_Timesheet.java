package stepDefinition;

import PageObjects.Page_Timesheet;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.Assert;

public class Steps_Timesheet {

	Page_Timesheet pt = new Page_Timesheet("anything");

	
	
	
@Then("^I am navigated to Timesheet page$")
public void i_am_navigated_to_Timesheet_page() throws Throwable {
	Assert.assertTrue(pt.IsTimesheetPageDisplayed());
	System.out.println("Navigated to Timesheet");
}


@Then("^I see the dates of the current week$")
public void i_see_the_dates_of_the_current_week() throws Throwable {
    Assert.assertTrue(pt.MatchCurrentWeekDaysAndDates());
}

@When("^I enter Timesheet Data under Account \"([^\"]*)\" as follows$")
public void i_enter_Timesheet_Data_under_Account_as_follows(String AccountName, DataTable table) throws Throwable {
	pt.FillTimeSheetData(AccountName, table);
}

//below i added a method

    @Then("^I should see user name \"([^\"]*)\" as follows$")
    public void i_should_see_user_name_as_follows(String USERNAME) throws Throwable {
        pt.verifyUsernameToApproveTimesheet(USERNAME);
        System.out.println(USERNAME);
    }

    //below iadded
    @Then("^I should see the status as \"([^\"]*)\"$")
    public void i_should_see_the_status_as(String STATUSCODE) throws Throwable {
        pt.verifyStatusCodeAfterApproval(STATUSCODE);
        System.out.println(STATUSCODE);
    }

    //below i added
    @Then("^I should see status on approvertimesheet as \"([^\"]*)\"$")
    public void i_should_see_status_on_approvertimesheet_as(String LABEL) throws Throwable {
        pt.verifyLabelOnApproverTimeSheet(LABEL);
        System.out.println(LABEL);
    }

    /*
    //below i added a method
    @Then("^I remove the filled timesheet again to delete the user and click on save$")
    public void i_remove_the_filled_timesheet_again_to_delete_the_user_and_click_on_save(DataTable empty) throws Throwable {
        pt.ClearTimeSheetDataTable(empty);
        pt.EmptyingTimesheet();
    }*/

    @When("^I enter the comments - \"([^\"]*)\"$")
    public void i_enter_the_comments(String arg1) throws Throwable {

    }

@When("^I store the Timesheet Data$")
public void i_store_the_Timesheet_Data() throws Throwable {
  pt.StoreTime();
}

@Then("^the Timesheet Data under Account \"([^\"]*)\" is stored as follows$")
public void the_Timesheet_Data_under_Account_is_stored_as_follows(String AccountName, DataTable table) throws Throwable {	
	Assert.assertTrue(pt.CheckEnteredTimesheet(AccountName, table), "Stored timesheet is different from expected timesheet");
}


@Then("^I see the following accounts-projects in the timesheet$")
public void i_see_the_following_accounts_projects_in_the_timesheet(DataTable dt) throws Throwable {

	for(int i=1;i < dt.raw().size();i++){
		BaseSteps.AssertTrue("Account Specific Project Row", "Not Null",pt.getProjectSpecificRowUnderAccount(dt.raw().get(i).get(0), dt.raw().get(i).get(1))!=null?"Not Null":"Null" , pt.getProjectSpecificRowUnderAccount(dt.raw().get(i).get(0), dt.raw().get(i).get(1))!=null);
		System.out.println(pt.getProjectSpecificRowUnderAccount(dt.raw().get(i).get(0), dt.raw().get(i).get(1)));
	}
	System.out.println("Verified the accounts-projects in the timesheet");

	}


	@When("^I enter the comment \"([^\"]*)\"$")
		public void i_enter_the_comment(String Comment) throws Throwable {
		pt.fillComments(Comment);
	}

	@Then("^the retrieved comment is \"([^\"]*)\"$")
	public void the_retrieved_comment_is(String Comment) throws Throwable {
	   pt.VerifyComments(Comment);
	}

	@When("^I set Week Location to \"([^\"]*)\"$")
	public void i_set_Week_Location_to(String Location) throws Throwable {
		pt.SetWorkLocation(Location);
	}

	@Then("^I see the week location as \"([^\"]*)\"$")
	public void i_see_the_week_location_as(String Location) throws Throwable {
		BaseSteps.AssertTrue("Work Location", Location, pt.getWorkLocation(), pt.getWorkLocation().equals(Location));
	}
	
}
