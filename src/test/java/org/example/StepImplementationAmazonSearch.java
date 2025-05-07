package org.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.AfterSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepImplementationAmazonSearch {

    private static WebDriver driver;

    @BeforeSuite
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Open Amazon website")
    public void openAmazon() {
        driver.get("https://www.amazon.in");
        driver.manage().window().maximize();
    }

    @Step("Click on Search bar")
    public void clickSearchBar() {
        driver.findElement(By.id("twotabsearchtextbox")).click();
    }

    @Step("Type <Mobiles> in Search bar")
    public void typeInSearchBar(String Mobiles) {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Mobiles);
    }

    @Step("Hit the Search button")
    public void hitSearchButton() {
        driver.findElement(By.id("nav-search-submit-button")).click();
    }

    @Step("Click <Number> mobile in the list")
    public void implementation5(int Number) throws InterruptedException {
        List<WebElement> items = driver.findElements(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']"));
        if (items.size() >= 10) {
            items.get(Number).findElement(By.cssSelector("h2")).click();
        } else {
            throw new RuntimeException("Less than 10 search results found.");
        }
        Thread.sleep(8000);
    }

    @Step("Verify that display mobile name is <expectedName>")
    public void verifyMobileName(String expectedName) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        WebElement titleElement = driver.findElement(By.id("productTitle"));
        String actualName = titleElement.getText().trim();
        if (!actualName.contains(expectedName)) {
            throw new AssertionError("Expected: " + expectedName + " but found: " + actualName);
        }
    }
}
