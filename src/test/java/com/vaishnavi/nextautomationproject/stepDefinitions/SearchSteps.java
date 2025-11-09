package com.vaishnavi.nextautomationproject.stepDefinitions;

import com.vaishnavi.nextautomationproject.pageObjects.HomePage;
import com.vaishnavi.nextautomationproject.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Given("User is on Next home page")
    public void user_is_on_next_home_page() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        homePage.searchFor(product);
    }

    @Then("Search results for {string} and {string} should be displayed")
    public void search_results_should_be_displayed(String product1, String product2) {
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains(product1.toLowerCase()),
                "Title does not contain search term: " + product1);
        Assert.assertTrue(title.toLowerCase().contains(product2.toLowerCase()),
                "Title does not contain search term: " + product2);
    }

    @Then("I select product size {string}")
    public void selectProductWithSize(String size) throws InterruptedException {
        homePage.pdpSizeSelect(size);
    }

    @Then("I add the product to bag")
    public void addProductToBag() {
        homePage.addToBag();
    }

    @Then("I verify {string} product is added to bag")
    public void verifyProductInBag(String productName) {
        homePage.verifyItemInBag(productName);
        //DriverFactory.quitDriver();
    }

}
