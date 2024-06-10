package demo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
    private static ExtentReports extent;
    //private static ExtentHtmlReporter htmlReporter;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            // Create an ExtentHtmlReporter object with the specified file path
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
            // Attach the ExtentHtmlReporter to the ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}
