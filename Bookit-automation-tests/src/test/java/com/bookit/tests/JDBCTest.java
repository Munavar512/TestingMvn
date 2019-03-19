package com.bookit.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.bookit.utilities.DBUtils;

public class JDBCTest {
	
	
	String dbUrl = "jdbc:postgresql://localhost:5432/hr" ;
	String dbUsername = "postgres";
	String dbPassword = "abc";
	
	
	
 // @Test
  public void PostGreSQL() throws SQLException {
	  Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	  Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
	  ResultSet resultset = statement.executeQuery("Select * from countries");
	  
//_------------------
//	  resultset.next();
//	  System.out.println(resultset.getString(1) + "-" + resultset.getString("country_name")+ "-" + resultset.getInt(3));
	
//------------------------
//	  while(resultset.next()) {
//		  System.out.println(resultset.getString(1) + "-" + resultset.getString("country_name")+ "-" + resultset.getInt(3));
//	  }
	
//----------------------
//	  resultset.next();
//	  resultset.next();
//	  resultset.next();
//	  resultset.next();
//	  System.out.println(resultset.getRow());

//-----------------------
// find out record in the result:
//	  resultset.last();
//	  int rowsCount = resultset.getRow();
//	  System.out.println("Total number of rows: "+rowsCount);

//-----------------------
// how to move first row and loop everything again after you are at last row?
//	  resultset.first();   //1 
//	  while(resultset.next()) {  //print start with second row
//		  System.out.println(resultset.getString(1) + "-" + resultset.getString("country_name")+ "-" + resultset.getInt(3));  
//	  }

//-----------------------
//	  resultset.beforeFirst();  //0
//	  while(resultset.next()) {   //1
//		  System.out.println(resultset.getString(1) + "-" + resultset.getString("country_name")+ "-" + resultset.getInt(3)); 
//	  }
	  
	  
	  
	  resultset.close();
	  statement.close();
	  connection.close();  
  }
  
  
  //@Test
  public void JDBCMetaData() throws SQLException {
	  Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	  Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
	  ResultSet resultset = statement.executeQuery("Select * from countries");
	  
	  //database metadata (create object)
	  DatabaseMetaData dbMetaData = connection.getMetaData();
	  
	  //with username are we using?
	  System.out.println("User: " + dbMetaData.getUserName());
	  
	  //database product name
	  System.out.println("Database: " + dbMetaData.getDatabaseProductName());
	  
	  // database product version
	  System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
	  
	  //resultset metadata create object
	  ResultSetMetaData rsMetaData = resultset.getMetaData();
	  
	  //how many columns we have?
	  System.out.println("Columns count: "+ rsMetaData.getColumnCount());
	  
	  //get first column name
	  System.out.println(rsMetaData.getColumnName(1));
	  
	  //get table name
	  System.out.println(rsMetaData.getTableName(1));
	  
	  //print all column name using loop 
	  int columCount = rsMetaData.getColumnCount();
	  for(int i=1; i<columCount; i++) {
		 System.out.println(i);
	 }
	  
  } 
  
  
	 // @Test
//	  public void DBUtil() throws SQLException {
//	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//	    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
//	    ResultSet resultset = statement.executeQuery("SELECT first_name, last_name, salary , job_id FROM Employees\n" + 
//	    		"Limit 5; ");
//	    
//	    //database metadata(create object)
//	    DatabaseMetaData dbMetadata =connection.getMetaData();
//	        
//	    //resultset metadata create object
//	    ResultSetMetaData rsMetadata = resultset.getMetaData();
//	    
//	    //our main structure, it will keep whole query result
//	    List<Map<String,Object>> queryData = new ArrayList<>();
//	    
//	    //we will add the first row data to this map
//	    Map<String,Object> row1 = new HashMap<>();
//	    
//	    //print the first row
//	    resultset.next();
//	    
//	    //key is column name, value is value of that column
//	    row1.put("first_name", resultset.getObject("first_name"));
//	    row1.put("last_name", resultset.getObject("last_name"));
//	    row1.put("Salary", resultset.getObject("salary"));
//	    row1.put("Job_id", resultset.getObject("job_id"));
//	    
//	    System.out.println(row1.toString());
//	    
//	    //push row1 map to list a whole row
//	    queryData.add(row1);
//	    
//	    System.out.println(queryData.get(0).get("first_name"));
//	    
//	    //other way
//	    //--------------ADDING ONE MORE ROW----------
//	    Map<String,Object> row2 = new HashMap<>();
//	    
//	    resultset.next();
//	    
//	    row2.put("first_name", resultset.getObject("first_name"));
//	    row2.put("last_name", resultset.getObject("last_name"));
//	    row2.put("Salary", resultset.getObject("salary"));
//	    row2.put("Job_id", resultset.getObject("job_id"));
//	    
//	    queryData.add(row2);
//
//	    System.out.println("First Row: "+queryData.get(0).toString());
//	    System.out.println("Second Row: "+queryData.get(1).toString());   
//	  }
	  
	  
	  
	  //@Test
	  public void DBUtilDynamic() throws SQLException {
	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
	    ResultSet resultset = statement.executeQuery("SELECT * FROM countries");
	    
	    DatabaseMetaData dbMetadata =connection.getMetaData();
	    ResultSetMetaData rsMetadata = resultset.getMetaData();

	    Map<String,Object> row1 = new HashMap<>();
	    
	  
	    
	    //Main list
	    List<Map<String,Object>> queryData = new ArrayList<>();
	    
	    int colCount = rsMetadata.getColumnCount();
	    
	    while(resultset.next()) {
	    	Map<String,Object> row = new HashMap<>();
	    	
	    	  for(int i=1; i<=colCount; i++) {
	    		row.put(rsMetadata.getColumnName(i), resultset.getObject(i));
	    		 
	    	 }
	    	
	    	queryData.add(row);
	    }
	 System.out.println(queryData.get(0));
	 System.out.println(queryData.get(1));
	 System.out.println(queryData.get(2));
	 System.out.println(queryData.get(3));
	 
	 
	 // print each countries name in the list
	 for(Map<String,Object> map:queryData) {
		 System.out.println(map.get("country_name"));
	 }
	
	    resultset.close();
	    statement.close();
	    connection.close();
	    
	  }
	  
	  
	  
	  @Test
	  public void useDBUtils() {
		  
		  //create connection with given information
		  DBUtils.createConnection();
		  
		  String query = "SELECT first_name, last_name, salary, job_id FROM Employees";
		  List<Map<String,Object>> queryData = DBUtils.getQueryResultMap(query);
		  Map<String,Object> onerowresult = DBUtils.getRowMap(query);
		  
		  
		  //first row salary value
		  System.out.println(queryData.get(0).get("salary"));
		  
		  //close connection
		  DBUtils.destroy();
				  
		  
	  }
  }

