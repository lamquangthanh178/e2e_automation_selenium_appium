package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class TestAppPage {
    private IOSDriver driver;

    public TestAppPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @iOSXCUITFindBy(accessibility = "IntegerA")
    private WebElement inputFirstNumber;

    @iOSXCUITFindBy(accessibility = "IntegerB")
    private WebElement inputSecondNumber;

    @iOSXCUITFindBy(accessibility = "ComputeSumButton")
    private WebElement btnComputeSum;

    @iOSXCUITFindBy(accessibility = "Answer")
    private WebElement txtAnswer;

    public void calculateSum(String num1, String num2) {
        inputFirstNumber.clear();
        inputFirstNumber.sendKeys(num1);
        inputSecondNumber.clear();
        inputSecondNumber.sendKeys(num2);
        btnComputeSum.click();
    }

    public String getResult() {
        return txtAnswer.getText();
    }
}