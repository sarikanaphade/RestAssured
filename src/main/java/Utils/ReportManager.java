package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Constants.FileConstants;

public class ReportManager {
	
	private static ExtentReports report;
	
	public static ExtentReports getInstance() {
		if(report==null) {
			createInstance();
		}
		return report;
	}

	private static ExtentReports createInstance() {
		ExtentSparkReporter sr = new ExtentSparkReporter(FileConstants.REPORT_PATH);
		sr.config().setDocumentTitle("API Report - "+getTimeStamp());
		sr.config().setTheme(Theme.STANDARD);
		report = new ExtentReports();
		report.attachReporter(sr);
		return report; 
		
	}
	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

}
