package com.vaishnavi.nextautomationproject.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v113.domsnapshot.model.StringIndex;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // Updated locators
     By acceptCookiesBtn = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
     By searchBox = By.xpath("//*[@id=\"header-big-screen-search-box\"]");
     By submitSearch = By.xpath("/html/body/div[2]/div/section/header/div[1]/nav/div[1]/div/div/div/div[1]/div/form/button");
     By pdpSize = By.cssSelector("div[data-testid='size-chips-button-group'] button");

    // Add to Bag locators
    By addToBagButton = By.cssSelector("button[data-testid='item-form-addToBag-button']");
    By viewBagButton = By.xpath("//a[normalize-space(text())='VIEW BAG']");
    By bagItem = By.cssSelector("div.sbm-item[data-itemnumber='W40428']");
    By itemCheckout = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[3]/div[2]/div/div/a");
    By reg = By.id("registrationBtn");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage() {
        driver.get("https://www.next.co.uk/");
        driver.manage().window().maximize();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
        }
        catch (Exception e) {
            System.out.println("No cookie banner found");
        }
    }


    public void searchFor(String productName) {
//       Wait for search box to appear
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(productName);

        // Wait for and click search icon
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(submitSearch));
        searchBtn.click();

    }

    public void pdpSizeSelect(String sizeSelect) throws InterruptedException {

        List<WebElement> sizes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pdpSize));

        boolean found = false;
        for (WebElement size : sizes) {
            String label = size.getAttribute("aria-label"); // example: "10 available"
            if (label != null && label.contains(sizeSelect)) {
                // Scroll the element into view
                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", size);

                // Optional: small wait to ensure smooth scroll
                Thread.sleep(500);

                try {
                    // Try to hide banner if it's present
                    List<WebElement> banners = driver.findElements(By.cssSelector("div.delivery-banner-item"));
                    if (!banners.isEmpty()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", banners.get(0));
                        System.out.println("Banner hidden successfully.");
                    } else {
                        System.out.println("No delivery banner found, continuing...");
                    }
                } catch (Exception e) {
                    System.out.println("No delivery banner found, continuing...");
                }

                // ✅ SAFER click using JavaScript (prevents ElementClickInterceptedException)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", size);
                System.out.println("Clicked on size: " + sizeSelect);

                found = true;
                break;
            }

        }

        if (!found) {
            throw new NoSuchElementException("Size '" + sizeSelect + "' not found or not available.");
        }
    }

    public void addToBag() {
        try {
            // Wait for Add to Bag button to be clickable
            WebElement addToBag = wait.until(ExpectedConditions.elementToBeClickable(addToBagButton));
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToBag);

            try {
                // Try to hide banner if it's present
                List<WebElement> banners = driver.findElements(By.cssSelector("div.delivery-banner-item"));
                if (!banners.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", banners.get(0));
                    System.out.println("Banner hidden successfully.");
                } else {
                    System.out.println("No delivery banner found, continuing...");
                }
            } catch (Exception e) {
                System.out.println("No delivery banner found, continuing...");
            }

            addToBag.click();
            System.out.println("Clicked on Add to Bag button.");

            // Wait for View Bag or confirmation to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(viewBagButton));
            System.out.println("Add to Bag confirmed successfully.");

        } catch (TimeoutException e) {
            System.out.println("Add to Bag button or confirmation not found.");
        }
    }

    public void verifyItemInBag(String itemName) {
        WebElement viewBag = wait.until(ExpectedConditions.elementToBeClickable(viewBagButton));
        viewBag.click();

        // Verify item is present
        // After clicking Add to Bag and then View Bag
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the shopping bag container to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("items")));

        // Verify that the item with the expected item number is present
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(bagItem));

        // You can further assert the product name or price
        String productName = item.findElement(By.cssSelector("p.sbm-item-description")).getText();
        System.out.println("Product found in bag: " + productName);
        Assert.assertTrue(productName.contains(itemName), "Product not found in bag!");

    }

    public void checkoutProduct(){
         WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(itemCheckout));
         checkoutButton.click();

    }

    public void registerNow(){
        WebElement registerButtonClick = wait.until(ExpectedConditions.elementToBeClickable(reg));
        registerButtonClick.click();
    }


}
