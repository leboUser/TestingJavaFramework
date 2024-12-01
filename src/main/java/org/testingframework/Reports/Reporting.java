package org.testingframework.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;
import org.testingframework.tools.Utils;

import java.io.File;
import java.nio.file.Paths;

public class Reporting extends Utils {

    public ExtentReports extent;
    ExtentSparkReporter sparsk;
    public ExtentTest testCase;
    public String newFolder;
    String testName;
    public String folderDir;
    public String fileNameZip;
    String screenshotFolder;


    public Reporting(){}

    public void reportingstarup(){

        if(sparsk == null) {
            extent = new ExtentReports();
            newFolder =   testDate()+"-report";
            folderDir = makefolder(System.getProperty("user.dir") +File.separator+newFolder+ File.separator) ;
            screenshotFolder =makefolder(folderDir + File.separator+"screenshot");
            sparsk = new ExtentSparkReporter(folderDir +File.separator+ testDate() + "_Results.html");
            extent.attachReporter(sparsk);
        }
    }

    public void setTestName(String testName){
        this.testName = testName;
    }

    public String getTestName(){
        return testName;
    }

    //Test Case
    public ExtentTest testCase(ExtentReports extent){
        return extent.createTest(getTestName());
    }

    //Steps
    public void stepWithScreenshot(boolean stepfOrP,Page page, String message){
        String filePic = screenshotFolder+ File.separator+testDate()+"__"+"screenshot.png";
        page.screenshot(new Page.ScreenshotOptions()
                .setPath( Paths.get(filePic)));
        if(stepfOrP){
            testCase.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(filePic).build());
        }else{
            testCase.fail(message,MediaEntityBuilder.createScreenCaptureFromPath(filePic).build());
        }
    }

    public void stepNoScreenshot(boolean stepfOrP,String message){
        if(stepfOrP){
            testCase.pass(message);
        }else{
            testCase.fail(message);
        }
    }
}
