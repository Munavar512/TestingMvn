package com.gasCalculator.testCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.gasCalculator.pages.GasCalculatorPage;
import com.zoho.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDFtest{

	WebDriver driver;

	Xls_Reader xl= new Xls_Reader("/Users/mina/Selenium/testing-maven/GasCalculate-DDF/src/test/resources/testData.xlsx");
	
	
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.calculator.net/gas-mileage-calculator.html");
		
	}
	
	@AfterMethod
	public void closeUp() {
		driver.quit();
	}
	
	@Test
	public void calculateTest() {
		
		GasCalculatorPage calculatePage = new GasCalculatorPage(driver);
		
//		double currentOr = 5432;
//		double previousOr = 2345;
//		double gas = 30;
		
		int rowcount = xl.getRowCount("Data");
		
		for(int i=2; i<rowcount; i++) {
			String currentOr = xl.getCellData("Data", "CurrentOr", i);
			String previousOr = xl.getCellData("Data", "PreviousOr", i);
			String gas = xl.getCellData("Data","Gas", i);
//			String expected = xl.getCellData("Data", "Expected", i);
//			String actual = xl.getCellData("Data", "Actual", i);
//			String status = xl.getCellData("Data", "Status", i);
//			String time = xl.getCellData("Data", "Time", i);
			
		
		
		calculatePage.currentOdometer.clear();
		calculatePage.currentOdometer.sendKeys(currentOr);
		calculatePage.previousOdometer.clear();
		calculatePage.currentOdometer.sendKeys(previousOr);
		calculatePage.gas.clear();
		calculatePage.gas.sendKeys(gas);
		calculatePage.calculate.click();
		
		String[] actualResult=calculatePage.result.getText().split(" ");
		xl.setCellData("Data", "Actual", i, actualResult[0]);
		System.out.println(actualResult[0]);
		
		double co = Double.parseDouble(currentOr);
		double po = Double.parseDouble(previousOr);
		double gs = Double.parseDouble(gas);
				
		double expectedResult = (co-po)/gs;
		DecimalFormat df = new DecimalFormat("0.00");
		String expectedResult2 = String.valueOf(df.format(expectedResult));
		xl.setCellData("Data","Expected", i,expectedResult2);
		
		if(actualResult[0].equals(expectedResult2)) {
			xl.setCellData("Data", "Status", i, "PASS");
		}else {
			xl.setCellData("Data", "Status", i, "Faild");
		}
		
		}
	}
	
}
