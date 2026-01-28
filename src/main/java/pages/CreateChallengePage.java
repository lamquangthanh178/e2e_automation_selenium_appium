package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateChallengePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CreateChallengePage.class);

    private static final By CHALLENGE_TITLE_INPUT = By.id("title");
    private static final By CHALLENGE_FLAG_INPUT = By.xpath("//input[@name='flag']");
    private static final By HOW_TO_SOLVE_INPUT = By.id("howtosolve");
    private static final By CREATE_BUTTON = By.xpath("//button[@type='submit']");

    public CreateChallengePage(WebDriver driver) {
        super(driver);
        logger.debug("CreateChallengePage initialized");
    }

    public void enterChallengeName(String name) {
        logger.info("Entering challenge title: {}", name);
        elementActions.type(CHALLENGE_TITLE_INPUT, name);
    }

    public void enterFlag(String flag) {
        logger.info("Entering challenge flag");
        elementActions.type(CHALLENGE_FLAG_INPUT, flag);
    }

    public void enterHowToSolve(String solveText) {
        logger.info("Entering how to solve");
        elementActions.type(HOW_TO_SOLVE_INPUT, solveText);
    }

    public void clickCreateButton() {
        logger.info("Clicking Create button");
        elementActions.click(CREATE_BUTTON);
        waitUtils.waitForPageLoad();
        logger.debug("Create button clicked");
    }

    public void createChallenge(String name, String flag, String solveText) {
        logger.info("Creating challenge: {}", name);
        enterChallengeName(name);
        enterFlag(flag);
        enterHowToSolve(solveText);
        clickCreateButton();
        logger.info("Challenge creation submitted");
    }

}