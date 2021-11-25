package web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * PropertiesReader help read properties file, get property with specific key
 * 
 */
public class PropertiesReader {
	
	private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            FileInputStream f = new FileInputStream(filePath);
            properties.load(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPropertyValue(String path, String key){
        Properties p = loadProperties(path);
        String result = "";

        Set<String> values = p.stringPropertyNames();
        for(String value : values){
            if(StringUtils.equalsIgnoreCase(value, key)){
                result = p.getProperty(value);
                break;
            }
        }
        return result;
    }
}
