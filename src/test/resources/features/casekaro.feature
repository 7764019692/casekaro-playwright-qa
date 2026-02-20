Feature: Casekaro Add to Cart Flow

  Scenario: Select iPhone 16 Pro and add materials to cart

    Given User opens Casekaro website
    When User navigates to Phone cases by model page
    And User searches for "iPhone"
    Then Other brands should not be visible
    When User selects "iPhone 16 Pro"
    And User opens the first product
    And User adds following materials to cart:
      | Hard  |
      | Soft  |
      | Glass |
    Then Cart should contain 3 items
    And Print cart item details
