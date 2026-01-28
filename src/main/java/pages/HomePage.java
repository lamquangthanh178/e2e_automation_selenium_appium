package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private static final By LOGIN_LINK = By.xpath("//a[text()='Login']");

    public HomePage(WebDriver driver) {
        super(driver);
        logger.debug("HomePage initialized");
    }

    public void clickLoginLink() {
        logger.info("Clicking Login link");
        elementActions.click(LOGIN_LINK);
        waitUtils.waitForPageLoad();
        logger.debug("Login link clicked - navigating to login page");
    }
}