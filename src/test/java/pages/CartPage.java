package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    // Locators
    private String cartItems = "form[action='/cart'] .cart-item";

    // Actions
    public void navigateToCart() {
        page.navigate("https://casekaro.com/cart");
    }

    public Locator getCartItems() {
        return page.locator(cartItems);
    }

    public void printCartDetails() {

        Locator items = getCartItems();

        System.out.println("\n====== CART ITEM DETAILS ======\n");

        for (int i = 0; i < items.count(); i++) {

            Locator item = items.nth(i);

            String text = item.innerText();

            String material = extractValue(text, "Material:");
            String price = extractPrice(text);

            String link = item.locator("a").first().getAttribute("href");

            System.out.println("Item " + (i + 1));
            System.out.println("Material : " + material);
            System.out.println("Price    : " + price);
            System.out.println("Link     : https://casekaro.com" + link);
            System.out.println("----------------------------------");
        }
    }

    // Helpers
    private String extractValue(String text, String key) {
        for (String line : text.split("\n")) {
            if (line.trim().startsWith(key)) {
                return line.replace(key, "").trim();
            }
        }
        return "N/A";
    }

    private String extractPrice(String text) {
        for (String line : text.split("\n")) {
            if (line.contains("₹")) {
                return line.trim();
            }
        }
        return "N/A";
    }
}
