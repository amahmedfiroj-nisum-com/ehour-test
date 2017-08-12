Feature: Project Management Feature

@ProjectManagement
Scenario Outline: Project Management
	
	
	
	Given Open "<browser>" and start application 
	When I enter valid "admin" and "admin"
	And I select menu item "Projects" 
	Then Page Header must display "Project management"
	
	#When I delete a project named "Ehour" 
	When I add the following non-existent projects
	|Name|ProjectCode|Client|ProjectManager|Description|ContactPerson|DefaultProject|Billable|Active|
	|TestProject|TPC|NOB - Nisum Non-Billable|No projectmanager|TPC-Desc|ABC Person|true|false|true|
	
	Then I can search the following projects
	|Name|ProjectCode|
	|TestProject|TPC|
	
	
	When I modify the following projects
	|Name|ProjectCode|Client|ProjectManager|Description|ContactPerson|DefaultProject|Billable|Active|
	|TestProject|TPC|NOB - Nisum Non-Billable|No projectmanager|TPC-Desc|ABC Person|true|false|true|
	
	When I delete the following projects
	|Name|ProjectCode|
	|TestProject|TPC|
	
	
	When I logout from eHour 
	Then I am successfully logged out
	
	
	
		Examples: 
		|browser|
		|remote-chrome|