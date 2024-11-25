package Framework.TestComponenets;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class Listeners extends BaseTest implements ITestListener {
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onStart(ITestContext context) {
		
		System.out.println("onStart");
			
	  }
	
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("In Success");
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	
	public void onTestFailure(ITestResult result) {
		
		System.out.println("In Failure");
		//test.log(Status.FAIL, "Test Failed");

		extentTest.get().fail(result.getThrowable());
		
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  }
	
	 public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }
	
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }
	
	 public void onTestFailedWithTimeout(ITestResult result) {
		    //onTestFailure(result);
		  }
	 
	
	public void onFinish(ITestContext context) {
	   System.out.println("In Finish");
			extent.flush();
		
	  }
	
	
	
	
	
}