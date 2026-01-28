package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private static final By CHALLENGES_DROPDOWN = By.xpath("//a[@id='navbarDropdownMenuLink']/following-sibling::a");
    private static final By CREATE_CHALLENGE_BUTTON = By.xpath("//a[contains(text(), 'Create Challenge')]");
    private static final By MY_CHALLENGES_LINK = By.xpath("//a[contains(text(), 'My Challenges')]");
    private static final By USER_MENU = By.id("profileDropdown");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@href='/user/logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
        logger.debug("DashboardPage initialized");
    }

    public void clickCreateChallenge() {
        logger.info("Clicking Create Challenge button");
        elementActions.click(CHALLENGES_DROPDOWN);
        elementActions.click(CREATE_CHALLENGE_BUTTON);
        waitUtils.waitForPageLoad();
        logger.info("Create Challenge page loading");
    }

    public void logout() throws InterruptedException {
        logger.info("Logging out");
        if (elementActions.isElementPresent(USER_MENU)) {
            elementActions.click(USER_MENU);
        }
        elementActions.click(LOGOUT_BUTTON);
        waitUtils.waitForPageLoad();
        logger.info("Logout completed");
    }
}