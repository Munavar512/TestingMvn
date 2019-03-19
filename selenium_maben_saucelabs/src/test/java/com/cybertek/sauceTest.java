package com.cybertek;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class sauceTest {

	public static final String USERNAME = "Munavar";
    public static final String ACCESS_KEY = "81bccdbb-5aec-4949-a9f0-0d7c041c98e3";
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    
    
    
	@Test
	public void login() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 7");
		caps.setCapability("version", "56.0");
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL(URL),caps);
		driver.get("https://www.cnn.com/");
		System.out.println(driver.getTitle());
		driver.quit();
		
	}
}
