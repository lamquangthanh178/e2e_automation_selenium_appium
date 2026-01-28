package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    public WebElement waitForElementVisible(By locator) {
        try {
            logger.debug("Waiting for element to be visible: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element visible: {}", locator, e);
            throw new RuntimeException("Element not visible within timeout: " + locator);
        }
    }

    public WebElement waitForElementClickable(By locator) {
        try {
            logger.debug("Waiting for element to be clickable: {}", locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element clickable: {}", locator, e);
            throw new RuntimeException("Element not clickable within timeout: " + locator);
        }
    }

    public void waitForPageLoad() {
        try {
            logger.debug("Waiting for page to load");
            wait.until(driver -> {
                return ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            });
            logger.debug("Page loaded successfully");
        } catch (Exception e) {
            logger.warn("Timeout waiting for page load", e);
        }
    }
}