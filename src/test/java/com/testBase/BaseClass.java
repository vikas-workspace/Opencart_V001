package com.testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
//import org.apache.logging.log4j.core.Logger;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j


public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	public Properties prop;

	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		// Loading Config properties file
		FileReader file =new FileReader("./src//test//resources//config.properties"); // location or path of file 
		prop= new Properties();
		prop.load(file);
		
		
		// we create logger object : this below line , automatically load the log4j file for every class
		logger=LogManager.getLogger(this.getClass());
		
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver();
		break;
		
		case "edge" : driver = new EdgeDriver();
		break;
		
		//case "firefox" : driver = new FirefoxDriver();
		//break;
		
		default : System.out.println("Invalid Browser Name..");
		return;
		
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL1")); // Reading URL form properties file  
		//driver.get("https://demo.opencart.com/en-gb?route=common/home");
		
	}

	// To Generate test data by using RandomStringUtils class, this class contains
	// many methods like randomAlphabetic()
	// //To generate random Alphabetic words i.e Name, lastName, email etc..
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	// To generate random Numbers i.e mobile number
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	// To generate random password
	public String randomAlphaNumberic() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;  
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

	@AfterClass
	public void tearDown() {
		
			driver.quit();
		}

	}


