package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		
		logger.info("**** Starting TC002_LoginTest ****");
		
		try
		{
		//Home Page
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
	
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount Page
		MyAccountPage macc= new MyAccountPage(driver);
	    boolean targetpage = macc.isMyAccountPageExists();
	    
	    Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished TC002_LoginTest ****");
	}

}
