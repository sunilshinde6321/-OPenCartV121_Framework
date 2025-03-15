package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage
{

	public Loginpage(WebDriver driver) 
	{
		super(driver);
		
	}
	@FindBy(xpath = "//input[@name='email']") WebElement txtemail;
	@FindBy(xpath = "//input[@name='password']")WebElement txtpassword;
	@FindBy(xpath = "//input[@value='Login']")WebElement btnlogin;

	
	public void setEmail(String emial)
	{
		txtemail.sendKeys(emial);
	}
	public void setPassword(String password)
	{
		txtpassword.sendKeys(password);
	}
	public void clickloginbtn()
	{
		btnlogin.click();
	}
}
