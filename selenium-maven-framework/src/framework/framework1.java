package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.WebOrderLoginPage;

public class framework1 {

	 public void setUp() {
		    WebDriverManager.chromedriver().setup();
		    driver=new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		  }
		  
		  @AfterTest
		  public void closeUp() {
		    driver.quit();
		  }
		  
		  @Ignore
		  @Test
		  public void labelsVerification() {
		    loginPage = new WebOrderLoginPage(driver);
		    allOrdersPage = new AllOrdersPage(driver);
		    //Assert.assertEquals(driver.getTitle(), "Web Orders Login","Login Page is not displayed.Application is down");
		    loginPage.username.sendKeys(userID);
		    loginPage.password.sendKeys("test");
		    loginPage.loginButton.click();
		    allOrdersPage.logout();
}
