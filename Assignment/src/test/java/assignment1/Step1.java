package assignment1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Step1 {
	
WebDriver driver = null;
	
	@BeforeTest
	public void testLaunchBrowser () {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.dice.com");
	}
	
	@Test
	public void testTitle () {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertTrue(title.equals("Find Jobs in Tech | Dice.com"),"Error");
		Assert.assertEquals(title, "Find Jobs in Tech | Dice.com","Error");
	}
	
	@AfterTest
	public void closeBrowser () {
		driver.close();
		driver.quit();
	}
	
}
