package org.testingframework.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Google {

    public Page page;
    public Locator locator;

    public Google(Page page){
        this.page=page;
    }

}
