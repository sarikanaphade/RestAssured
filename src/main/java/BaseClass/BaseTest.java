package BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utils.ReportManager;
import Utils.RestUtils;

public class BaseTest {
	RestUtils ru = null;
    public static ExtentReports report=null;
    public static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>();
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	@BeforeClass
	public void setup() {
		ru = new RestUtils();
	}
	
	@BeforeSuite
    public static void setUpBS() {
    	report = ReportManager.getInstance();
    }
	
	 @AfterSuite
	    public void flushReport() {
	        if (report != null) {
	            report.flush();
	        }
	    }
	
	
}
