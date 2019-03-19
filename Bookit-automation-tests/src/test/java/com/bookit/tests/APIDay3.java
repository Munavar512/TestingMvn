package com.bookit.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.bookit.beans.Region;
import com.bookit.beans.RegionResponse;
import com.sun.tools.xjc.model.SymbolSpace;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class APIDay3 {
	
	String baseurl = "http://18.212.184.188:1000/ords/hr";
	

	@Test
	public void regionPOSTv1() {

	  String requestBody = "{\"region_id\":15,\"region_name\":\"myregion\"}";
	  
	  Response response = given().accept(ContentType.JSON)
	            .and().contentType(ContentType.JSON).and().body(requestBody)
	            .when().post(baseurl+"/regions/");
	      
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "myregion");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 15);

	}
	
	@Test
	public void regionPOSTv2() {
	  
	  //not a good way to pass JSON Request BODY
	  //String requestBody = "{\"region_id\":15,\"region_name\":\"myregion\"}";
	  
	  Map requestMap = new HashMap<>();
	  
	  requestMap.put("region_id", 124);
	  requestMap.put("region_name", "mytest");

	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
	            .and().contentType(ContentType.JSON).and().body(requestMap)
	            .when().post(baseurl+"/regions/");
	      
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "mytest");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 124);

	}
	
	@Test
	public void regionPOSTv3() {
	  
	  Region region = new Region();
	  
	  region.setRegion_id(111);
	  region.setRegion_name("mynewregion");
	  
	  
	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
	            .and().contentType(ContentType.JSON).and().body(region)
	            .when().post(baseurl+"/regions/");
	      
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  JsonPath json =response.jsonPath();
	  //verify region name
	  assertEquals(json.getString("region_name"), "mynewregion");
	  //verify region id
	  assertEquals(json.getInt("region_id"), 111);

	}
	
	@Test
	public void regionPOSTv4() {
	  
	  Region region = new Region();
	  
	  region.setRegion_id(156);
	  region.setRegion_name("mynewregion");
	  
	  
	  //map will be converted to JSON 
	  Response response = given().accept(ContentType.JSON)
	            .and().contentType(ContentType.JSON).and().body(region)
	            .when().post(baseurl+"/regions/");
	      
	  //verify status code for POST request
	  assertEquals(response.statusCode(), 201);
	  
	  //create our POJO object for response body
	  
	  RegionResponse regionresponse = response.as(RegionResponse.class);
	  
	  assertEquals(regionresponse.getRegion_id(), region.getRegion_id());
	  assertEquals(regionresponse.getRegion_name(), region.getRegion_name());
	}  	  
	  
	  
  }
  

