package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.Loginpage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "datadriven")
    public void Verify_LoginDDT(String email, String pass, String exp) throws InterruptedException {
        logger.info("************ Starting TC003_LoginDDT **************");

        try {
            // Home page
            HomePage hp = new HomePage(driver);
            hp.clickmyaccount();
            hp.ClickLoginpage();

            // Login page
            Loginpage lp = new Loginpage(driver);
            lp.setEmail(email);
            lp.setPassword(pass);
            lp.clickloginbtn();

            // MyAccount
            MyAccountPage mcc = new MyAccountPage(driver);
            boolean targetPage = mcc.isMyAccountPageExit();
            logger.info("Login attempt for email: " + email + " | Expected: " + exp + " | Actual: " + (targetPage ? "Success" : "Failed"));

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
             
                    mcc.Clicklogout();
                    Assert.assertTrue(true);
                } else {
                    logger.error("❌ Expected: Valid | Actual: Failed -> Test Failed");
                    Assert.fail("Login failed for a valid user.");
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    logger.error("❌ Expected: Invalid | Actual: Success -> Test Failed");
                    mcc.Clicklogout();
                    Assert.fail("Login succeeded for an invalid user.");
                } else {
                    logger.info("✅ Expected: Invalid | Actual: Failed -> Test Passed");
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            logger.error("❌ Test Failed for email: " + email + " due to Exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Exception occurred: " + e.getMessage());
        }

        logger.info("************ Finished TC003_LoginDDT **************");
    }
}
