package com.bookit.step_definitions;

import com.bookit.pages.MapPage;
import com.bookit.pages.MySelfPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class AccountInfoStepDefs {

	@When("the user goes to myself page")
	public void the_user_goes_to_myself_page() {
		//Open the myself Page 
	 new MapPage().goToSelf(); 
	}

	@Then("the team name {string} should be displayed")
	public void the_team_name_should_be_displayed(String expectedTeamName) {
	   MySelfPage mySelfPage = new MySelfPage();
	   String actualTeamName = mySelfPage.teamName.getText();
	   
	   Assert.assertEquals(expectedTeamName, actualTeamName);
  }
}

