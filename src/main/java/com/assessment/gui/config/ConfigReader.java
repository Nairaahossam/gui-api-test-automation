package com.assessment.gui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + CONFIG_FILE + " on the classpath");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }
    }

    private ConfigReader() {
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }

    public static String getBrowser() {
        return PROPERTIES.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("headless"));
    }

    public static int getImplicitWaitSeconds() {
        return Integer.parseInt(PROPERTIES.getProperty("implicit.wait.seconds"));
    }

    public static int getExplicitWaitSeconds() {
        return Integer.parseInt(PROPERTIES.getProperty("explicit.wait.seconds"));
    }

    public static int getPageLoadTimeoutSeconds() {
        return Integer.parseInt(PROPERTIES.getProperty("page.load.timeout.seconds"));
    }
}