package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	

	@FindBy(xpath = "//span[text()=\"My Account\"]")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement lnkRegister;
	
	
	@FindBy(xpath = "//a[contains(text(),\"Login\")]")
	WebElement lnklogin;
	
	
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnklogin.click();	
	}

}
