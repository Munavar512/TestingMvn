package com.bookit.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

@Test
public class APIDay2 {
	
	String baseurl = "http://18.212.184.188:1000/ords/hr";
	
	/* Given Accept type is JSON
	 * WHen i send a Get request to url/employees
	 * Then status code is 200
	 * And response content should be JSON
	 * and 100 employees data should be in Json response body
	 * 
	 */
  public void employeesWithParams() {
	  given().accept(ContentType.JSON).and().param("limit", 100)
	  .when().get(baseurl + "/employees/").then()
	  .assertThat().statusCode(200).and()
	  .contentType(ContentType.JSON).and()
	  .body("items.employee_id", Matchers.hasSize(100));
  }
  
  @Test
  public void practice() {
	  Response response = given().accept(ContentType.JSON)
			  .and().pathParam("limit", 110)
			  .when().get(baseurl+"/employees/{id}");
	  
	  JsonPath json = response.jsonPath();
	  
	  System.out.println(json.getString("first_name"));
	  System.out.println(json.getString("job_id"));
	  
	  String actualFirstName = json.getString("first_name");
	  String expectedFirstName = json.getString("first_name");
	  
	  assertEquals(actualFirstName, expectedFirstName);
	  
	}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /* Given Accept type is JSON
	 * WHen i send a Get request to url/employees/110
	 * Then status code is 200
	 * And response content should be JSON
	 * and first_name must be"John"
	 * and email must be "JCHEN"
	 * 
	 */
  
  public void testWithPathParams() {
     given().accept(ContentType.JSON).and()
    .pathParam("id", 110)
    .when().get(baseurl+"/employees/{id}").then()
    .statusCode(200).and().assertThat().contentType(ContentType.JSON)
    .and().assertThat().body("first_name", Matchers.equalTo("John"),"email",Matchers.equalTo("JCHEN"));
  }
  
  /* Given Accept type is JSON
	 * WHen i send a Get request to url/employees/110
	 * Then status code is 200
	 * And response content should be JSON
	 * and first_name must be"John"
	 * and email must be "JCHEN"
	 * 
	 */
  public void testWithJohnPath() {
	  Response response = given().accept(ContentType.JSON)
			  .and().pathParam("id",110).when().get(baseurl+"/employees/{empId");
	  
	  //get json body and assign to Json path object
	  JsonPath json = response.jsonPath();
	  System.out.println(json.getString("first_name"));
	  System.out.println(json.getString("job_id"));
	  System.out.println(json.getString("employee_id"));
	  
	  String expectFirstName = json.getString("first_name");
	  String actualFirstName = ("John");
	  
	  assertEquals(actualFirstName,expectFirstName);
  }
  
  
  public void testWithJsonPath() {
    
    Response response = given().accept(ContentType.JSON)
        .and().pathParam("empId",110).when().get(baseurl+"/employees/{empId}");
    
    //get json body and assign to JsonPath Object 
    JsonPath json = response.jsonPath();
    
    System.out.println(json.getString("first_name"));
    System.out.println(json.getString("job_id"));
    System.out.println(json.getInt("employee_id"));
    
    String actualFirstname = json.getString("first_name");
    String expectedFirstname = "John";
    
    assertEquals(actualFirstname, expectedFirstname);
    
  }
  
  
  /* Given Accept type is JSON
	 * WHen i send a Get request to url/employees/110
	 * Then status code is 200
	 * And response content should be JSON
	 * and first_name must be"John"
	 * and email must be "JCHEN"
	 * 
	 */
  
  public void testJsonPathWithList() {
    
    Map<String,Integer> paramMap = new HashMap<>();
    paramMap.put("limit", 100);
    
    
    Response response = given().accept(ContentType.JSON)
        .and().params(paramMap).when().get(baseurl+"/employees/");
    
    JsonPath json = response.jsonPath();
    
    //verify status code 
    assertEquals(response.statusCode(),200);
    
    //get all employee ids and assign to one list 
    List<Integer> empIDs = json.getList("items.employee_id");
    
    //verify we got 100 employees as a response 
    assertEquals(empIDs.size(), 100);
    
    //get all emails and assign one list  and print them 
    List<String>  emails = json.getList("items.email");
    System.out.println(emails);
    
    //get all employess ID that are greater than 150
    List<Integer> empID2 = json.getList("items.findAll{it.employee_id> 150}.first_name");
    	System.out.println(empID2);
    	
    //get all employees lastname, whose salary greater than 7000
    List<String> lastName7000 = json.getList("items.findAll{it.salary> 7000}.last_name");
      	System.out.println(lastName7000.size());

  }

  
    /* Given Accept type is JSON
	 * WHen i send a Get request to url/employees/140
	 * Then status code is 200
	 * And response content should be JSON
	 * all data should be returned
	 * 
	 */

  public void testWithJSONtoMap() {
	  Response response = given().accept(ContentType.JSON)
			  .and().when().get(baseurl+ "/employees/160");
	  
	  //we convert JSON result to hasmap data structure
	  Map<String,Object> jsonmap = response.as(HashMap.class);
	  System.out.println(jsonmap);
	  System.out.println(jsonmap.get("first_name"));
	  System.out.println(jsonmap.get("salary"));
	  
	  String actualFirstName = (String) jsonmap.get("first_name");
	  
	  assertEquals(actualFirstName, "Joshua");
	  
  }
  
  
  
  public void convertJSONtoListToMap() {
	  Response response = given().accept(ContentType.JSON)
			  .and().get(baseurl+"/departments/");
	  JsonPath json = response.jsonPath();
	  List<Map> result = json.getList("items",Map.class);
	 String actualDepartmentName = (String) result.get(4).get("department_name");
	 String expectDepartmentName = "Shippings";
	 
	 assertEquals(actualDepartmentName, expectDepartmentName);
	 
	 //compare expected and actual names
	 System.out.println(result.get(10));
	 
	 //verify department id 
	 List<Map> departmentId = json.getList("items",Map.class);
	 String actualDepartmentId = (String) departmentId.get(4).get("department_id");
	 System.out.println(departmentId.get(50));
			  
  }
  
  /*Given Content type is JSON
   * And Limit is 10
   * When I send the GET request to url/regions
   * the status code must be 200
   * The I should see following data
   *     1 Europe
   *     2 Americas
   *     3 Asia
   *     4 Middle East and Africa
   */
  
  @Test 
  public void regionTaskV1() {
    
    given().accept(ContentType.JSON).and().params("limit",10)
    .when().get(baseurl+"/regions").then().statusCode(200).and()
    .assertThat().body("items.region_name", Matchers.hasItems("Europe","Americas","Asia","Middle East and Africa"));      
    
  }
 
  @Test
  public void regionTaskV2() {
    
    Response response = given().accept(ContentType.JSON).and().params("limit",10)
        .when().get(baseurl+"/regions");
    
    JsonPath json = response.jsonPath();       //important line
    
    //status code check
    assertEquals(response.statusCode(), 200);
   
    //region verify
    assertEquals(json.getString("items[0].region_name"), "Europe");
    assertEquals(json.getString("items[1].region_name"), "Americas");
    assertEquals(json.getString("items[2].region_name"), "Asia");
    assertEquals(json.getString("items[3].region_name"), "Middle East and Africa");  
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
