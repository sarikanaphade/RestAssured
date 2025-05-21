package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import BaseClass.BaseTest;

public class TestListeners implements ITestListener{
	
	
	public void onTestSuccess(ITestResult result) {
		BaseTest.threadLocalTest.get().pass("Test : "+ result.getName()+" passed!");
	}
	
	public void onTestStart(ITestResult result) {
        ExtentTest test = BaseTest.report.createTest(result.getName());
        BaseTest.threadLocalTest.set(test);
    }
	
	public void onTestFailure(ITestResult result) {
		BaseTest.threadLocalTest.get().fail("Test : "+ result.getName()+" failed!");
	}
	
	public void onTestSkipped(ITestResult result) {
		BaseTest.threadLocalTest.get().skip("Test skipped: " + result.getName());
    }

}
