package stepDefinition;

import org.junit.Assert;

import PageObjects.ProjectManagement;
import PageObjects.Page_UserManagement;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_ProjectManagement{
	
	ProjectManagement pm = new ProjectManagement("anything");
	Page_UserManagement um = new Page_UserManagement("anything");

	
	@When("^I add the following non-existent projects$")
	public void i_add_the_following_non_existent_projects(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			if(pm.getProjectRow(dt.raw().get(i).get(0), dt.raw().get(i).get(1))!=null){
				pm.DeleteProject(dt.raw().get(i));
			}
			pm.addProject(dt.raw().get(i));
		}
	}
	
	@Then("^I can search the following projects$")
	public void i_can_search_the_following_projects(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pm.SearchRecords(dt.raw().get(i).get(0));
			BaseSteps.AssertTrue("Filtered Row Count", "Non Zero", pm.getDisplayedFilteredRows().size()==0?"Zero":"Non Zero", pm.getDisplayedFilteredRows().size()>0);
			pm.SearchRecords("");	
		}
	}
		
		
	
	

	@When("^I modify the following projects$")
	public void i_modify_the_following_projects(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pm.EditProject(dt.raw().get(i));
		}
	}

	@When("^I delete the following projects$")
	public void i_delete_the_following_projects(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pm.DeleteProject(dt.raw().get(i));
		}
	}


	
	
	
}
