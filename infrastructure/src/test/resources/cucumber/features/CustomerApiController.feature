Feature: CustomerApiController

  Scenario: All Customers
    Given I have 2 customers in the system
    When I get all customers
    Then it should return 2 customers