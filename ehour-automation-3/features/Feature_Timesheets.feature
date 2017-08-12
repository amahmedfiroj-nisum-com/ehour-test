Feature: Fill Timesheet Feature
		
		@FillTimesheet 
		Scenario Outline: User fills timesheet 
			Given Open "<browser>" and start application 
			When I enter valid "mparekh" and "test$123" 
			Then I should be able see "Parekh, Malav" on Welcome Page
			And I am navigated to Timesheet page 
			When I enter Timesheet Data under Account "Nisum Non-Billable" as follows 
				|Project		|Sun|Mon|Tue|Wed|Thu|Fri|Sat|
				|01-PTO																	|3|3|3|3|3|3|3|
				|02-Reserved for Future Client Work			|3|3|3|3|3|3|3|
				|02-Shadow															|3|3|3|3|3|3|3|  
				|02-Unassigned													|3|3|3|3|3|3|3|
				|03-Education														|3|3|3|3|3|3|3|
				|03-Internal Meetings and Events				|3|3|3|3|3|3|3|
				|03-Interviews or Recruiting events			|3|3|3|3|3|3|3|
			And I enter the comment "I am working on Automation"
			When I store the Timesheet Data 
			When I logout from eHour 
			Then I am successfully logged out 
			When I enter valid "mparekh" and "test$123" 
			Then I should be able see "Parekh, Malav" on Welcome Page
			And I am navigated to Timesheet page 
			Then the Timesheet Data under Account "Nisum Non-Billable" is stored as follows 
				|Project		|Sun|Mon|Tue|Wed|Thu|Fri|Sat|
				|01-PTO																	|3|3|3|3|3|3|3|
				|02-Reserved for Future Client Work			|3|3|3|3|3|3|3|
				|02-Shadow															|3|3|3|3|3|3|3|  
				|02-Unassigned													|3|3|3|3|3|3|3|
				|03-Education														|3|3|3|3|3|3|3|
				|03-Internal Meetings and Events				|3|3|3|3|3|3|3|
				|03-Interviews or Recruiting events			|3|3|3|3|3|3|3|
			And the retrieved comment is "I am working on Automation"
			Then I remove the filled timesheet again to delete the user and click on save
			   #| ProjectName    | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
				|01-PTO																	|3|3|3|3|3|3|3|
				|02-Reserved for Future Client Work			|3|3|3|3|3|3|3|
				|02-Shadow															|3|3|3|3|3|3|3|  
				|02-Unassigned													|3|3|3|3|3|3|3|
				|03-Education														|3|3|3|3|3|3|3|
				|03-Internal Meetings and Events				|3|3|3|3|3|3|3|
				|03-Interviews or Recruiting events			|3|3|3|3|3|3|3|
			When I logout from eHour
			Then I am successfully logged out

			Examples:
				|browser|
			        |remote-chrome |
