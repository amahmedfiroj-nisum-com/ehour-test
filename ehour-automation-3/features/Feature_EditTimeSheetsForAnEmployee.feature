@Edit_timesheets_on_behalf_of_other_employee
Feature: Edit time sheets for other employees
	@EditTimeSheets
  Scenario Outline: Creates an user and edit timesheets with admin rights
    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I add a new user with the following details if non existent
      | Username   | FirstName | LastName | Email             | Password  | ConfirmPassword | Department | UserRole | Active | AssignToProjectAfterSave | Country  | Location | EmployeeType |TimeSheetApprover|
      #| Automation | QA        | Testing  | Testing@nisum.com | yahoo$123 | yahoo$123       | Offshore   | User     | true   | false                    | Pakistan | Karachi  | Nisum        |Lim, Karin|
      |qwerty3user|qwerty3|qwerty3|qwerty3@nisum.com|Nisum$123|Nisum$123|Offshore|User|true|true|India|Hyderabad|Adroit|Lim, Karin|
    Then I select menu item "Edit timesheet"
    Then I select a user "qwerty3" to edit the timesheet
    Then I click on Temporarily sign in and check if i logged in temporarily to that user i should see "qwerty3, qwerty3"
    Then I fill the timesheet of the user as the following
     #| ProjectName    | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
     | PTO            | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | Reserved       | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | shadow         | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | Unassigned     | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | Education      | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | Meeting&Events | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
     | Interviews     | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 | 3.00 |
    Then I enter a comment for that week and click on save
    Then I switch back to the admin account
    When I logout from eHour
    Then I am successfully logged out
    When I enter valid "qwerty3user" and "Nisum$123"
    Then I should be able see "qwerty3, qwerty3" on Welcome Page
    Then I check if the timesheets are filled properly by admin or not
    Then I remove the filled timesheet again to delete the user and click on save
     #| ProjectName    | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
      | PTO            | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | Reserved       | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | shadow         | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | Unassigned     | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | Education      | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | Meeting&Events | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
      | Interviews     | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 | 0.00 |
    When I logout from eHour
    Then I am successfully logged out
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    When I Select the user with username - "qwerty3"
    When I press the delete button
    Then I cannot search the user with the username - "qwerty3"
    When I logout from eHour
    Then I am successfully logged out

    Examples: 
      | browser |
      | remote-chrome  |
