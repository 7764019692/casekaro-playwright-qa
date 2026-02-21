# 🧪 Casekaro Playwright QA Automation Framework

## 📌 Project Overview
This project is an **end-to-end UI Automation Framework** built using:

- **Playwright with Java**
- **Cucumber (BDD)**
- **Page Object Model (POM)**
- **Maven**
- **Features**

The framework automates a real user journey on the **Casekaro e-commerce website**, validating search behavior, product selection, cart operations, and extracting cart details.

---

## 🚀 Tech Stack

| Tool / Technology | Purpose |
|-------------------|---------|
| Playwright (Java) | Browser automation |
| Java 17           | Programming language |
| Maven             | Build & dependency management |
| Cucumber          | BDD test execution |
| JUnit / Surefire  | Test runner |
| POM Design        | Maintainable structure |

---

## 🏗️ Framework Architecture


src
└── test
├── java
│ ├── base
│ │ ├── BaseTest.java
│ │ ├── Hooks.java
│ │ └── TestBase.java
│ │
│ ├── pages
│ │ ├── HomePage.java
│ │ ├── ModelSelectionPage.java
│ │ ├── ProductPage.java
│ │ └── CartPage.java
│ │
│ ├── stepdefinitions
│ │ └── CasekaroSteps.java
│ │
│ ├── runners
│ │ └── TestRunner.java
│ │
│ └── utils
│ ├── WaitUtil.java
│ └── ScreenshotUtil.java
│
└── resources
└── features
└── casekaro.feature


---

## ✅ Key Automation Scenarios

✔ Open Casekaro website  
✔ Navigate to Phone Cases by Model page  
✔ Search for **iPhone**  
✔ Perform **negative validation** (Samsung / OnePlus hidden)  
✔ Select **iPhone 16 Pro**  
✔ Open first product  
✔ Add multiple materials to cart:
- Hard  
- Soft  
- Glass  

✔ Validate cart item count  
✔ Extract & print cart details:
- Material  
- Price  
- Product Link  

---

## 🧠 Design Patterns Used

### 🔹 Page Object Model (POM)
- Separates locators & actions from test logic
- Improves maintainability
- Encourages reusability

### 🔹 BDD with Cucumber
- Human-readable feature files
- Clear mapping between steps & code

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites
Install:

- Java 17+
- Maven
- Node.js (required by Playwright)
- IntelliJ / VS Code

---

### 2️⃣ Install Playwright Browsers
Run:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
--------------------------**HOW TO RUN**-----------------
1- disable all antivirus
2- download file 
3- extract file
4- load file
5- Go to terminal
6- Run Tests - mvn clean test


🛒 Sample Console Output
====== CART ITEM DETAILS ======

Item 1
Material : Glass
Price : Rs. 249.00
Link : https://casekaro.com/products/...

----------------------------------

Item 2
Material : Soft
Price : Rs. 149.00
Link : https://casekaro.com/products/...

----------------------------------

Item 3
Material : Hard
Price : Rs. 69.00
Link : https://casekaro.com/products/...
📸 Screenshot Support

Framework includes:

✔ Automatic screenshot capture
✔ Timestamp-based file naming

Stored under:

/screenshots
🧩 Utilities Included
Utility	Purpose
WaitUtil	Smart waits / stability
ScreenshotUtil	Failure debugging
🎯 Best Practices Implemented

✔ Clean locator strategy
✔ Reusable page methods
✔ Proper synchronization
✔ Strict mode handling
✔ Dynamic UI handling (Cart Drawer)
✔ Professional console reporting

👨‍💻 Author
Satyaki Kumar
QA / Automation Engineer

📄 License

This project is created for learning, demonstration, and assignment purposes.
