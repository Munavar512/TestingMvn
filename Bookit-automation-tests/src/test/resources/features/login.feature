Feature: Login 

As a user, When i go to the login page, 
I should be able to login

@login
Scenario: login as teacher
	Given the user is on the login page
	When the user login as teacher
	Then the user should be logged in
	
		
Scenario: Login as a team lead
	Given the user is on the login page
	When the user logs in as team lead
	Then the user should be logged in

