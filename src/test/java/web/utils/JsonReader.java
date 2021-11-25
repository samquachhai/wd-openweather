/**
 * Json reader help parsing JSON file
 */

package web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * JsonReader is an helper for manipulating with JSON file 
 * 
 */

public class JsonReader {
	
	/**
	 * This method is intended to read data from Json file.
	 * 
	 * 
	 * @param jsonFilePath the Json file path to be read
	 * @param dataObject the data object in Json file to get
	 * @param totalKeys total keys of the data object
	 * 
	 */
	public static Object[][] getData(String jsonFilePath, String dataObject, int totalKeys) throws FileNotFoundException
	{ 
		File jsonFile = new File(jsonFilePath);
		InputStream jsonInputStream = new FileInputStream(jsonFile);
		
	    @SuppressWarnings("resource")
		String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();
	    
	    // Get document context for path evaluation
	    DocumentContext context = JsonPath.parse(jsonString);
	    
	    // Read the given path from this context
        List<Map<String, Object>> dataList = context.read("$['" + dataObject + "'][*]");
	        
        int records = dataList.size();
        
        // Create a 2d object array from the list of object maps keys values
        Object[][] matrix = new Object[records][totalKeys];

        int i = 0;
        int j = 0;
        
        for(Map<String, Object> item: dataList) {
            for(Entry<String, Object> entry : item.entrySet()) {
            	 matrix[i][j] = entry.getValue().toString().replace("\"","");
                 j++; 
                
            }
            
            i++;
            j = 0;
        }
         
        return matrix;
        
	}
	
}
