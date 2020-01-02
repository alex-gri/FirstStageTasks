package iohelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static Logger logger = LogManager.getLogger();
    private static String propertyFilePath = "src/main/resources/config.properties";
    private static String errorMessage = "Error: Property file is not found!";

    public static void writeProperty(String key, String value) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(propertyFilePath);
            properties.load(fileInputStream);
            fileInputStream.close();

            properties.setProperty(key, value + ".docx");

            fileOutputStream = new FileOutputStream(propertyFilePath);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            logger.error(errorMessage);
        }
    }

    public static String readProperty(String key) {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(propertyFilePath);
            properties.load(fileInputStream);
            fileInputStream.close();
            return properties.getProperty(key);
        } catch (IOException e) {
            logger.error(errorMessage);
            return null;
        }
    }
}
