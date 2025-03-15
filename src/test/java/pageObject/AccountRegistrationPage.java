package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}

	@FindBy(xpath = "//input[@name='firstname'] ") WebElement txtfirstname;
	
	@FindBy(xpath = "//input[@name='lastname'] ") WebElement txtLasttname;
	
	@FindBy(xpath = "//input[@name='email'] ") WebElement txtemailname;
	
	@FindBy(xpath = "//input[@name='telephone'] ") WebElement txtTephonename;
	
	@FindBy(xpath = "//input[@name='password'] ") WebElement txtpassword;
	
	@FindBy(xpath = "//input[@name='confirm'] ") WebElement txtconfirmpasswod;
	
	@FindBy(xpath = "//input[@name='agree']")WebElement selcheckbox;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")WebElement btncontinue;
	
	@FindBy(xpath = " //h1[normalize-space()='Your Account Has Been Created!']") WebElement msgconfirmation;
	
	public void setFirstName(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	public void setLastName(String lname) 
	{
		txtLasttname.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txtemailname.sendKeys(email);
	}
	public void setTelephone(String tel) 
	{
		txtTephonename.sendKeys(tel);
	}
	public void setPassword(String pass)
	{
		txtpassword.sendKeys(pass);
	}
	public void setConfirmPassword(String cfmpass)
	{
		txtconfirmpasswod.sendKeys(cfmpass);
		
	}
	public void selectCheckbox()
	{
		selcheckbox.click();
	}
	
	public void btnContine()
	{
		btncontinue.click();
	}
	public String getconfirmationMsg() 
	{
		try {
			return(msgconfirmation.getText());
			
		} catch (Exception e) 
		{
			return(e.getMessage());
		}
		
	}
}
