package base;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.info("========== Test Setup Started ==========");
        logger.info("Environment: {}", ConfigReader.getEnvironment());

        driver = DriverManager.initializeWebDriver();

        logger.info("========== Test Setup Completed ==========");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("========== Test Teardown Started ==========");

        try {
            DriverManager.closeWebDriver();
            logger.info("WebDriver closed successfully");
        } catch (Exception e) {
            logger.error("Error during teardown", e);
        }

        logger.info("========== Test Teardown Completed ==========");
    }
//
//    protected WebDriver getDriver() {
//        return driver;
//    }
}