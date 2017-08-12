package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import PageObjects.Base;
import PageObjects.Page_Client;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class Steps_ClientManagement  {

	Page_Client pc = new Page_Client("Anything");
	
	
@When("^I create a client with the following details$")
public void i_create_a_client_with_the_following_details(DataTable table) throws Throwable {
    pc.CreateClient(table);
}

@Then("^I can search the client by Client Name \"([^\"]*)\"$")
public void i_can_search_the_client_by_Client_Name(String ClientName) throws Throwable {
	pc.VerifyIfClientRecordCanBeFoundInSearchResults(ClientName);
}

@When("^I select the client with Client Name \"([^\"]*)\"$")
public void i_select_the_client_with_Client_Name(String ClientName) throws Throwable {
	pc.SelectClientRowFromSearchResults(ClientName);
}

@Then("^The selected client has the following details$")
public void the_selected_client_has_the_following_details(DataTable table) throws Throwable {
	pc.VerifySelectedClient(table);
}

@When("^I delete the client with Client Name \"([^\"]*)\"$")
public void i_delete_the_client_with_Client_Name(String ClientName) throws Throwable {
		BaseSteps.AssertTrue("Desired Client Row in the List of All Clients", true, pc.getDesiredClient(ClientName)!=null, pc.getDesiredClient(ClientName)!=null);
		pc.getDesiredClient(ClientName).click();
		Thread.sleep(2000);
		pc.DeleteClient(ClientName);
}




@Then("^I cannot search the client by Client Name \"([^\"]*)\"$")
public void i_cannot_search_the_client_by_Client_Name(String ClientName) throws Throwable {
	BaseSteps.AssertTrue("Desired Client Row", true, pc.getDesiredClient(ClientName) == null, pc.getDesiredClient(ClientName) == null);
	//Assert.assertTrue("Client could be found", pc.getDisplayedFilteredClientCount().equals(0));
}

@When("^I delete a client named \"([^\"]*)\"$")
public void i_delete_a_client_named(String ClientName) throws Throwable {
	if (pc.getDesiredClient(ClientName)!=null){
		pc.getDesiredClient(ClientName).click();
		pc.DeleteClient(ClientName);
	}
}

}
