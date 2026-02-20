package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBase {

    @Before
    public void setUp() {
        launchBrowser();
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
