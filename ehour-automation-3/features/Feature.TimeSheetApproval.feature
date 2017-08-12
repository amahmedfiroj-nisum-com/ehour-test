Feature: Time Sheet Approval

  @TimeSheetApproval
  Scenario Outline: Time Sheet Approver
    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    Then I should be able see "Admin, eHour" on Welcome Page
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I add a new user with the following details if non exist
      | Username | FirstName | LastName | Email             | Password | ConfirmPassword | Functional Group | UserRoles | Active | AssignToProjectAfterSave | Country | Location  | EmployeeType | TimeSheetApprover |
      #| qwerty1  | qwerty1   | qwerty1  | qwerty1@nisum.com | qwerty1  | qwerty1         | Offshore   | User      | true   | false                    | India   | Hyderabad | Adroit      | Lim, Karin        |
      | Automation | QA        | Testing  | Testing@nisum.com | yahoo$123 | yahoo$123       | Offshore   | User     | true   | false                    | Pakistan | Karachi  | Nisum        |Lim, Karin|
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I add a new user with the following details if non exist
      | Username | FirstName | LastName | Email             | Password | ConfirmPassword | Functional Group | UserRoles | Active | AssignToProjectAfterSave | Country | Location  | EmployeeType | TimeSheetApprover |
      | qwerty2  | qwerty2   | qwerty2  | qwerty2@nisum.com | qwerty2  | qwerty2         | Offshore   | User      | true   | true                     | India   | Hyderabad | Adroit      | Testing, QA  |
    When I select menu item "Assignments"
    When I assign projects to users as follows
      | FirstName | LastName | Account             | Project                   | AssignmentType | StartDate | EndDate | Role | HourlyRate | Active |
      | qwerty2   | qwerty2  | NB - Nisum Billable | ATS-Product - ATS-Product | Date range     | true      | true    | QA   | 0.00       | true   |
    Then I click save button on user page
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "qwerty2" and "qwerty2"
    Then I should be able see "qwerty2, qwerty2" on Welcome Page
    And I am navigated to Timesheet page
    And I see the following accounts-projects in the timesheet
      | AccountName    | ProjectName |
      | Nisum Billable | ATS-Product |
    When I enter Timesheet Data under Account "Nisum Billable" as follows
      | Project     | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
      | ATS-Product | 8   | 8   | 8   | 8   | 8   | 8   | 8   |
    And I enter the comment "I am working on QA Automation"
    When I store the Timesheet Data
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "Automation" and "yahoo$123"
    Then I should be able see "Testing, QA" on Welcome Page
    When I click on Approve Timesheet
    Then I should see user name "qwerty2, qwerty2" as follows
    Then I should see the status as "POSTED"
    When I click on reject Anchor
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "qwerty2" and "qwerty2"
    Then I should be able see "qwerty2, qwerty2" on Welcome Page
    Then I should see the status as "REJECTED"
    When I logout from eHour
    Then I am successfully logged out


    Given Open "<browser>" and start application
    When I enter valid "Automation" and "yahoo$123"
    Then I should be able see "Testing, QA" on Welcome Page
    When I click on Approve Timesheet
    Then I should see status on approvertimesheet as "REJECTED"
    When I click on approve Anchor
    When I logout from eHour
    Then I am successfully logged out


    Given Open "<browser>" and start application
    When I enter valid "Automation" and "yahoo$123"
    Then I should be able see "Testing, QA" on Welcome Page
    When I click on Approve Timesheet
    Then I should see status on approvertimesheet as "APPROVED"
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "qwerty2" and "qwerty2"
    Then I should be able see "qwerty2, qwerty2" on Welcome Page
    Then I should see the status as "APPROVED"
    When I logout from eHour
    Then I am successfully logged out


    Given Open "<browser>" and start application
    When I enter valid "qwerty2" and "qwerty2"
    Then I should be able see "qwerty2, qwerty2" on Welcome Page
    And I am navigated to Timesheet page
    Then the Timesheet Data under Account "Nisum Billable" is stored as follows
      | Project     | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
      | ATS-Product | 8   | 8   | 8   | 8   | 8   | 8   | 8   |
    And the retrieved comment is "I am working on QA Automation"
    Then I remove the filled timesheet again to delete the user and click on save
      #| ProjectName    | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
      | ATS-Product |  |  |  |  |  |  |  |
    When I logout from eHour
    Then I am successfully logged out

    Given Open "<browser>" and start application
    When I enter valid "admin" and "admin"
    When I select menu item "Users"
    Then Page Header must display "User management"
    When I Select the user with username - "qwerty2"
    When I press the delete button
    Then I cannot search the user with the username - "qwerty2"
    When I select menu item "Users"
    When I Select the user with username - "Testing"
    When I press the delete button
    Then I cannot search the user with the username - "Testing"
    When I logout from eHour
    Then I am successfully logged out

    Examples:
      | browser |
      | chrome  |
