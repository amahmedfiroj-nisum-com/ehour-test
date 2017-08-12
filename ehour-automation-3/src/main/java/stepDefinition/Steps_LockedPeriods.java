package stepDefinition;

import java.util.Date;

import PageObjects.Base;
import PageObjects.Page_LockedPeriods;
import PageObjects.Page_Timesheet;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_LockedPeriods {

	Page_LockedPeriods lp = new Page_LockedPeriods(null);
	Page_Timesheet ts = new Page_Timesheet(null);
	
	@When("^I search and delete the locked periods for current week$")
	public void i_search_and_delete_the_locked_periods_for_current_week() throws Throwable {
		Date FirstDateOfWeek = new Base(null).GetFirstDateOfCurrentWeek();
		Date LastDateOfWeek = new Base(null).GetLastDateOfCurrentWeek();
		System.out.println(FirstDateOfWeek);
		System.out.println(LastDateOfWeek);
		lp.SearchLockedPeriods(FirstDateOfWeek, LastDateOfWeek);
	}

	@When("^I create new locked period for current week$")
	public void i_create_new_locked_period_for_current_week() throws Throwable {
		Date FirstDateOfWeek = new Base(null).GetFirstDateOfCurrentWeek();
		Date LastDateOfWeek = new Base(null).GetLastDateOfCurrentWeek();
		lp.CreateLockedPeriod(FirstDateOfWeek, LastDateOfWeek, FirstDateOfWeek.toString() + LastDateOfWeek.toString());
	}

	@Then("^I see that the fields are locked for the current week$")
	public void i_see_that_the_fields_are_locked_for_the_current_week() throws Throwable {
		Date FirstDateOfWeek = new Base(null).GetFirstDateOfCurrentWeek();
		Date LastDateOfWeek = new Base(null).GetLastDateOfCurrentWeek();
		ts.VerifyTimesheetWeekLocked(FirstDateOfWeek, LastDateOfWeek);
		
	}

	@Then("^I see that the fields are unlocked for the current week$")
	public void i_see_that_the_fields_are_unlocked_for_the_current_week() throws Throwable {
		Date FirstDateOfWeek = new Base(null).GetFirstDateOfCurrentWeek();
		Date LastDateOfWeek = new Base(null).GetLastDateOfCurrentWeek();
		
		ts.VerifyTimesheetWeekUnLocked(FirstDateOfWeek, LastDateOfWeek);
	}

	
	
}
