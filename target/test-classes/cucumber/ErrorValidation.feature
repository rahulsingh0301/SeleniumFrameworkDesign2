@tag
Feature: ErrorValidion

  @ErrorValidation2
  Scenario Outline: Positive Test Of Submitting the order
    Given I Landed On Ecommerce Page
    Given Loggin with username <name> and password <password>
    Then "Incorrect email or password." message is diaplayed

  

  Examples:
    | name                     | password            |
    | Sangamrathor31@gmail.com | Sangamrathore31     |
