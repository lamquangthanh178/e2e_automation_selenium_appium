package drivers;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import java.net.URL;
import java.net.MalformedURLException;

public class MobileDriverManager {
    private static final Logger logger = LoggerFactory.getLogger(MobileDriverManager.class);
    private static final ThreadLocal<IOSDriver> appiumDriverThreadLocal = new ThreadLocal<>();

    public static IOSDriver initializeAppiumDriver() {
        try {
            logger.info("========== Initializing Appium IOSDriver ==========");
            DesiredCapabilities capabilities = createIOSCapabilities();
            URL appiumServerUrl = new URL(ConfigReader.getAppiumServerUrl());
            IOSDriver driver = new IOSDriver(appiumServerUrl, capabilities);
            appiumDriverThreadLocal.set(driver);
            logger.info("========== Appium IOSDriver initialized successfully ==========");
            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + ConfigReader.getAppiumServerUrl(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium IOSDriver: " + e.getMessage(), e);
        }
    }

    private static DesiredCapabilities createIOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:deviceName", ConfigReader.getDeviceName());

        String platformVersion = ConfigReader.getPlatformVersion();
        if (platformVersion != null && !platformVersion.isEmpty()) {
            capabilities.setCapability("appium:platformVersion", platformVersion);
        }

        capabilities.setCapability("appium:bundleId", ConfigReader.getBundleId());
        capabilities.setCapability("appium:noReset", ConfigReader.isNoReset());
        capabilities.setCapability("appium:waitForQuiescence", ConfigReader.isWaitForQuiescence());
        capabilities.setCapability("appium:useJSONSource", ConfigReader.isUseJSONSource());
        capabilities.setCapability("appium:newCommandTimeout", ConfigReader.getNewCommandTimeout());
        capabilities.setCapability("appium:shouldTerminateApp", true);

        String appPath = ConfigReader.getAppPath();
        if (appPath != null && !appPath.isEmpty()) {
            capabilities.setCapability("appium:app", appPath);
        }

        String udid = ConfigReader.getUdid();
        if (udid != null && !udid.isEmpty()) {
            capabilities.setCapability("appium:udid", udid);
        }

        return capabilities;
    }

    public static void closeAppiumDriver() {
        IOSDriver driver = appiumDriverThreadLocal.get();
        if (driver != null) {
            try {
                logger.info("========== Closing Appium IOSDriver ==========");
                String bundleId = ConfigReader.getBundleId();
                driver.terminateApp(bundleId);
                driver.quit();
                appiumDriverThreadLocal.remove();
                logger.info("Appium IOSDriver closed and App {} terminated", bundleId);
            } catch (Exception e) {
                logger.error("Error closing Appium IOSDriver", e);
            }
        }
    }

}