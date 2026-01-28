package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ElementActions - Common element interactions utility
 */
public class ElementActions {
    private static final Logger logger = LoggerFactory.getLogger(ElementActions.class);
    private WebDriver driver;
    private WaitUtils waitUtils;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Type text in an element
     */
    public void type(By locator, String text) {
        try {
            WebElement element = waitUtils.waitForElementVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.debug("Typed '{}' in element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to type in element: {}", locator, e);
            throw new RuntimeException("Failed to type in element", e);
        }
    }

    /**
     * Click an element
     */
    public void click(By locator) {
        try {
            WebElement element = waitUtils.waitForElementVisible(locator);
            element.click();
            logger.debug("Clicked element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", locator, e);
            throw new RuntimeException("Failed to click element", e);
        }
    }

    /**
     * Check if element is displayed
     */
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = waitUtils.waitForElementVisible(locator);
            boolean displayed = element.isDisplayed();
            logger.debug("Element displayed: {}", displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }

    /**
     * Check if element exists
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