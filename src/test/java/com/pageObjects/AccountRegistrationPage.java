package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	// Page Objects
	
	@FindBy(xpath="//input[@id=\"input-firstname\"]")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id=\"input-lastname\"]")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id=\"input-email\"]") 
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id=\"input-confirm\"]")
	WebElement txtConfirmPassword;
	
	
	@FindBy(xpath="//input[@type=\"checkbox\"]")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement btnContinue;
	
	
	@FindBy(xpath="//div[@id=\"content\"]/child::h1")
	WebElement mgsConirmation;
	
	//Action Methods
	
	 public void setFirstName(String fname)
	 {
		 txtFirstname.sendKeys(fname);
	 }
	 
	 
	 public void setLastName(String lname)
	 {
		 txtLastname.sendKeys(lname);
	 } 
	 
	 
	 public void setEmail(String email)
	 {
		 txtEmail.sendKeys(email);
	 }
	 
	 
	 public void setTelephone(String tel)
	 {
		 txtTelephone.sendKeys(tel);
	 }
	 
	 
	 public void setPassword(String pwd)
	 {
		 txtPassword.sendKeys(pwd);
	 }
	 
	 
	 public void setConfirmpassword(String pwd)
	 {
		 txtConfirmPassword.sendKeys(pwd);
	 }
	 
	 
	 public void setPrivacyPolicy()
	 {
		 chkdPolicy.click();
	 }
	 
	 
	 public void clickContinue()
	 {
		 btnContinue.click();
	 }
	 
		public String getConfirmationMgs() {
			try {
				return (mgsConirmation.getText());
			} catch (Exception e) {
				return (e.getMessage());
			}
		}

}
