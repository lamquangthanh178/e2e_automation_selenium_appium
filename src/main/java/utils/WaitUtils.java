package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * WaitUtils provides various wait strategies for synchronization
 */
public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementVisible(By locator) {
        try {
            logger.debug("Waiting for element to be visible: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element visible: {}", locator, e);
            throw new RuntimeException("Element not visible within timeout: " + locator);
        }
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementClickable(By locator) {
        try {
            logger.debug("Waiting for element to be clickable: {}", locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element clickable: {}", locator, e);
            throw new RuntimeException("Element not clickable within timeout: " + locator);
        }
    }

    /**
     * Wait for element presence in DOM
     */
    public WebElement waitForElementPresence(By locator) {
        try {
            logger.debug("Waiting for element presence: {}", locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element presence: {}", locator, e);
            throw new RuntimeException("Element not present within timeout: " + locator);
        }
    }

    /**
     * Wait for element to be invisible
     */
    public boolean waitForElementInvisible(By locator) {
        try {
            logger.debug("Waiting for element to be invisible: {}", locator);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for element invisible: {}", locator, e);
            return false;
        }
    }

    /**
     * Wait for page to load completely
     */
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

    /**
     * Wait for all elements matching locator
     */
    public java.util.List<WebElement> waitForElementsPresence(By locator) {
        try {
            logger.debug("Waiting for elements presence: {}", locator);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            logger.error("Timeout waiting for elements presence: {}", locator, e);
            return java.util.Collections.emptyList();
        }
    }

    /**
     * Wait for custom condition with timeout
     */
    public boolean waitForCondition(long timeoutSeconds, String conditionDescription) {
        try {
            logger.debug("Waiting for condition: {}", conditionDescription);
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(driver -> true);
            return true;
        } catch (Exception e) {
            logger.error("Timeout for condition: {}", conditionDescription, e);
            return false;
        }
    }

    /**
     * Wait and get element with implicit wait handling
     */
    public WebElement getElement(By locator) {
        try {
            return waitForElementPresence(locator);
        } catch (Exception e) {
            logger.warn("Failed to get element: {}", locator);
            return null;
        }
    }

    /**
     * Check if element exists without throwing exception
     */
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            logger.debug("Element not found: {}", locator);
            return false;
        }
    }
}