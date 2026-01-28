package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Manages environment-specific configuration properties
 * Supports: dev, qa, staging environments
 * Handles both Web and Mobile (iOS) configurations
 */
public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties;
    private static String environment = "dev";

    static {
        loadProperties();
    }

    /**
     * Load properties from environment-specific file
     */
    private static void loadProperties() {
        properties = new Properties();
        String env = System.getProperty("env", environment);
        String propertyFilePath = "src/test/resources/config/" + env + ".properties";

        try (FileInputStream fileInputStream = new FileInputStream(propertyFilePath)) {
            properties.load(fileInputStream);
            environment = env;
            logger.info("Properties loaded successfully for environment: {}", env);
        } catch (IOException e) {
            logger.error("Failed to load properties file: {}", propertyFilePath, e);
            throw new RuntimeException("Failed to load configuration file: " + propertyFilePath);
        }
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: {}", key);
            return "";
        }
        return value;
    }

    /**
     * Get property with default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get current environment
     */
    public static String getEnvironment() {
        return environment;
    }

    // ============ WEB CONFIGURATION ============

    /**
     * Get base URL for web tests
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    /**
     * Get browser type (chrome, firefox, edge)
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get explicit wait time in seconds
     */
    public static long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait", "15"));
    }

    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }

    // ============ MOBILE (iOS) CONFIGURATION ============

    /**
     * Get Appium server URL
     * Default: http://127.0.0.1:4723
     */
    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url", "http://127.0.0.1:4723");
    }

    /**
     * Get device name (e.g., iPhone 15, iPhone Simulator)
     */
    public static String getDeviceName() {
        return getProperty("device.name", "iPhone 15");
    }

    /**
     * Get platform version (e.g., 17.0, 17.2)
     */
    public static String getPlatformVersion() {
        return getProperty("platform.version", "17.0");
    }

    /**
     * Get app path (.app file path for initial installation)
     */
    public static String getAppPath() {
        return getProperty("app.path", "");
    }

    /**
     * Get bundle ID (e.g., io.appium.TestApp)
     * Used to launch the app on device
     */
    public static String getBundleId() {
        return getProperty("bundle.id", "io.appium.TestApp");
    }

    /**
     * Get UDID (Unique Device Identifier)
     * Required only for physical devices
     */
    public static String getUdid() {
        return getProperty("udid", "");
    }

    /**
     * Check if noReset capability is enabled
     * When true: doesn't uninstall app before test (faster)
     * When false: uninstalls app before each test (fresh state)
     */
    public static boolean isNoReset() {
        return Boolean.parseBoolean(getProperty("no.reset", "true"));
    }

    /**
     * Check if waitForQuiescence is enabled
     * When true: waits for app to become idle before proceeding
     * When false: proceeds immediately
     */
    public static boolean isWaitForQuiescence() {
        return Boolean.parseBoolean(getProperty("wait.for.quiescence", "false"));
    }

    /**
     * Check if useJSONSource is enabled
     * When true: uses JSON representation of UI elements
     * When false: uses standard XPath
     */
    public static boolean isUseJSONSource() {
        return Boolean.parseBoolean(getProperty("use.json.source", "true"));
    }

    /**
     * Get newCommandTimeout in seconds
     * How long to wait for a new command before timing out
     * Default: 300 seconds (5 minutes)
     */
    public static long getNewCommandTimeout() {
        return Long.parseLong(getProperty("new.command.timeout", "300"));
    }
}