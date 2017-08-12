@FeatureLockedPeriodsManagement
Feature: Locked Period Management 

@ScenarioLockedPeriodsManagement
Scenario Outline: Verify that locked period causes the timesheet to be locked for the given period.
	
	
	Given Open "<browser>" and start application
	When I enter valid "admin" and "admin" 
	Then I should be able see "Admin, eHour" on Welcome Page
	When I select menu item "Locked periods" 
	Then Page Header must display "Locked periods"
	When I search and delete the locked periods for current week
	And I create new locked period for current week
	And I logout from eHour
	And I enter valid "mparekh" and "test$123"
	Then I should be able see "Parekh, Malav" on Welcome Page 
	And I am navigated to Timesheet page 
	And I see that the fields are locked for the current week
	When I logout from eHour
	And I enter valid "admin" and "admin"
	And I select menu item "Locked periods" 
	Then Page Header must display "Locked periods"
	When I search and delete the locked periods for current week
	And I logout from eHour
	And I enter valid "mparekh" and "test$123"
	Then I should be able see "Parekh, Malav" on Welcome Page 
	And I am navigated to Timesheet page 
	And I see that the fields are unlocked for the current week
	When I logout from eHour
	Then I am successfully logged out
	
	
	
Examples:
|browser|
|remote-chrome|