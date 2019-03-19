@login2
Feature: Login 

As a user, When i go to the login page, 
I should be able to login

Background:
    Given the user in on the login page
    And not common
    And common

Scenario: login as teacher
	When the user login as teacher
	Then the user should be logged in
	
Scenario: Login as a team lead
	When the user logs in as team lead
	Then the user should be logged in