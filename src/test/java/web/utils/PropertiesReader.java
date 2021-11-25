package web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


/**
 * PropertiesReader help read properties file, get property with specific key
 * 
 */
public final class PropertiesReader {
	
	private PropertiesReader() {
		
	}
	
	private static Properties loadProperties(final String propertyFilePath) {
        final Properties properties = new Properties();
        try(InputStream fileInputStream = Files.newInputStream(Paths.get(propertyFilePath))) {
            	properties.load(fileInputStream);
           
        } catch (IOException e){
            
        }
        return properties;
    }

	/**
	 * This method is intended to get property value from given key
	 * 
	 * @param propertyFilePath the property file path
	 * @param key the key need to get value from 
	 */
    public static String getPropertyValue(final String propertyFilePath, final String key){
        final Properties property = loadProperties(propertyFilePath);
        
        final Set<String> values = property.stringPropertyNames();
        
        for(final String value : values){
            if(StringUtils.equalsIgnoreCase(value, key)){
                return property.getProperty(value);
            }
        }
        return "";
    }
}
