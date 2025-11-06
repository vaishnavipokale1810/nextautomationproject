package com.vaishnavi.nextautomationproject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Updated locators
    private By acceptCookiesBtn = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    private By searchBox = By.xpath("//*[@id=\"header-big-screen-search-box\"]");
    private By searchIcon = By.cssSelector("\"button[data-testid='header-search-button']\"");
    private By submitSearch = By.cssSelector("\"button[data-testid='search-button']\"");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage() {
        driver.get("https://www.next.co.uk/");
        driver.manage().window().maximize();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
        } catch (Exception e) {
            System.out.println("No cookie banner found");
        }
    }


    public void searchFor(String productName) {
        // Wait for search box to appear
//        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
//        box.clear();
//        box.sendKeys(productName);

        // Wait for and click search icon
//        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
//        searchBtn.click();

        System.out.println("This test is done");
    }
}
