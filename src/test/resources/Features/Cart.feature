Feature: Cart
  # Positive
   Scenario Outline: User want to add one product to cart
      Given User open url
      When User input valid username "<username>" and valid password "<password>"
      And Click Login button
      And User should success login
      And The product image must be displayed and be appropriate
      When User click add to cart button
      Then Button remove and cart icon badge should displayed
   Examples:
     |username|password|
     |standard_user|secret_sauce|
     |performance_glitch_user|secret_sauce|
     |problem_user|secret_sauce|
   # Positive
   Scenario: User want to add multiple product to cart
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User add 4 product to cart
     Then System should display a remove button according to the selected product and display a cart badge with the corresponding number
   # Positive
   Scenario: User want to remove one product from inventory page
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And Button remove and cart icon badge should displayed
     When User clicks on the remove button
     Then Add to cart button should be displayed and cart icon badge should be removed
   # Positive
   Scenario: User want to check cart
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And Button remove and cart icon badge should displayed
     When User click cart icon
     Then System should be navigate user to cart page and selected Product must be displayed
   # Positive
   Scenario: User want to go back to the inventory page after clicking the cart icon.
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click cart icon
     Then System should be navigate user to cart page
     When user click on Continue Shopping button
     Then System should be navigate user to inventory page
   # Positive
   Scenario: User want to remove one product from cart page
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And Button remove and cart icon badge should displayed
     When User click cart icon
     Then System should be navigate user to cart page and selected Product must be displayed
     When User click on remove button
     Then System should remove the item
   # Positive
   Scenario: User want to remove product contains Bolt T-Shirt from inventory page
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     And User add 4 product to cart
     When User remove "Bolt T-Shirt" Product
     Then System should display a remove button according to the selected product and display a cart badge with the corresponding number
   # Positive
   Scenario: User want to remove product contains Bolt T-Shirt from cart page
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     And User add 4 product to cart
     And User click cart icon
     Then System should be navigate user to cart page and selected Product must be displayed
     When User remove "Bolt T-Shirt" Product
     Then System should remove "Bolt T-Shirt" product and display a cart badge with the corresponding number
   # Positive
   Scenario: User want to check out order
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     And User add 6 product to cart
     And User click cart icon
     Then System should be navigate user to cart page and selected Product must be displayed
     When User click checkout button
     Then System should be navigate user to checkout page
     When User input firstname "example", lastname "example" and postal code 40222
     And Click continue button
     Then System should navigate user to checkout step two page and display corresponding order data
     When User click finish button
     Then System should navigate user to checkout complete page and display a success message

