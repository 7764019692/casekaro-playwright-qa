package pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ModelSelectionPage {

    private final Page page;

    public ModelSelectionPage(Page page) {
        this.page = page;
    }

    // Locators
    private String modelSearchBox = "#modelSearch";
    private String iphone16ProOption = "text=iPhone 16 Pro";

    // Actions
    public void searchPhoneModel(String brand) {
        page.locator(modelSearchBox).click();
        page.keyboard().type(brand);
    }

    public void validateOtherBrandsHidden() {
        assertThat(page.locator("text=Samsung").first()).not().isVisible();
        assertThat(page.locator("text=OnePlus").first()).not().isVisible();
    }

    public void selectIphone16Pro() {
        page.locator(iphone16ProOption).first().click();
    }
}
