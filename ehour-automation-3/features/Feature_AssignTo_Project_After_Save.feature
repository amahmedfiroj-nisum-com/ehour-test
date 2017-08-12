@FeatureAssignProjectAfterSave
Feature: Assign To Project After Save

  @AssignProjectAfterSave
  Scenario Outline: Assign To Project After Save
    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I add a new user with the following details if non existent
      |Username|FirstName|LastName|Email|Password|ConfirmPassword|Functional Group|UserRoles|Active|AssignToProjectAfterSave|Country|Location|EmployeeType|TimeSheetApprover|
      |qwerty3user|qwerty3|qwerty3|qwerty3@nisum.com|Nisum$123|Nisum$123|Offshore|User|true|true|India|Hyderabad|Adroit|Lim, Karin|
    When I select menu item "Assignments"
    When I assign projects to users as follows
      |FirstName|LastName|Account|Project|AssignmentType|StartDate|EndDate|Role|HourlyRate|Active|
      |qwerty3|qwerty3|NB - Nisum Billable|ATS-Product - ATS-Product|Date range|true|true|QA|0.00|true|
    Then I click save button on user page
    When I logout from eHour
    Then I am successfully logged out
    Given Open "<browser>" and start application
    When I enter valid "qwerty3user" and "Nisum$123"
    Then I should be able see "qwerty3, qwerty3" on Welcome Page
    And I am navigated to Timesheet page
    And I see the following accounts-projects in the timesheet
      |AccountName|ProjectName|
      |Nisum Billable|ATS-Product|
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I select menu item "Users"
    When I Select the user with username - "qwerty3"
    When I press the delete button
    Then I cannot search the user with the username - "qwerty3"
    When I logout from eHour
    Then I am successfully logged out

    Examples:
      |browser|
      |remote-chrome|

