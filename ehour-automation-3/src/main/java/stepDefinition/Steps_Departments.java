package stepDefinition;

import PageObjects.Page_Departments;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Departments {

	Page_Departments pd = new Page_Departments(null);
	
	
	@When("^I add the following non-existing department$")
	public void i_add_the_following_non_existing_department(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			if(pd.getDepartmentRow(dt.raw().get(i).get(0), dt.raw().get(i).get(1))!=null){
				pd.getDepartmentRow(dt.raw().get(i).get(0), dt.raw().get(i).get(1)).click();
				pd.DeleteDepartment(dt.raw().get(i));
			}
			
			pd.AddDepartment(dt.raw().get(i));
		}
		System.out.println("Added Department");
		
	}

	@Then("^I can search the following departments$")
	public void i_search_the_following_department(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pd.SearchRecords(dt.raw().get(i).get(0));
			BaseSteps.AssertTrue("Displayed filtered rows", 1, pd.getDisplayedFilteredRows().size(), pd.getDisplayedFilteredRows().size()>0);
			pd.getTextBoxFilter().clear();
			}
		System.out.println("Searched Department");
	}
	
	@When("^I edit the following department$")
	public void i_edit_the_following_department(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pd.EditDepartment(dt.raw().get(i));
		}
		System.out.println("Edited Department");
	}

	@When("^I delete the following department$")
	public void i_delete_the_following_department(DataTable dt) throws Throwable {
		for(int i=1;i<dt.raw().size();i++){
			pd.DeleteDepartment(dt.raw().get(i));
		}
		System.out.println("Deleted Department");
	}	
}
