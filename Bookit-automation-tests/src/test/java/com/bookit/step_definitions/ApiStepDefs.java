package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DBUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class ApiStepDefs {
	
	String token;
	JsonPath json;
	String fullname;
	String email;
	
	@Given("I logged Bookit api using {string} and {string}")
	public void i_logged_Bookit_api_using_and(String username, String password) {
		
		
		//get token url from configuration file and assign to new string variable 
		String tokenurl = ConfigurationReader.getProperty("qa1_tokenurl");

		//create one map to keep query param information inside it 
		Map<String,String> loginParams = new HashMap<>();
		 loginParams.put("email", username);
		 loginParams.put("password", password);
		 
		//send get request to get accessToken
		 Response response = given().accept(ContentType.JSON).and().params(loginParams)
		     .when().get(tokenurl);

		 //verify status code 
		  assertEquals(response.statusCode(), 200);
		  
		  //convert response to json format 
		  JsonPath json = response.jsonPath();
		  //get token value from response JSON Body and assign to one variable 
		  token = json.getString("accessToken");
		  
		  fullname=password;
		  email=username;
		  
	}

	@When("I get the current user information from api")
	public void i_get_the_current_user_information_from_api() {
	     
	  String url = ConfigurationReader.getProperty("qa1_baseurl") +"/students/me";
	  
	  Response response = given().accept(ContentType.JSON).and().header("Authorization",token)
	        .when().get(url);
	  
	  //status code
	  assertEquals(response.statusCode(), 200);
	  json = response.jsonPath();

	}

	@Then("the information about current user should be returned")
	public void the_information_about_current_user_should_be_returned() {
		
		//compare api first name and last and with fullname (password)
	   String actualFullname = json.getString("firstName").toLowerCase()+ json.getString("lastName").toLowerCase();
	   
	   //compare full name form api and feature file
	   assertEquals(fullname, actualFullname);

	}
	
	@Then("the information about current user from api and database shoud be match")
	public void the_information_about_current_user_from_api_and_database_shoud_be_match() {
	  
		//get your query
		String query = "SELECT id,firstname,lastname,role\n"+"FROM users\n"+"WHERE email = '"+email+"'";
		
		//send query to database get the result and assign java object
		Map<String,Object> result = DBUtils.getRowMap(query);
	 	
        SelfPage selfPage = new SelfPage();
	    
	    //wait until got the user information table
	    BrowserUtils.waitFor(2);

	    //getting values from UI and assigning to variables 
	    String actualUIFullname = selfPage.name.getText();
	    String actualUIRole = selfPage.role.getText();
		
		//assign database information to expected variable
		String expectedID = (String)result.get("id");
		String expectedFirstName = (String)result.get("firstname");
		String expectedLastName = (String)result.get("lastname");
		String expectedRole = (String)result.get("role");
		
		int actualID = json.getInt("id");
		String actualFirstName = json.getString("firstname");
		String actualLastName = json.getString("lastname");
		String actualRole = json.getString("role");
		
		
		//compare data base information against API information
		assertEquals(expectedID,actualID);
		assertEquals(expectedFirstName,actualFirstName);
		assertEquals(expectedLastName,actualLastName);
		assertEquals(expectedRole,actualRole);
		
		//UI to API 
		String expectedFullname =expectedFirstName+" "+expectedLastName;
		 
		String apiFullname = actualFirstName.toLowerCase()+actualLastName.toLowerCase();
		assertEquals(actualUIFullname,apiFullname);
		assertEquals(actualUIRole,actualRole);
		
		//database to UI
		assertEquals(expectedFullname,actualUIFullname);
		assertEquals(expectedRole,actualUIRole);
		
		
	}
	
	@Then("UI,API and Database user information must be match")
	public void ui_API_and_Database_user_information_must_be_match() {
	  
		
		
	}


}
