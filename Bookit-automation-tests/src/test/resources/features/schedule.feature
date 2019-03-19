Feature: View schedule

@schedule @smoke
	Scenario: My team schedule
	Given the user is on the login page
	And the user login as teacher
	When go to my schedule
	Then I should be able to see the reserbations for my team
	