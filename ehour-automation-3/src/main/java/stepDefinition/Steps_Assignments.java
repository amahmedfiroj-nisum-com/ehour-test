package stepDefinition;

import PageObjects.Page_Assignments;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;

public class Steps_Assignments {

	Page_Assignments as = new Page_Assignments(null);
	
	
	
	
	@When("^I assign projects to users as follows$")
	public void i_assign_projects_to_users_as_follows(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			as.getDesiredUser(dt.raw().get(i).get(0), dt.raw().get(i).get(1)).click();
			//Thread.sleep(3000);
			as.AssignProject(dt.raw().get(i));
			//Thread.sleep(3000);
		}
		System.out.println("Assigned project to user");
	}
	
	
	@When("^I unassign projects to users as follows$")
	public void i_unassign_projects_to_users_as_follows(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			//Thread.sleep(2000);
			as.getDesiredUser(dt.raw().get(i).get(0), dt.raw().get(i).get(1)).click();
			//Thread.sleep(2000);
			as.DeleteProjectAssignment(dt.raw().get(i).get(2), dt.raw().get(i).get(3));
		}
		System.out.println("Unassigned Assignments as required");
	}
	
	@When("^I edit assignments as follows$")
	public void i_edit_assignments_as_follows(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			//Thread.sleep(3000);
			as.getDesiredUser(dt.raw().get(i).get(0), dt.raw().get(i).get(1)).click();
			//Thread.sleep(3000);
			as.EditProjectAssignment(dt.raw().get(i));

		}
		System.out.println("Edited Assignments as required");
	}
	
}
