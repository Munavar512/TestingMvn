package com.gassCalculateTestCases;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDFTest {
  
  WebDriver driver;
  Xls_Reader xl = new Xls_Reader("/Users/mina/Selenium/testing-maven/selenium-maven-framework1/src/test/resources/testData.xlsx");
  
  
  
  @BeforeMethod
  public void setup() {
    
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("https://www.calculator.net/gas-mileage-calculator.html");
  }
  
  @AfterMethod
  public void closeup() {
    driver.quit();
  }
  
  @Test
  public void calculateTest() {
    
    GasCalculatorPage calculataPage=new GasCalculatorPage(driver);
    
//    double currentOr=3456;
//    double previousOr=2345;
//    double gas=30;
    
    int rowcount=xl.getRowCount("Data");
    
    for(int i=2;i<=rowcount;i++) {
      
      String currentOr = xl.getCellData("Data", "CurrentOR", i);
      String previousOr = xl.getCellData("Data", "PreviousOR", i);
      String gas = xl.getCellData("Data", "Gas", i);

      
      
      calculataPage.currentOdometer.clear();
      calculataPage.currentOdometer.sendKeys(currentOr);
      calculataPage.previousOdometer.clear();
      calculataPage.previousOdometer.sendKeys(previousOr);
      calculataPage.gas.clear();
      calculataPage.gas.sendKeys(gas);
      calculataPage.calculate.click();
    
      String[] actualResult = calculataPage.result.getText().split(" ");
      xl.setCellData("Data", "Actual", i, actualResult[0]);
          
      System.out.println(actualResult[0]);
      
      double co = Double.parseDouble(currentOr);
      double po = Double.parseDouble(previousOr);
      double gs = Double.parseDouble(gas);
    
      double expectedResult = (co-po)/gs;
      DecimalFormat df = new DecimalFormat("0.00");
      String expectedResult2=String.valueOf(df.format(expectedResult));
      xl.setCellData("Data", "Expected", i, expectedResult2);
    
      if(actualResult[0].equals(expectedResult2)) {
        xl.setCellData("Data", "Status", i, "PASS");
      }else {
        xl.setCellData("Data", "Status", i, "FAIL");
      }
      
      xl.setCellData("Data", "Time", i, LocalDateTime.now().toString());
      
    }
    
    
  }

  
  

}