package assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Step2 {

	WebDriver driver = null;

	@BeforeTest
	public void testLaunchBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.dice.com");
	}

	@Test(dataProvider="getdata")
	public void testInputBoxes(String job,String location) {
		driver.findElement(By.id("search-field-keyword")).sendKeys(job);
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();
		
		String result = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(result);
		String replaced = result.replaceAll(",", "");
		
		int number = Integer.parseInt(replaced);
		if (number > 1000) {
			System.out.println("More than 1000");
		} else {
			System.out.println("Less than 1000");
		}
		Assert.assertTrue(number > 1000, "Less than 1000");
		driver.navigate().back();
	}
	
	@DataProvider
	public Object[][] getdata(){
		Object[][] data  = new Object[3][2];
		
		data[0][0] = "java developer";
		data[0][1] = "VA";
		
		data[1][0] = "javascript developer";
		data[1][1] = "VA";
		
		data[2][0] = "ruby developer";
		data[2][1] = "VA";
		
		return data;
				
	}

	@AfterTest
	public void closeWindows() {
		driver.quit();
	}

}
