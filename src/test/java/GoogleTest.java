import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testingframework.BasicCore;
import org.testingframework.page.Google;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



public class GoogleTest extends BasicCore{

    @BeforeEach
    public void Teest(){
        System.out.println("Wowo");
    }


    @Test
    public void Test(){
        google = new Google(getPage());
        google.page.navigate("http://google.com");
        reporting.stepWithScreenshot(getPage().title().equalsIgnoreCase("Playwright"),getPage(),"Has Title Playwrite");
        // Expect a title "to contain" a substring.
        assertThat(getPage()).hasTitle(Pattern.compile("Playwright"));
    }

    @Test
    public void Test2(){
        getPage().navigate("http://google.com");
        reporting.stepWithScreenshot(getPage().title().equalsIgnoreCase("Playwright"),getPage(),"Has Title Playwrite");
        // Expect a title "to contain" a substring.
        assertThat(getPage()).hasTitle(Pattern.compile("Playwright"));
    }
}