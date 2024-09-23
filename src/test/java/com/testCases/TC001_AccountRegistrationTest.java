package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.AccountRegistrationPage;
import com.pageObjects.HomePage;
import com.testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verify_account_registraion() {
		
		logger.info("**** Starting the TC001_AccountRegistrationTest ****");
		
		
		// to call homePage class methods by creating object of Home class
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**** Click on MyAccount Link...");
		hp.clickRegister();
		logger.info("**** Click on Register Link...");

		// to call RegistrationPage class method creating object of RegistrationPage
		// class

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("**** Providing customer details...");
		regpage.setFirstName(randomString().toUpperCase()); 
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com"); // Randomly generated the email
		regpage.setTelephone(randomNumber());

		// To call randomAlphaNumberic() methods and store into variable and pass into
		// setPassword and setConfirmpassword() method

		String password = randomAlphaNumberic();

		regpage.setPassword(password);
		regpage.setConfirmpassword(password); 

		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		logger.info("Validating expected message...");
		String confmsg = regpage.getConfirmationMgs();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Case failed..");
			logger.debug("Debug Logs..");
			Assert.assertTrue(false);
			
		}
	
		}
		
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("**** finished the TC001_AccountRegistrationTest **** ");

	}
	

}
