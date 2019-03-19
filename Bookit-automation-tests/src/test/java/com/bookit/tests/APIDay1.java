package com.bookit.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APIDay1 {

	String baseurl = "http://18.212.184.188:1000/ords/hr";
	/* When i send a GET request to URL/employees
	 * Then response STATUS should be 200 success request 
	 */
     //simple smoke test of API
	@Test
	public void simpleStatusCode() {
		when().get("http://18.212.184.188:1000/ords/hr/employees").then().statusCode(200);   
   }
	
	
	/* When I send a GET request to URL/countries
	   * Then I should see the JSON response
	   */
	@Test
	public void printResponse() {
		when().get("http://18.212.184.188:1000/ords/hr/countries").body().prettyPrint();
	}
	
	
	 /* When I send a GET request to URL/countries
	   * And Accept type is "application/json"
	   * Then I should see the JSON response
	   */
	@Test
	public void getWithHeaders() {
		given().accept(ContentType.JSON)
		.when().get("http://18.212.184.188:1000/ords/hr/countries")
		.then().statusCode(200);
	}
	
	
	  //negative test cases:
	 /* When I send a GET request to URL/employees/1234
	   * Then response STATUS CODE should be 404
	   */
	@Test
	public void nagativeGet() {
		when().get("http://18.212.184.188:1000/ords/hr/employees1234").then().statusCode(404);
	}
	
	
	/* When i send GET request to URL/employees/1234
	 * Then status code is 404
	 * And Response Body error message should includes "Not found"
	 */
	@Test
	public void nagativeGetWithBody() {
		Response response = when().get("http://18.212.184.188:1000/ords/hr/employees1234");
		
		//status code check
		assertEquals(response.statusCode(),404);
		
		//check body includes "Not Found"
		assertTrue(response.asString().contains("Not Found"));
		
		//print the response
		response.prettyPrint();
	}
	
	
	
	/*When I send GET requestto URL/employees/110
	 * ANd accept type is JSON
	 * And Response content should be Json
	 */
	@Test
	public void verifyContenType() {
		given().accept(ContentType.JSON)
		.when().get("http://18.212.184.188:1000/ords/hr/employees/110")
		.then().statusCode(200).and().contentType(ContentType.JSON);
	}
	
	
	/* When i send A GET request to URL/employees/110
	 * Then STATUS code is 200
	 * And response content should be JSON
	 * and first name should be "John"
	 */
	@Test
	public void verifyFirstName() {
		given().accept(ContentType.JSON)
		.when().get(baseurl+"/employees/110")
		.then().statusCode(200).and().contentType(ContentType.JSON).
		 and().body("first_name",Matchers.equalTo("John"));	
	}

	
	/* When i send A GET request to URL/employees/150
	 * Then STATUS code is 200
	 * And response content should be JSON
	 * and last name should be "Tucker"
	 * and JOB_ID should be "SA_REP"
	 */
	
	
	@Test 
	public void verifyFirstNameAndJobId() { 
		given().accept(ContentType.JSON).
		when().get(baseurl+"/employees/150").
	    then().statusCode(200).and().contentType(ContentType.JSON)
	    .and().body("last_name", Matchers.equalTo("Tucker"))
	    .and().body("job_id", Matchers.equalTo("SA_REP")); 
	}
	
	
	
	/* When i send A GET request to URL/employees/150
	 * Then STATUS code is 200
	 * And response content should be JSON
	 * and 4 regions should be returned
	 */	
	@Test
	public void verifyRegionCount() {
	given().accept(ContentType.JSON)
	.when().get(baseurl+"/regions")
	.then().assertThat().statusCode(200)
	.and().assertThat().contentType(ContentType.JSON)
	.and().assertThat().body("items.region_id", Matchers.hasSize(4))
	.and().assertThat().body("items.region_name", Matchers.hasItem("Europe"))
	.and().body("items.region_name", Matchers.hasItems("Americas","Middle East and Africa"));  
	
}	
	
}
