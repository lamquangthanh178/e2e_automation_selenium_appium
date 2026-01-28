package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import utils.WaitUtils;

public class CommonActions {
    private static final Logger logger = LoggerFactory.getLogger(CommonActions.class);
    private WebDriver driver;
    private WaitUtils waitUtils;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.navigate().to(url);
        waitUtils.waitForPageLoad();
        logger.info("Successfully navigated to: {}", url);
    }

    public void navigateToBaseUrl() {
        navigateToUrl(ConfigReader.getBaseUrl());
    }

    public WebDriver getDriver() {
        return driver;
    }
}