#Testing on https://www.saucedemo.com
Feature: Login to saucedemo
#  Scenario: User want to login with valid credentials
#    Given User open url
#    When User input valid username "standard_user" and valid password "secret_sauce"
#    And Click Login button
#    Then User should success login

# Digunakan apabila ingin mengulang beberapa iterasi
  # Positive
  Scenario Outline: User want to login with valid credentials
    Given User open url
    When User input valid username "<username>" and valid password "<password>"
    And Click Login button
    Then User should success login
  Examples:
#    Variable
    |username|password|
#    Value
    |standard_user|secret_sauce|
    |problem_user|secret_sauce|
    |performance_glitch_user|secret_sauce|
# Positive
  Scenario: User want to login with locked out user
    Given User open url
    When User input valid username "locked_out_user" and valid password "secret_sauce"
    And Click Login button
    Then System should display locked out message
# Positive
  Scenario: Users want to click the close button next to the error message
    Given User open url
    When User input invalid username "invalid" and invalid password "invalid"
    And Click Login button
    Then System should display error message
    When User clicks on the close button next to the error message
    #Misalkan dalam PRD diharuskan menghapus field value apabila user sudah click button error message
    Then System should be clearing the input field and removing the error message
# Negative
  Scenario: User want to login with invalid credentials
    Given User open url
    When User input invalid username "invalid" and invalid password "invalid"
    And Click Login button
    Then System should display error message

# Misalkan diharuskan, ketika user mengkosongkan field username dan password
# Maka sistem menampilkan pesan "Epic sadface: Username and password is required"
# Negative
  Scenario: User want to login with empty username and password
    Given User open url
    When Click Login button
    Then System should display error message "Epic sadface: Username and password is required"
# Negative
  Scenario: User want to login with empty username
    Given User open url
    When User input empty username and valid password "secret_sauce"
    And Click Login button
    Then System should display error message "Epic sadface: Username is required"
# Negative
  Scenario: User want to login with empty password
    Given User open url
    When User input valid username "standard_user" and empty password
    And Click Login button
    Then System should display error message "Epic sadface: Password is required"