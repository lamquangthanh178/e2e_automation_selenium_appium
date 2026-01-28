package base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementActions;
import utils.WaitUtils;

public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected ElementActions elementActions;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
        this.waitUtils = new WaitUtils(driver);
        logger.debug("BasePage initialized for: {}", this.getClass().getSimpleName());
    }
}