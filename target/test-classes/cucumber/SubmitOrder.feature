@tag
Feature: Purchase the order from the ecommerce website.

  Background:
    Given I Landed On Ecommerce Page

  @Regression
  Scenario Outline: Positive Test Of Submitting the order

    Given Loggin with username <name> and password <password>
    When I add product <ProductName> to Cart
    And CheckOut <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage

  Examples:
    | name                     | password            | ProductName     |
    | Sangamrathor31@gmail.com | Sangamrathore31#     | ADIDAS ORIGINAL |

