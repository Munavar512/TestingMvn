package com.gassCalculatePage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LginWebPage{

	public class WebOrderLoginPage {
		
		public WebOrderLoginPage(WebDriver driver) { 	
		 PageFactory.initElements(driver, this);   
		}
		
		
		@FindBy(id="uscodreading")   
		public WebElement miles1; 
		
		@FindBy(id="uspodreading") 
		public WebElement miles2;
		
		@FindBy(id="usgasputin") 
		public WebElement gallons;
		
	    @FindBy(css="#standard > tbody  input[type=\"image\"]")
		public WebElement button;
		
		@FindBy(linkText="24.00 miles per gallon")
		public WebElement LoginMsg;
		
	}
}
