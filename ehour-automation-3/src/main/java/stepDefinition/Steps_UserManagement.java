package stepDefinition;

import static PageObjects.Base.driver;
import static org.testng.Assert.assertTrue;

import org.junit.Assert;

import PageObjects.Page_Header;
import PageObjects.Page_UserManagement;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_UserManagement {

	Page_Header h = new Page_Header("anything");
	Page_UserManagement um = new Page_UserManagement("anything");
	
	@Then("^Page Header must display \"([^\"]*)\"$")
	public void page_Header_must_display(String PageHeader) throws Throwable {
		Assert.assertTrue(h.getPageHeader().equals(PageHeader));
		System.out.println("Page Header Verification");
		
	}
	
	@When("^I set Show Inactive flag to \"([^\"]*)\"$")
	public void i_set_Show_Inactive_flag_to(String flag) throws Throwable {
		um.ToggleInactiveFlag(flag.equals("true")?true:false);
	}
	
	@When("^I delete the user with username - \"([^\"]*)\"$")
	public void i_delete_the_user_with_username(String UserName) throws Throwable {
		um.DeleteUser(UserName);
	}
	
	@When("^I add a new user with the following details if non existent$")
	public void i_add_a_new_user_with_the_following_details_if_non_existent(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			if(um.getDesiredUser(dt.raw().get(i).get(2))!=null){
				um.DeleteUser(dt.raw().get(i).get(2));
			}
			um.CreateUserAssignProject(dt.raw().get(i));
			Thread.sleep(3000);
		}
		System.out.println("Added New User");

	}


	@When("^I add a new user with the following details if non exist$")
	public void i_add_a_new_user_with_the_following_details_if_non_exist(DataTable dt) throws Throwable {
		for (int i = 1; i < dt.raw().size(); i++) {
			if (um.getDesiredUser(dt.raw().get(i).get(0)) != null) {
				um.DeleteUser(dt.raw().get(i).get(0));
			}
			um.CreateUserAssignProject(dt.raw().get(i));
			Thread.sleep(3000);
		}
	}



	/*
	@When("^I add a new user with the following details$")
	public void i_add_a_new_user_with_the_following_details(DataTable dt) throws Throwable {
	   um.CreateUser(dt);
	   
	}*/
	
	@Then("^I can search the user with the username - \"([^\"]*)\"$")
	public void i_can_search_the_user_with_the_username(String UserName) throws Throwable {
	Assert.assertTrue(um.getUserSearchResultCount(UserName)==1);
	}

	@When("^I Select the user with username - \"([^\"]*)\"$")
	public void i_Select_the_user_with_username(String UserName) throws Throwable {
    //um.SelectUserRowFromSearchResults(UserName);
    um.getDesiredUser(UserName).click();
    System.out.println("Selected the user with username:"+UserName);
	}

	@When("^I press the delete button$")
	public void i_press_the_delete_button() throws Throwable {
    um.PressDeleteButton();
    System.out.println("Clicked on Delete Button");
	}

	@Then("^I cannot search the user with the username - \"([^\"]*)\"$")
	public void i_cannot_search_the_user_with_the_username(String UserName) throws Throwable {
    //Assert.assertTrue(um.getUserSearchResultCount(UserName) == 0);
	
    BaseSteps.AssertTrue("Desired User Row", "NULL" , um.getDesiredUser(UserName)==null?"NULL":"Not NULL" , um.getDesiredUser(UserName)==null);
    System.out.println("Cannot Search the user with username:"+UserName);
	}

	@Then("^I should see password field$")
	public void i_should_see_password_field() throws Throwable {
    um.passwordField();
	}

	@When("^I enter password as \"([^\"]*)\"$")
	public void i_enter_password_as(String password) throws Throwable {
	um.enterPasssword(password);
	}

	@When("^I enter confirm password as \"([^\"]*)\"$")
	public void i_enter_confirm_password_as(String confirmpassword) throws Throwable {
    um.enterConfirmPassword(confirmpassword);
	}

	@When("^I should click save button on user page$")
	public void i_should_click_save_button_on_user_page() throws Throwable {
    um.getButtonSave().click();
	}

	@When("^I click save button on user page$")
	public void i_click_save_button_on_user_page() throws Throwable {
		um.getButtonSave().click();
		System.out.println("Clicked on Save Button");
	}



    @Then("^I should see user data saved$")
	public void i_should_see_user_data_saved() throws Throwable {
		Assert.assertTrue(um.getError().getText().equals("Data saved"));
    
    }

	@When("^I edit a user with the following details$")
    public void i_edit_a_user_with_the_following_details(DataTable dt) throws Throwable {
    	for(int i=1;i<dt.raw().size();i++){
    		if(um.getDesiredUser(dt.raw().get(i).get(2))!=null){
	    		um.getDesiredUser(dt.raw().get(i).get(2)).click();
	    		Thread.sleep(5000);
	    		um.EditUser(dt.raw().get(i));
    		}else{
    			throw new Exception("User Not Found");
    		}
    	}
    }

}
