package com.bookit.tests;

import static io.restassured.RestAssured.when;


import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class reviewAPI {
 
	String baseurl = "http://18.212.184.188:1000/ords/hr";
	@Test
	public void simpleCode() {
		Response response = when().get(baseurl+"/employees/1234");
		
  }
}
