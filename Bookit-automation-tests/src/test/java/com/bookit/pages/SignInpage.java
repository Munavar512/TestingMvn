package com.bookit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bookit.utilities.Driver;

public class SignInpage {
  
  public SignInpage() {
    PageFactory.initElements(Driver.getDriver(), this);
  }  
  
  @FindBy(name="email")
  public WebElement email;

  @FindBy(name = "password")
  public WebElement password;
  
  @FindBy(xpath = "//button[.='sign in']")
  public WebElement signInButton;
  
}


// Shared via @CodeMix. To open this file type:
// /code-open SignInPage.java 1-24 qvlMLQ