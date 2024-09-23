package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.testBase.BaseClass;
import com.utilites.DataProviders;




public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadrivern")// getting data provider from different package 
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("**** stating TC003_LoginDDT **** ");
		try
		{
		// Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		// Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);

		lp.setPassword(pwd);
		lp.clickLogin();

		// MyAccount Page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
		/* Data is Valid - login success - testCase pass - logout 
		                 - login failed - testCase fail 
		 
		 * Data is Invalid - login success - testCase failed - logout
		 *                 - login failed - testCase pass 
		 */
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		
		logger.info("**** Finished TC003_LoginDDT **** ");
	}

}
