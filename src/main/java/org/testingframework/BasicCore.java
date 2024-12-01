package org.testingframework;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.testingframework.Reports.Reporting;
import org.testingframework.page.Google;

import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BasicCore extends Reporting  {

    public Reporting reporting;
    public static Browser browser;
    public static BrowserContext browercontext;
    public static Page page;
    public Google google;


   public BasicCore(){
        super();
    }


   @BeforeAll
   public void beforeAl()  {
       reporting = new Reporting();
       reporting.reportingstarup();
   }

    @BeforeEach
    public void beforeTestExecution(TestInfo testInfo)  {

       reporting.setTestName(testInfo.getDisplayName());
       reporting.testCase  = reporting.testCase(reporting.extent);
        if (browser == null) {
            // Initialize browser only once for all tests
            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
        }
        // Create a new browser context and page for each test
        browercontext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(reporting.folderDir)));
        page = browercontext.newPage();

    }

    @AfterEach
    public void afterEachTestExecution(){
        if (page != null) {
            page.close();
        }
        if (browercontext != null) {
            browercontext.close();
        }
    }

    @AfterAll
    public void afterTestExecution() throws Exception {
        // Close the page and context after each test
        reporting.extent.flush();
        zipfolder(reporting.folderDir,reporting.newFolder);
    }



    public static Page getPage() {
        return page;
    }
    public static Browser getBrowser() {
        return browser;
    }




    public void testAborted(ExtensionContext context, Throwable cause) {
        reporting.testCase.log(Status.WARNING,reporting.getTestName()+" Aborted");
        reporting.testCase.warning(reporting.getTestName()+"--Aborted");
    }

}
