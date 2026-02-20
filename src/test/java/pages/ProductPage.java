package pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    private final Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    // Locators
    private String firstProduct = "a[href*='/products/']";
    private String addToCartButton = "button[id^='ProductSubmitButton']";
    private String cartDrawerClose = "button.drawer__close";

    // Actions
    public void openFirstProduct() {
        page.locator(firstProduct).first().click();
    }

    public void selectMaterial(String material) {
        page.locator("label")
                .filter(new com.microsoft.playwright.Locator.FilterOptions()
                        .setHasText(material))
                .filter(new com.microsoft.playwright.Locator.FilterOptions()
                        .setHasNotText("Black"))
                .first()
                .click();
    }

    public void addToCart() {
        page.locator(addToCartButton).click();
    }

    public void closeCartDrawerIfVisible() {
        if (page.locator(cartDrawerClose).isVisible()) {
            page.locator(cartDrawerClose).click();
        }
    }
}
