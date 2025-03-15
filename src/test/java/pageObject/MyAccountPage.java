package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);

	}

	@FindBy(xpath = "//h2[text()='My Account']") WebElement msgheadling;
	
	//@FindBy(xpath = "//a[text()='Logout']") WebElement lnklogout;  
	 @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout'] ")  WebElement lnklogout;         // add in step 6 DDT

	public boolean isMyAccountPageExit() 
	{
		try 
		{
			return(msgheadling.isDisplayed());
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	public void Clicklogout()
	{
	 lnklogout.click();	
	}

}
