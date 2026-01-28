package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver initializeWebDriver() {
        try {
            String browser = ConfigReader.getBrowser().toLowerCase();
            logger.info("Initializing WebDriver for browser: {}", browser);

            WebDriver driver;

            if ("chrome".equals(browser)) {
                driver = initializeChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = initializeFirefoxDriver();
            } else if ("edge".equals(browser)) {
                driver = initializeEdgeDriver();
            } else {
                logger.warn("Unknown browser: {}, defaulting to Chrome", browser);
                driver = initializeChromeDriver();
            }

            webDriverThreadLocal.set(driver);
            webDriverThreadLocal.set(driver);
            logger.info("WebDriver initialized successfully");
            return driver;
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver", e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private static WebDriver initializeChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            logger.info("Chrome running in headless mode with CI optimizations");
        }

        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

    private static WebDriver initializeFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Firefox running in headless mode");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver initializeEdgeDriver() {
        return new EdgeDriver();
    }

    public static void closeWebDriver() {
        WebDriver driver = webDriverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                webDriverThreadLocal.remove();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error closing WebDriver", e);
            }
        }
    }
}