Feature: Client Management 

@ClientManagement
Scenario Outline: Client - Add, Search, Delete

	Given Open "<browser>" and start application
	When I enter valid "admin" and "admin" 
	Then I should be able see "Admin, eHour" on Welcome Page
	When I select menu item "Clients" 
	Then Page Header must display "Client management"
	When I delete a client named "TestClient"	
	When I create a client with the following details
	|ClientName|ClientCode|ClientDescription|IsActive|
	|TestClient|TestClientCode|Test Client Description|true|
	Then I can search the client by Client Name "TestClient"
	When I select the client with Client Name "TestClient"
	Then The selected client has the following details
	|ClientName|ClientCode|ClientDescription|IsActive|
	|TestClient|TestClientCode|Test Client Description|true|
	When I delete the client with Client Name "TestClient"
	Then I cannot search the client by Client Name "TestClient"
	When I logout from eHour 
	Then I am successfully logged out

Examples:
|browser|
|remote-chrome|
