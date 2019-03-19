package POMdesign;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



import io.github.bonigarcia.wdm.WebDriverManager;
import pages.WebOrderLoginPage;


public class WebOrderLoginTest {
	
	
		WebDriver driver;

		@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		}
		
	@AfterTest	
	public void closeUp() {
		driver.quit();
	}	
	
	@Test	
	public void login() {
		    
     driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Mike");
	 driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("Smith");
     driver.findElement(By.name("ctl00$MainContent$login_button")).click();
}
	
	@Test
	public void loginUsingPom() {
		
		WebOrderLoginPage loginPage = new WebOrderLoginPage(driver);
		loginPage.username.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
	}
		
	@BeforeTest	
	public void checkUp() {
			driver.close();
		}
		
	
	  @Test
	  public void invalidLogin() {
	    WebOrderLoginPage loginPage = new WebOrderLoginPage(driver);
	    loginPage.username.sendKeys("Tester123");
	    loginPage.password.sendKeys("test");
	    loginPage.loginButton.click();
	    
	    String errorMsg = loginPage.invalidLoginMsg.getText();
	    Assert.assertEquals("Invalid Login or Password.", errorMsg); 
	  }
		
	}

