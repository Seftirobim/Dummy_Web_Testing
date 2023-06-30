Feature: Cart
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

   Scenario: Valid User want to remove one product from inventory page
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And Button remove and cart icon badge should displayed
     When User clicks on the remove button
     Then Add to cart button should be displayed and cart icon badge should be removed

   Scenario: User want to check cart
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And Button remove and cart icon badge should displayed
     When User click cart icon
     Then Selected Product must be displayed

  Scenario: Valid User want to remove one product from cart page
    Given User open url
    When User input valid username "standard_user" and valid password "secret_sauce"
    And Click Login button
    And User should success login
    And The product image must be displayed and be appropriate
    When User click add to cart button
    And Button remove and cart icon badge should displayed
    When User click cart icon
    And Selected Product must be displayed
    When User click on remove button
    Then System should remove the item
