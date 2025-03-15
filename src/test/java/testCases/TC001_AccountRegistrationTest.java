package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
    @Test(groups = {"Regression","Master"})
    public void Verify_account_registration() throws InterruptedException {
        try {
            logger.info("*********** Starting TC001_AccountRegistrationTest ************");
           

            HomePage hp = new HomePage(driver);
            hp.clickmyaccount();
            logger.info("Clicked on My Account Page");  // Changed from logger.error() to logger.info()

            hp.clickRegister();
            logger.info("Clicked on Register Page");  // Changed from logger.error() to logger.info()

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Providing customer details ");
            regpage.setFirstName(randomstring().toUpperCase());
            regpage.setLastName(randomstring().toUpperCase());
            regpage.setEmail(randomstring() + "@gmai.com");
            regpage.setTelephone(randomNumber());
            regpage.setPassword("Sunil@123");
            regpage.setConfirmPassword("Sunil@123");
            regpage.selectCheckbox();
            regpage.btnContine();

            logger.info("Validating expected messages..");
            String confmsg = regpage.getconfirmationMsg();
            if (confmsg.equals("Your Account Has Been Created!")) 
            {
                Assert.assertTrue(true);
                logger.info("Test case passed");  // Add a success message
            }
            else 
            {
                logger.error("Test case failed - Expected message not found");
                Assert.fail("Expected message not found");
            }
        } catch (Exception e) 
        {
            logger.error("Test case encountered an exception: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }

        logger.info("*********** Finished TC001_AccountRegistrationTest ************");
    }
}
