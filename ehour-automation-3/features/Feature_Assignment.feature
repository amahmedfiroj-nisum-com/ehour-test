@FeatureAssignment
Feature: Assigning Users to Projects 

@ProjectAssignment1
Scenario Outline: Create Users and assign them to project 
	Given Open "<browser>" and start application 
	When I enter valid "admin" and "admin"

	Then I should be able see "Admin, eHour" on Welcome Page
	When I select menu item "Users" 
	Then Page Header must display "User management" 
	When I add a new user with the following details if non existent
		|Username|FirstName|LastName|Email|Password|ConfirmPassword|FunctionalGroup|UserRole|Active|AssignToProjectAfterSave|Country|Location|EmployeeType|TimeSheetApprover|
		|qwerty3user|qwerty3|qwerty3|qwerty3@nisum.com|Nisum$123|Nisum$123|Offshore|User|true|false|Pakistan|Karachi|Nisum|Lim, Karin|
		
	When I select menu item "Assignments" 
	When I assign projects to users as follows 
		|FirstName|LastName|Account|Project|AssignmentType|StartDate|EndDate|Role|HourlyRate|Active|
		|qwerty3|qwerty3|NB - Nisum Billable|ATS-Product - ATS-Product|Date range|true|true|Developer|0.00|true|

	When I logout from eHour
	Then I am successfully logged out 	
	When I enter valid "qwerty3user" and "Nisum$123"
	Then I should be able see "qwerty3, qwerty3" on Welcome Page
	And I am navigated to Timesheet page 
	
	And I see the following accounts-projects in the timesheet
	|AccountName|ProjectName|
	|Nisum Billable|ATS-Product|

	When I logout from eHour 
	Then I am successfully logged out 	
	

	When I enter valid "admin" and "admin" 
	Then I should be able see "Admin, eHour" on Welcome Page 

	When I select menu item "Assignments" 

	When I edit assignments as follows
		|FirstName|LastName|Account|Project|AssignmentType|StartDate|EndDate|Role|HourlyRate|Active|
		|qwerty3|qwerty3|NB - Nisum Billable|ATS-Product - ATS-Product|Date range|true|true|Tester|10.00|true|
	When I unassign projects to users as follows
		|FirstName|LastName|Account|Project|AssignmentType|StartDate|EndDate|Role|HourlyRate|Active|
		|qwerty3|qwerty3|NB - Nisum Billable|ATS-Product - ATS-Product|Date range|true|true|Developer|0.00|true|
	When I logout from eHour
	Then I am successfully logged out 	
	Examples: 
		|browser|
		|remote-chrome|
