@UserManagementFeature
Feature: User Management Feature 


@CreateUsers
Scenario Outline: Create Users
	Given Open "<browser>" and start application 
	When I enter valid "admin" and "admin" 
	Then I should be able see "Admin, eHour" on Welcome Page 

#	When I select menu item "Users" 
#	Then Page Header must display "User management" 
#	When I add a new user with the following details if non existent  
#		|Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|AssignToProjectAfterSave|Country|Location|EmployeeType|
#		|madeeluddin|Muhammad|Adeel Uddin|madeeluddin@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|fjafri|Syed Faisal|Jafri|fjafri@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|mzeeshan|Muhammad|Zeeshan|mzeeshan@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|nsiddiq|Noman|Siddiq|nsiddiq@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|siqbal|Shahzad|Iqbal|siqbal@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|tumer|Tabish|Umer Farooqi|tumer@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|gmshah|Ghulam|Muhammad Shah|gmshah@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|aqureshi|Adeel|Hussain Qureshi|aqureshi@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|ajamshaid|Adil|Jamshaid|ajamshaid@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|umaqsood|Umer|Maqsood|umaqsood@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|kiqbal|Khurram|Iqbal|kiqbal@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|shiqbal|Shaharyar|Iqbal|shiqbal@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|ahayat|Aqib|Hayat|ahayat@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#		|maslam|Muhammad|Aslam|maslam@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|Pakistan|Karachi|Nisum|
#	When I logout from eHour 
#	Then I am successfully logged out 
	Examples: 
		|browser|
|remote-chrome|


@CreateDeleteUser
Scenario Outline: Create and Delete User
	
	Given Open "<browser>" and start application 

	When I enter valid "admin" and "admin"
	Then I should be able see "Admin, eHour" on Welcome Page
	When I select menu item "Users" 
	Then Page Header must display "User management" 
	When I add a new user with the following details if non existent
		|Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRole|Active|AssignToProjectAfterSave|Country|Location|EmployeeType|TimeSheetApprover|
		|qwerty3user|qwerty3|qwerty3|qwerty3@nisum.com|Nisum$123|Nisum$123|Offshore|User|true|false|India|Hyderabad|Adroit|Lim, Karin|
	#Then I can search the client by Client Name "testuser"
	When I logout from eHour 
	Then I am successfully logged out 
	When I enter valid "qwerty3user" and "Nisum$123"
	Then I should be able see "qwerty3, qwerty3" on Welcome Page 
	When I logout from eHour 
	Then I am successfully logged out 
	When I enter valid "admin" and "admin" 
	Then I should be able see "Admin, eHour" on Welcome Page 
	When I select menu item "Users" 
	When I delete the user with username - "qwerty3"
	Then I cannot search the user with the username - "qwerty3"
	When I logout from eHour 
	Then I am successfully logged out 
	
	Examples: 
		|browser|
|remote-chrome|
		
		
	#	@ChangePassword 
	#	Scenario Outline: Change Password 
		
	#		Given Open "<browser>" and start application 
	#		When I enter valid "admin" and "admin" 
	#		Then user should be able see "Admin, eHour" on Welcome Page 
	#		When I select menu item "Users" 
	#		Then Page Header must display "User management" 
	#		When I add a new user with the following details if non existent
	#			|Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|AssignToProjectAfterSave|Country|Location|
	#			|test|tu-fn1|tu-ln1|tuemail1@nisum.com|tu@123|tu@123|Offshore|User,Manager|true|false|India|Hydrabad|
	#		Then I can search the user with the username - "test" 
	#		When I Select the user with username - "test" 
	#		Then I should see password field 
	#		When I enter password as "test123" 
	#		And I enter confirm password as "test123" 
	#		When I should click save button on user page 
	#		Then I should see user data saved 
	#		When I Select the user with username - "test" 
	#		And I press the delete button 
	#		Then I cannot search the user with the username - "test" 
	#		When I logout from eHour 
	#		Then I am successfully logged out 
			
	#		Examples: 
	#			|browser|
	#			|chrome|
				
				@EditUser
				Scenario Outline: Edit User 
				
					Given Open "<browser>" and start application 
					When I enter valid "admin" and "admin" 
					Then I should be able see "Admin, eHour" on Welcome Page 
					When I select menu item "Users" 
					Then Page Header must display "User management" 
	
					When I add a new user with the following details if non existent  
						|Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|AssignToProjectAfterSave|Country|Location|EmployeeType|TimesheetApprover|
						|qwerty3user|qwerty3|qwerty3|qwerty3@nisum.com|Nisum123|Nisum123|Offshore|User|true|false|India|Hyderabad|Adroit|Parekh, Malav|
						
	
					When I edit a user with the following details 
						|Username|FirstName|LastName|Email|Password|ConfirmPassword|Department|UserRoles|Active|Country|Location|EmployeeType|TimesheetApprover|
						|qwerty3user||qwerty3||Nisum$123|Nisum$123|||true|||Nisum||
						
		
					When I logout from eHour 
					Then I am successfully logged out 
			
						
					Examples: 
						|browser|
						|remote-chrome|
						
						
		