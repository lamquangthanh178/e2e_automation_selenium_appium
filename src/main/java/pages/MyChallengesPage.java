package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyChallengesPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(MyChallengesPage.class);

    private static final String CHALLENGE_NAME_XPATH = "//span[@id='title-display' and contains(text(),'%s')]";

    public MyChallengesPage(WebDriver driver) {
        super(driver);
        logger.debug("MyChallengesPage initialized");
    }

    public boolean isChallengeDisplayed(String challengeName) {
        logger.info("Checking if challenge is displayed: {}", challengeName);
        By challengeLocator = By.xpath(String.format(CHALLENGE_NAME_XPATH, challengeName));
        return elementActions.isDisplayed(challengeLocator);
    }
}