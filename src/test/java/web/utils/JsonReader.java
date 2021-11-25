/**
 * Json reader help parsing JSON file
 */

package web.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

public final class JsonReader {
	
	private JsonReader() {
		
	}
	
	/**
	 * This method is intended to read data from Json file.
	 * 
	 * 
	 * @param jsonFilePath the Json file path to be read
	 * @param dataObject the data object in Json file to get
	 * @param totalKeys total keys of the data object
	 * 
	 */
	public static Object[][] getData(final String jsonFilePath, final String dataObject, final int totalKeys) throws FileNotFoundException
	{ 
		try(InputStream jsonInputStream = Files.newInputStream(Paths.get(jsonFilePath))) {
        	
			@SuppressWarnings("resource")
			String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();
		    
		    // Get document context for path evaluation
		    DocumentContext context = JsonPath.parse(jsonString);
		    
		    // Read the given path from this context
	        List<Map<String, Object>> dataList = context.read("$['" + dataObject + "'][*]");
		        
	        int records = dataList.size();
	        
	        // Create a 2d object array from the list of object maps keys values
	        Object[][] matrix = new Object[records][totalKeys];

	        int row = 0;
	        int col = 0;
	        
	        for(Map<String, Object> item: dataList) {
	            for(Entry<String, Object> entry : item.entrySet()) {
	            	 matrix[row][col] = entry.getValue().toString().replace("\"","");
	            	 col++; 
	                
	            }
	            
	            row = row + 1;
	            col = 0;
	        }
	         
	        return matrix;
		} catch (IOException e){
			 return new String[0][0];
		}		     
	}
}
