package base;

import com.microsoft.playwright.*;

public class TestBase {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    public static void launchBrowser() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        BrowserContext context = browser.newContext();
        page = context.newPage();

        System.out.println("Browser Launched");
    }

    public static void closeBrowser() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }

        System.out.println("Browser Closed");
    }
}
