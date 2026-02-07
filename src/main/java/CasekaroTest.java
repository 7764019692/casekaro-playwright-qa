import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CasekaroTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();

            // 1. Open website
            page.navigate("https://casekaro.com/");
            page.waitForTimeout(3000);

            // 2. Phone cases by model
            page.navigate("https://casekaro.com/pages/phone-cases-by-model");
            page.waitForTimeout(3000);

            // 3. Search iPhone
            page.locator("#modelSearch").click();
            page.keyboard().type("iPhone", new Keyboard.TypeOptions().setDelay(120));
            page.waitForTimeout(2000);

            // 4. Negative validation
            assertThat(page.locator("text=Samsung").first()).not().isVisible();
            assertThat(page.locator("text=OnePlus").first()).not().isVisible();

            // 5. Select iPhone 16 Pro
            page.locator("text=iPhone 16 Pro").first().click();
            page.waitForTimeout(3000);

            // 6. Open first product
            page.locator("a[href*='/products/']").first().click();
            page.waitForTimeout(3000);

            // 7. Add Hard, Soft, Glass only
            String[] materials = {"Hard", "Soft", "Glass"};

            for (String material : materials) {

                page.locator("label")
                        .filter(new Locator.FilterOptions().setHasText(material))
                        .filter(new Locator.FilterOptions().setHasNotText("Black"))
                        .first()
                        .click();

                page.waitForTimeout(1000);

                page.locator("button[id^='ProductSubmitButton']").click();
                page.waitForTimeout(3000);

                if (page.locator("button.drawer__close").isVisible()) {
                    page.locator("button.drawer__close").click();
                    page.waitForTimeout(1500);
                }
            }

            // 8. Open cart page
            page.navigate("https://casekaro.com/cart");
            page.waitForTimeout(4000);
// 9. Open cart page
//            page.navigate("https://casekaro.com/cart");
//            page.waitForTimeout(4000);

// 10. Get ONLY visible cart items
            Locator cartItems = page.locator("form[action='/cart'] .cart-item:visible");
            assertThat(cartItems).hasCount(3);

// 11. Print Material, Price, Link
            System.out.println("\n====== CART ITEM DETAILS ======\n");

            for (int i = 0; i < cartItems.count(); i++) {

                Locator item = cartItems.nth(i);

                String fullText = item.innerText();

                // Extract Material
                String material = "N/A";
                for (String line : fullText.split("\n")) {
                    if (line.contains("Material")) {
                        material = line.replace("Material:", "").trim();
                        break;
                    }
                }

                // Extract Price
                String price = "N/A";
                for (String line : fullText.split("\n")) {
                    if (line.contains("₹")) {
                        price = line.trim();
                        break;
                    }
                }

                // Extract Link
                String link = item.locator("a").first().getAttribute("href");

                System.out.println("Item " + (i + 1));
                System.out.println("Material : " + material);
                System.out.println("Price : " + price);
                System.out.println("Link : https://casekaro.com" + link);
                System.out.println("----------------------------------");
            }

            page.waitForTimeout(3000);
            browser.close();
        }
    }
}
