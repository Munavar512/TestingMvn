package com.bookit.beans;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class review {
	
 String baseurl = "18.212.184.188:1000/ords/hr";
 
 
  @Test
  public void FirstAPI(){
	  
	given().accept(ContentType.JSON).when().get(baseurl+"/employees/110").
	 then().statusCode(200).contentType(ContentType.JSON).and().body("first_name",Matchers.equalTo("John")) ;
	
	}
  
  @Test
  public void verifyCountries() {
	  given().accept(ContentType.JSON).when().get(baseurl+"/regions/").then().statusCode(200)
	  .and().contentType(ContentType.JSON).body("item.region_id",Matchers.hasItem(4))
	  .and().assertThat().body("items.region_name", Matchers.hasItem("Europs"))
	  .and().body("items.region_name", Matchers.hasItems("Americans","Middle East and Africa"));
  }
  }
