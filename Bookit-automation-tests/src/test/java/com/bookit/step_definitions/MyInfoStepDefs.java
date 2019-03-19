package com.bookit.step_definitions;

import com.bookit.pages.SignInpage;
import com.bookit.pages.TeamPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DBUtils;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class MyInfoStepDefs {

	@Given("user logs in using {string} {string}")
	public void user_logs_in_using(String email, String password) {
	    Driver.getDriver().get(ConfigurationReader.getProperty("url"));
	    Driver.getDriver().manage().window().maximize();
	    SignInpage signInPage = new SignInpage();
	    signInPage.email.sendKeys(email);
	    signInPage.password.sendKeys(password);
	    signInPage.signInButton.click();
	    BrowserUtils.waitFor(2);
	          
	}

  @When("user is on the my self page")
  public void user_is_on_the_my_self_page() {
     SelfPage selfPage = new SelfPage();
     selfPage.goToSelf();
    // BrowserUtils.waitFor(2);
     
  }

  @Then("user info should match with the database records for {string}")
  public void user_info_should_match_with_the_database_records_for(String email) {
	  
	  //writing our query
	    String query = "SELECT firstname,lastname,role\n" + 
	        "FROM users\n" + 
	        "WHERE email = '"+email+"'";
	    //get value from database and assign to map
	    Map<String,Object> result = DBUtils.getRowMap(query);
	    
	    //assigning database values to variables
	    String expectedFirstName = (String) result.get("firstname");
	    String expectedLastName = (String) result.get("lastname");
	    String expectedRole = (String) result.get("role");
	    
	    String expectedFullname =expectedFirstName+" "+expectedLastName;
	    //----------------UI----------------------------
	    
	    SelfPage selfPage = new SelfPage();
	    
	    //wait until got the user information table
	    BrowserUtils.waitFor(2);

	    //getting values from UI and assigning to variables 
	    String actualFullName = selfPage.name.getText();
	    String actualRole = selfPage.role.getText();
	    
	    
	    System.out.println(actualFullName);
	    System.out.println(actualRole);
	    
	    
	    // compare UI and DATABASE values  
	    Assert.assertEquals(actualFullName, expectedFullname);
	    Assert.assertEquals(actualRole, expectedRole);
  		}
  

  		@When("user is on the my team page")
  			public void user_is_on_the_my_team_page() {
  			SelfPage selfPage = new SelfPage();
  			selfPage.goToTeam();
  			BrowserUtils.waitFor(2);
  		}
  		

  		@Then("team info should match with the database records {string}")
  		//write query
  			public void team_info_should_match_with_the_database_records(String email) {
  			String query = "SELECT firstname, role\n" + 
  							"FROM users\n" + 
  							"WHERE team_id = (SELECT team_id FROM users WHERE EMAIL = '"+email+"')";
  			
  		//assign query result to list of maps
  			List<Map<String,Object>> result = DBUtils.getQueryResultMap(query);
  			
  		//import teamPage and create teamPage object to 
  			TeamPage teamPage = new TeamPage();
  			
  		//empty list for actual name
  			List<String> actualNames = new ArrayList<>();
  
  			for(WebElement el: teamPage.teamMemberNames) {
  				
  		//get each web element name and add to actualNames list
  				actualNames.add(el.getText());	
  			}
  		
  		//empty list for actual roles
  			List<String> actualRoles = new ArrayList<>();
  			for(WebElement el: teamPage.teamMemberRoles) {   //el is element
  				actualRoles.add(el.getText());	
  			}
  			
  		//before one by one comparison, check the number of results from DB and UI
  			Assert.assertEquals(result.size(), actualNames.size());  //check compare the database and UI 
  			
  			for(Map<String,Object> row : result) {
  				String firstname =(String) row.get("firstname");
  				Assert.assertTrue(actualNames.contains(firstname));
  			}
  		
  			for(Map<String,Object> row : result) {
  				String role =(String) row.get("role");
  				Assert.assertTrue(actualRoles.contains(role));
  		    }
  			
  			
  
	}
}
