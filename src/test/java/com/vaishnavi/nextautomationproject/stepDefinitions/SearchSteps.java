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

    @Then("Search results for {string} should be displayed")
    public void search_results_should_be_displayed(String product) {
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains(product.toLowerCase()),
                "Title does not contain search term: " + product);
        DriverFactory.quitDriver();
    }

}
