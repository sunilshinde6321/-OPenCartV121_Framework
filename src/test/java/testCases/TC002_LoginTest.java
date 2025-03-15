package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.Loginpage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups = {"sanity","Master"})
	public void Verify_Login()
	{
		logger.info("***********Starting TC002_LoginTest***********");

		try
		{
			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickmyaccount();
			hp.ClickLoginpage();
			// Login page
			Loginpage lp = new Loginpage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("pasword"));
			lp.clickloginbtn();

			// MyAccount

			MyAccountPage mcc = new MyAccountPage(driver);
			boolean targetpage = mcc.isMyAccountPageExit();
			Assert.assertTrue(targetpage);

		}
		catch (Exception e) 
		{
			Assert.fail();
		}
		logger.info("***********Finished TC002_LoginTest***********");

	}
}
