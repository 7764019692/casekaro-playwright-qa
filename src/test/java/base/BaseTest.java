package base;

import com.microsoft.playwright.*;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();
    }

    public static void tearDown() {
        browser.close();
        playwright.close();
    }
}
