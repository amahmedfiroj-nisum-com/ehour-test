Feature: Assigning a date based project to the user

  @ProjectAssignment
  Scenario Outline: Create Users and assign them to project I want to create a user, assign a date based project to the user and verify if the same is reflecting on the user account
    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I add a new user with the following details if non existent
      | Username   | FirstName | LastName | Email             | Password  | ConfirmPassword | Department | UserRole | Active | AssignToProjectAfterSave | Country  | Location | EmployeeType |TimeSheetApprover|
      #| Automation | QA        | Testing  | Testing@nisum.com | yahoo$123 | yahoo$123       | Offshore   | User     | true   | false                    | Pakistan | Karachi  | Nisum        |Lim, Karin|
      |qwertyuser|qwerty|qwerty|qwerty@nisum.com|Nisum$123|Nisum$123|Offshore|User|true|true|India|Hyderabad|Adroit|Lim, Karin|
    When I select menu item "Assignments"
    When I select a client named "qwerty"
    Then I should assign a project with Start and End date
      | Assignment type |
      | Date range      |
    Given Open "<browser>" and start application
    Then I enter valid "qwertyuser" and "Nisum$123"
    Then I should be able see "qwerty, qwerty" on Welcome Page
    Then i check if project is displayed or not when the user is logged in
    Then i check if project is not displayed when i select the other dates in time frame
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    When I Select the user with username - "qwerty"
    When I press the delete button
    Then I cannot search the user with the username - "qwerty"
    When I logout from eHour
    Then I am successfully logged out

    Examples: 
      | browser |
      | remote-chrome  |
