Feature: Search on Next website
  As a user
  I want to search for products
  So that I can view matching results

  Scenario: Search for a product
    Given User is on Next home page
    When User searches for "dress"
    Then Search results for "dress" should be displayed
