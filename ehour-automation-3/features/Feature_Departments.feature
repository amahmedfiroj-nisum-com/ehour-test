Feature: Create, Update, Delete Departments

@CRUDDepartments
Scenario Outline: Create Update Delete Departments

Given Open "<browser>" and start application

	When I enter valid "admin" and "admin" 

	Then I should be able see "Admin, eHour" on Welcome Page
	When I select menu item "Functional groups" 
	Then Page Header must display "Functional groups management" 
	When I add the following non-existing department
      | DepartmentName     | DepartmentCode | FunctionalManager |
      | TestDepartmentName | TDC            | Lim, Karin        |
	Then I can search the following departments
	|DepartmentName|DepartmentCode|
	|TestDepartmentName|TDC|
	When I edit the following department
      | OriginalDepartmentName | OriginalDepartmentCode | NewDepartmentName | NewDdepartmentCode |NewFunctionalManager|
      | TestDepartmentName     | TDC                    | NewDepartmentName | NDC                |Lopez, Luis         |
	When I delete the following department
	|DepartmentName|DepartmentCode|
	|NewDepartmentName|NDC|
 	When I logout from eHour 
	Then I am successfully logged out
 	
	Examples:
	|browser|
	|remote-chrome|