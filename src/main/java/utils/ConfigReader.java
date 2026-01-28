package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties;
    private static String environment = "dev";

    static {
        loadProperties();
    }

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

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: {}", key);
            return "";
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getEnvironment() {
        return environment;
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait", "15"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }

    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url", "http://127.0.0.1:4723");
    }

    public static String getDeviceName() {
        return getProperty("device.name", "iPhone 15");
    }

    public static String getPlatformVersion() {
        return getProperty("platform.version", "17.0");
    }

    public static String getAppPath() {
        return getProperty("app.path", "");
    }

    public static String getBundleId() {
        return getProperty("bundle.id", "io.appium.TestApp");
    }

    public static String getUdid() {
        return getProperty("udid", "");
    }

    public static boolean isNoReset() {
        return Boolean.parseBoolean(getProperty("no.reset", "true"));
    }

    public static boolean isWaitForQuiescence() {
        return Boolean.parseBoolean(getProperty("wait.for.quiescence", "false"));
    }

    public static boolean isUseJSONSource() {
        return Boolean.parseBoolean(getProperty("use.json.source", "true"));
    }

    public static long getNewCommandTimeout() {
        return Long.parseLong(getProperty("new.command.timeout", "300"));
    }
}