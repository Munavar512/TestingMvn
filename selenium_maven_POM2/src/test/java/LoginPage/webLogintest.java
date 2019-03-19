package LoginPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import LoginPage2.webOrderLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class webLogintest {

	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.calculator.net/gas-mileage-calculator.html");
	}
	@AfterTest
	public void closeup() {
		driver.quit();
	}
	
	@Test
	public void login() {
		
		driver.findElement(By.id("uscodreading")).sendKeys("1234");
		driver.findElement(By.id("uspodreading")).sendKeys("123");
		driver.findElement(By.id("usgasputin")).sendKeys("879");
		driver.findElement(By.cssSelector("#standard>tbody>:nth-child(5)>td>input")).click();
		
	}
	
	@Test
	public void loginUsingPOM() {
		webOrderLoginPage loginPage = new webOrderLoginPage(driver);
		loginPage.CurrentOR.sendKeys("1234");
		loginPage.PreviousOR.sendKeys("1234");
		loginPage.gallons.sendKeys("876");
	}
	
	@Test
	public void invalidLogin() {
		webOrderLoginPage loginPage = new webOrderLoginPage(driver);
		loginPage.CurrentOR.sendKeys("1234");
		loginPage.PreviousOR.sendKeys("1234");
		loginPage.gallons.sendKeys("876");
		

		
	}
	
	
	
}
