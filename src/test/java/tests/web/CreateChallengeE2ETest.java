package tests.web;

import base.BaseTest;
import helpers.CommonActions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateChallengePage;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyChallengesPage;
import utils.ConfigReader;

@Feature("Challenge Creation E2E")
public class CreateChallengeE2ETest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateChallengeE2ETest.class);

    private String username;
    private String password;
    private String challengeTitle;
    private String challengeFlag;
    private String howToSolve;

    private CommonActions commonActions;
    private HomePage homePage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CreateChallengePage createChallengePage;
    private MyChallengesPage myChallengesPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        logger.info("========== E2E Test Setup Started ==========");

        username = ConfigReader.getProperty("username");
        password = ConfigReader.getProperty("password");
        challengeTitle =  "Thanh Title "+ System.currentTimeMillis();
        challengeFlag = "CTFlearn{m4st3r_h4ck3r}";
        howToSolve = "This challenges were solved by automation test";

        commonActions = new CommonActions(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        createChallengePage = new CreateChallengePage(driver);
        myChallengesPage = new MyChallengesPage(driver);

        logger.info("========== Setup Complete ==========");
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("E2E Test: Home -> Login -> Create Challenge -> Verify -> Logout")
    public void testCreateChallengeE2E() throws InterruptedException {
        logger.info("===== STARTING E2E TEST: CREATE CHALLENGE =====");

        logger.info("STEP 1: Navigate to CTFLearner Home");
        commonActions.navigateToBaseUrl();

        logger.info("→ Clicking Login link on Home Page");
        homePage.clickLoginLink();

        logger.info("STEP 2: Login to CTFLearner");
        loginPage.login(username, password);

        logger.info("STEP 3: Navigate to Create Challenge");
        dashboardPage.clickCreateChallenge();

        logger.info("STEP 4: Create Challenge and verify this challenge created successfully");
        createChallengePage.createChallenge(challengeTitle, challengeFlag, howToSolve);
        Assert.assertTrue(myChallengesPage.isChallengeDisplayed(challengeTitle));

        logger.info("STEP 5: Logout");
        dashboardPage.logout();
        logger.info("===== E2E TEST PASSED ✓ =====");
    }
}