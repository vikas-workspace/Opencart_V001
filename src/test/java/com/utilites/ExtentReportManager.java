package com.utilites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	
	public ExtentSparkReporter  sparkReporter;// UI of the reports
	public ExtentReports extent; // populated common info on the report
	public ExtentTest test; // creating test case entries in the reports and update status of the test methods
	
	String repName;
	//private ITestContext testContext;
	
	public void onStart(ITestContext context) {
		
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
		Date dt= new Date();
		String timeStamp=df.format(dt);
	*/	
		String timeStamp =new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());// time stamp
		repName= "Test-Report-" + timeStamp + ".html";
		
		
		sparkReporter =new ExtentSparkReporter(".\\reports\\"+ repName );// specify location of the report
		
		sparkReporter.config().setDocumentTitle("Automation Report"); // title of report
		sparkReporter.config().setReportName("Functional Testing");// Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "openCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environmrnt", "QA");
		
		
		// to fetch the OS details from xml file 
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		// to fetch the browser details from xml file 
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Operating System", browser);
		
		
		// to fetch the group detail like name from xml file 
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
	  }
	
	public void onTestStart(ITestResult result) {
		
	
	  }
	
	public void onTestSuccess(ITestResult result) {
	   
		test=extent.createTest(result.getTestClass().getName()); // create entry in the report
		test.assignCategory(result.getMethod().getGroups()); //to display group name 
		test.log(Status.PASS, result.getName()+ " got successfully exectuted");// update status pass/fail/skip
		
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); // create entry in the report
		test.assignCategory(result.getMethod().getGroups()); //to display group name
		
		
		test.log(Status.FAIL,result.getName()+ "got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());//getThrowable() method give you error mesagees in console window
		
		try
		{
			String imgpath= new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	  }

		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName() + " got skipped" );
			test.log(Status.INFO, result.getThrowable().getMessage());

	 }
	 
	public void onFinish(ITestContext context) {
		
		extent.flush();// whatever we done so far above flush() method will updated all in report
		
		// this code open report automatically after execution is done 
		String pathOfExtentReport =System.getProperty("user.dir")+"\\reports\\"+ repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		// this code for send email to cilent 
	/*	
		 try
		 {
		    URL url =new URL("file://"System.getProperty("user.dir")+"\\reports\\"+repName) ;
		    
		    //Create the email message
		     
		    ImageHtmlEmail email = new ImageHtmlEmail();
		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    email.setHostName("smtp.googlemail.com");
		    email.setSmtpPort(465);
		    email.setAuthenticator(new DefaultAuthenticator("khandelote.vikas95@gmail.com", "password"));
		    email.setSSLOnConnect(true);
		    email.setFrom("khandelote.vikas95@gmail.com");// sender
		    email.setSubject("Test Result");
		    email.setMsg("Plase fins Attached Report....");
		    email.addTo("shree.busyqa@gmail.com");//Receiver
		    email.attach(url, "extent report", "plase check report...");
		    email.send();// send the email
		    
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 */
    }
	

}
