Feature: Cart
   Scenario Outline: User want to add to cart
      Given User open url
      When User input valid username "<username>" and valid password "<password>"
      And Click Login button
      And User should success login
      And The product image must be displayed and be appropriate
      When User click add to cart button
      Then button remove and cart icon badge should displayed
   Examples:
     |username|password|
     |standard_user|secret_sauce|
     |performance_glitch_user|secret_sauce|
     |problem_user|secret_sauce|

   Scenario: User want to check cart
     Given User open url
     When User input valid username "standard_user" and valid password "secret_sauce"
     And Click Login button
     And User should success login
     And The product image must be displayed and be appropriate
     When User click add to cart button
     And button remove and cart icon badge should displayed
     When User click cart icon
     Then The Product must be displayed


