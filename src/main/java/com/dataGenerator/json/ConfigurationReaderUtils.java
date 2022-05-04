package com.dataGenerator.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public final class ConfigurationReaderUtils {
    private static Properties properties;

    public static Properties readConfiguration(String confPath) {
        properties = new Properties();
        try {

            File fl = new File(confPath);
            FileInputStream fstream = new FileInputStream(fl);
            if (fstream != null)
                properties.load(fstream);
            else
                throw new FileNotFoundException("property file '" + confPath + "'not found in classpath");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
        return properties;
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
