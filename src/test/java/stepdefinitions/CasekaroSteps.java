package stepdefinitions;

import base.TestBase;
import com.microsoft.playwright.Locator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CasekaroSteps extends TestBase {

    @Given("User opens Casekaro website")
    public void openWebsite() {
        page.navigate("https://casekaro.com/");
        page.waitForTimeout(2000);
    }

    @When("User navigates to Phone cases by model page")
    public void navigateToModelPage() {
        page.navigate("https://casekaro.com/pages/phone-cases-by-model");
        page.waitForTimeout(2000);
    }

    @When("User searches for {string}")
    public void searchBrand(String brand) {
        Locator searchBox = page.locator("#modelSearch");
        searchBox.click();
        searchBox.fill("");
        searchBox.pressSequentially(brand, new Locator.PressSequentiallyOptions().setDelay(120));
        page.waitForTimeout(1500);
    }

    @Then("Other brands should not be visible")
    public void validateBrandsHidden() {
        assertThat(page.locator("text=Samsung").first()).not().isVisible();
        assertThat(page.locator("text=OnePlus").first()).not().isVisible();
    }

    @When("User selects {string}")
    public void selectModel(String model) {
        page.locator("text=" + model).first().click();
        page.waitForTimeout(2000);
    }

    @When("User opens the first product")
    public void openFirstProduct() {
        page.locator("a[href*='/products/']").first().click();
        page.waitForTimeout(2000);
    }

    @When("User adds following materials to cart:")
    public void addMaterials(DataTable table) {

        List<String> materials = table.asList();

        for (String material : materials) {

            System.out.println("Selecting material : " + material);

            Locator materialOption = page.locator("label")
                    .filter(new Locator.FilterOptions().setHasText(material))
                    .filter(new Locator.FilterOptions().setHasNotText("Black"))
                    .first();

            materialOption.scrollIntoViewIfNeeded();
            materialOption.click();

            page.waitForTimeout(800);

            Locator addToCartBtn = page.locator("button[id^='ProductSubmitButton']");
            addToCartBtn.scrollIntoViewIfNeeded();
            addToCartBtn.click();

            // Wait for drawer
            Locator drawerClose = page.locator("button.drawer__close").first();
            drawerClose.waitFor(new Locator.WaitForOptions().setTimeout(5000));

            if (drawerClose.isVisible()) {
                drawerClose.scrollIntoViewIfNeeded();
                drawerClose.click(new Locator.ClickOptions().setForce(true));
            }

            page.waitForTimeout(1000);
        }
    }

    @Then("Cart should contain {int} items")
    public void validateCartItems(int expectedCount) {

        page.navigate("https://casekaro.com/cart");
        page.waitForTimeout(2000);

        Locator cartItems = page.locator("form[action='/cart'] .cart-item:visible");

        int actualCount = cartItems.count();
        System.out.println("Visible Cart Items : " + actualCount);

        assertThat(cartItems).hasCount(expectedCount);
    }

    @Then("Print cart item details")
    public void printCartDetails() {

        Locator cartItems = page.locator("form[action='/cart'] .cart-item:visible");

        System.out.println("\n====== CART ITEM DETAILS ======\n");

        for (int i = 0; i < cartItems.count(); i++) {

            Locator item = cartItems.nth(i);
            String fullText = item.innerText();

            // Material
            String material = "N/A";
            for (String line : fullText.split("\n")) {
                if (line.toLowerCase().contains("material")) {
                    material = line.replace("Material:", "").trim();
                    break;
                }
            }

            // Price
            String price = "N/A";
            for (String line : fullText.split("\n")) {
                if (line.contains("₹")) {
                    price = line.replace("₹", "Rs. ").trim();
                    break;
                }
            }

            // Link
            String link = item.locator("a").first().getAttribute("href");

            System.out.println("Item " + (i + 1));
            System.out.println("Material : " + material);
            System.out.println("Price    : " + price);
            System.out.println("Link     : https://casekaro.com" + link);
            System.out.println("----------------------------------");
        }
    }
}
