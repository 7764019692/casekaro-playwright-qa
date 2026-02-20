package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    // Locators
    private String modelMenu = "a[href*='phone-cases-by-model']";

    // Actions
    public void navigateToHomePage() {
        page.navigate("https://casekaro.com/");
    }

    public void goToModelSelectionPage() {
        page.locator(modelMenu).first().click();
    }
}
