package stepDefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;

import PageObjects.Page_Client;
import PageObjects.Page_UserManagement;

public class Steps_AssigningDateBased {
	
	
	Page_UserManagement um = new Page_UserManagement("anything");
	Page_Client pc = new Page_Client("Anything");
	
	@When("^I select a client named \"([^\"]*)\"$")
public void i_select_a_client_named(String ClientName) throws Throwable {
		
		/*if (pc.getMyClient(ClientName)!=null){
			pc.getMyClient(ClientName).click();
		}*/
		pc.getMyClient(ClientName);
		//um.getDesiredUser(ClientName);
		//um.SelectUser(ClientName);
		System.out.println("Selected Client Name:"+ClientName);
	}
	
	@Then("^I should assign a project with Start and End date$")
	public void i_should_assign_a_project_with_start_and_End_date(DataTable dt) throws Throwable {
		//System.out.println(dt.raw().size());
		
		
		for(int i=1;i<dt.raw().size();i++){
			
				um.SetDate(dt.raw().get(i));
			}
		System.out.println("Assiagned a project with Start and End date");
		
		}
	

	@Then("^i check if project is displayed or not when the user is logged in$")
	public boolean i_select_a_date_between_assigned_date_to_check_if_project_is_displayed() throws Throwable {
		if(um.Verify_start_end_date()) {
			System.out.println("The project assigned is being displayed properly!");
			return  true;
		} else {
			System.out.println("The project assigned is not displayed properly!!");
			return false;
		}
		
	}
	

	@Then("^i check if project is not displayed when i select the other dates in time frame$")
	public boolean i_check_if_project_is_not_displayed_when_i_select_the_other_dates_in_time_frame() throws Throwable {
		if(um.check_project_in_other_dates()) {
			return true;
		} else {
			
		}return false;
		
		
	}
	}
