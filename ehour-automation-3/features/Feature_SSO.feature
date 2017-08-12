@FeatureSingleSignOn
Feature: Single SignOn

@ScenarioSingleSignOn
  Scenario Outline: Verify the Single Sign On is working for Google+ through e-hour

    Given Open "<browser>" and launch google
    When I login to google with username "ehoursupport@nisum.com" and password "ehour@123"
    And I load eHour Application Home Page
    When I login to eHour using Single Signon as "ehoursupport@nisum.com"
    Then I should be able see "Support, eHour" on Welcome Page
    And I am navigated to Timesheet page
    When I logout from eHour
    Then I am successfully logged out

    Examples:
      | browser |
      |remote-chrome|

#     Scenario Outline: Verify the Single Sign On with User who has lastname is null
##-- Malav to Parvathi on 11-Jul-17 5:30pm : Incorrect scenario. We have to have the google account last name as blank. In eHour, it doesn't allow the last name to be null
#
#       Given Open "<browser>" and start application
#       When I enter valid "admin" and "admin"
#       Then I should be able see "Admin, eHour" on Welcome Page
#       When I select menu item "Users"
#       Then Page Header must display "User management"
#       When I edit a user with the following details
#         |Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|Country|Location|EmployeeType|
#         |ehoursupport||Null||||||true|||Nisum|
#       When I logout from eHour
#       Then I am successfully logged out
#		Given Open "<browser>" and launch google
#		When I login to google with username "ehoursupport@nisum.com" and password "ehour@123"
#		And I load eHour Application Home Page
#		When I login to eHour using Single Signon as "ehoursupport@nisum.com"
#		Then I should be able see "Support, eHour" on Welcome Page
#
#       Examples:
#     	|browser|
#     	|chrome|

  @ScenarioInactiveUserSingleSignOn
  Scenario Outline: Verify the Single Sign On for Inactive user

    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I set Show Inactive flag to "true"
    When I edit a user with the following details
      |Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|Country|Location|EmployeeType|
      |ehoursupport||Support||||||false|||Nisum|
    When I logout from eHour
    Then I am successfully logged out
    Given Open "<browser>" and launch google
    When I login to google with username "ehoursupport@nisum.com" and password "ehour@123"
    And I load eHour Application Home Page
    When I login to eHour using Single Signon as "ehoursupport@nisum.com"
    Then I should see the error "incorrect username and password combination"
    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I set Show Inactive flag to "true"
    When I edit a user with the following details
      |Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|Country|Location|EmployeeType|
      |ehoursupport||Support||||||true|||Nisum|
    When I logout from eHour
    Then I am successfully logged out
	#Then I close the browser  //Malav to Parvathi on 13-Jul-17 4:35PM: This is an easy way of doing it. However I've also changed the method for Google Login to check if it can directly see the password field. We can choose to ignore this step.
    Given Open "<browser>" and launch google
    Then I am successfully logged out from Google
    When I login to google with username "ehoursupport@nisum.com" and password "ehour@123"
    And I load eHour Application Home Page
   	When I login to eHour using Single Signon as "ehoursupport@nisum.com"
    Then I should be able see "Support, eHour" on Welcome Page
  	When I logout from eHour
    Then I am successfully logged out

    Examples:
      | browser |
      |remote-chrome|