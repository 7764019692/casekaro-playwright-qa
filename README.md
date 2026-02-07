📱 Casekaro QA Automation Project
Java + Playwright End-to-End Automation
📌 Project Overview

This project automates the end-to-end user journey on https://casekaro.com
 using Java and Playwright.
The automation validates search functionality, negative validations, product selection, cart behavior, and console reporting as per the assignment requirements.

The goal was to simulate real user behavior and ensure correctness through assertions without using try-catch.

🛠 Tech Stack Used
Technology	Purpose
Java (JDK 17)	Automation language
Playwright (Java)	Browser automation
Maven	Dependency management
IntelliJ IDEA	IDE
Chromium (non-headless)	Test execution
Git & GitHub	Version control
📂 Project Structure
casekaro-playwright-qa
│
├── pom.xml
├── README.md
├── .gitignore
└── src
    └── main
        └── java
            └── CasekaroTest.java

🎯 Assignment Requirements & Implementation

Below is a step-by-step mapping of the assignment questions with the exact logic used.

✅ 1. Navigate to website

Requirement:
Open Casekaro website.

Logic Used:
page.navigate() to load the URL.

page.navigate("https://casekaro.com/");

✅ 2. Click on Mobile covers (Phone cases by model)

Requirement:
Go to phone model selection page.

Logic Used:
Direct navigation to avoid flaky UI menus.

page.navigate("https://casekaro.com/pages/phone-cases-by-model");

✅ 3. Click on search button and type "Apple / iPhone"

Requirement:
Search phone brand using search box.

Logic Used:

Located input field using id

Used keyboard typing with delay (real user simulation)

Locator searchBox = page.locator("#modelSearch");
searchBox.click();
page.keyboard().type("iPhone", new Keyboard.TypeOptions().setDelay(120));

✅ 4. Negative validation for other brands

Requirement:
Ensure other brands (Samsung, OnePlus) are not visible after search.

Logic Used:
Playwright assertion to check invisibility.

assertThat(page.locator("text=Samsung").first()).not().isVisible();
assertThat(page.locator("text=OnePlus").first()).not().isVisible();


✔️ This confirms search filter works correctly.

✅ 5. Click Apple → iPhone 16 Pro

Requirement:
From filtered list, select iPhone 16 Pro.

Logic Used:
Text-based locator with first() to avoid duplicates.

page.locator("text=iPhone 16 Pro").first().click();

✅ 6. Click "Choose options" (open first product)

Requirement:
Open first product from product listing.

Logic Used:
Anchor (<a>) selector matching product URLs.

page.locator("a[href*='/products/']").first().click();

✅ 7. Each item has 3 materials

Hard

Soft

Glass

✅ 8. Add all 3 materials of same case to cart

Requirement:
Select only Hard, Soft, Glass (ignore Black Soft).

Logic Used:

Filter labels by text

Exclude unwanted variants

Loop through materials

Close cart drawer after each add (critical fix)

String[] materials = {"Hard", "Soft", "Glass"};

for (String material : materials) {

    page.locator("label")
        .filter(new Locator.FilterOptions().setHasText(material))
        .filter(new Locator.FilterOptions().setHasNotText("Black"))
        .first()
        .click();

    page.locator("button[id^='ProductSubmitButton']").click();

    if (page.locator("button.drawer__close").isVisible()) {
        page.locator("button.drawer__close").click();
    }
}


✔️ Prevents:

Cart overlay blocking clicks

Duplicate items

Wrong material selection

✅ 9. Open Cart

Requirement:
Open cart page.

page.navigate("https://casekaro.com/cart");

✅ 10. Validate all 3 items are added

Requirement:
Exactly 3 items should be present in cart.

Logic Used:
Scoped locator to avoid hidden drawer duplicates.

Locator cartItems = page.locator("form[action='/cart'] .cart-item");
assertThat(cartItems).hasCount(3);

✅ 11. Print details in console

Requirement:
Print for each item:

Material

Price

Link

Logic Used:

Loop through cart items

Extract visible text only

Build full product URL

for (int i = 0; i < cartItems.count(); i++) {

    Locator item = cartItems.nth(i);

    String material = item.innerText().contains("Material")
            ? item.innerText().split("Material:")[1].split("\n")[0].trim()
            : "N/A";

    String price = item.locator("span.money").first().innerText();

    String link = item.locator("a").first().getAttribute("href");

    System.out.println("Item " + (i + 1));
    System.out.println("Material : " + material);
    System.out.println("Price    : " + price);
    System.out.println("Link     : https://casekaro.com" + link);
    System.out.println("----------------------------------");
}

🖥 Sample Console Output
Item 1
Material : Glass
Price : ₹ 249.00
Link : https://casekaro.com/products/...

Item 2
Material : Soft
Price : ₹ 149.00
Link : https://casekaro.com/products/...

Item 3
Material : Hard
Price : ₹ 69.00
Link : https://casekaro.com/products/...

🧪 Key Automation Best Practices Followed

✅ No try-catch used

✅ Assertions added for validations

✅ Stable locators (no brittle XPath)

✅ Handled overlays & UI flakiness

✅ Real user typing simulation

✅ Clean console reporting

✅ Maven-based Playwright setup

🚀 How to Run the Project

mvn compile

mvn exec:java


(or directly run CasekaroTest.java from IntelliJ)
