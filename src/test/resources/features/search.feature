Feature: Search on Next website
  As a user
  I want to search for products
  So that I can view matching results

  Scenario: Search for a product
    Given User is on Next home page
    When User searches for "W40-428"
    Then Search results for "Love" and "Chiffon Sleeve" should be displayed
    Then I select product size "10"
    Then I add the product to bag
    And I verify "Knitted Mini Dress" product is added to bag
    Then I click on checkout button
    Then I click on Register Now Button
