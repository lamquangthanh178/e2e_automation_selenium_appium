package tests.mobile;

import drivers.MobileDriverManager;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TestAppPage;

public class ComputeSumTest {
    private IOSDriver driver;
    private TestAppPage testAppPage;

    @BeforeMethod
    public void setUp() {
        driver = MobileDriverManager.initializeAppiumDriver();
        testAppPage = new TestAppPage(driver);
    }

    @Test(description = "Verify that sum of two integers is calculated correctly")
    public void testComputeSum() {
        String num1 = "3";
        String num2 = "2";
        String expectedSum = "5";

        testAppPage.calculateSum(num1, num2);

        String actualSum = testAppPage.getResult();
        Assert.assertEquals(actualSum, expectedSum, "Verify that sum of two integers is calculated correctly");
    }

    @AfterMethod
    public void tearDown() {
        MobileDriverManager.closeAppiumDriver();
    }
}