package LoginPage2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class webOrderLoginPage {

	public webOrderLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="uscodreading")
	public WebElement CurrentOR;
	
	@FindBy(id="uspodreading")
	public WebElement PreviousOR;
	
	@FindBy(id="usgasputin")
	public WebElement gallons;


	public Object invalidLoginMsg;
	
	
	
}
