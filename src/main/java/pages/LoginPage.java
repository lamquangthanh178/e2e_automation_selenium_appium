package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private static final By EMAIL_INPUT = By.id("identifier");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Login')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.debug("LoginPage initialized");
    }

    public void login(String email, String password) {
        logger.info("Logging in with email: {}", email);
        elementActions.type(EMAIL_INPUT, email);
        elementActions.type(PASSWORD_INPUT, password);
        elementActions.click(LOGIN_BUTTON);
        waitUtils.waitForPageLoad();
        logger.info("Login completed");
    }
}