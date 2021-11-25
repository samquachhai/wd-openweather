package web.tests.example;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestSimple {
	
	/**
	 * Simple RestAssured test
	 * 
	 */
	//@Test
	public void restSimple() {
		//https://openweathermap.org/data/2.5/find?q=Vung%20Tau&appid=439d4b804bc8187953eb36d2a8c26a02&units=metric
		
		// REST base url
		RestAssured.baseURI = "https://openweathermap.org/data/2.5/";
		
		// Get the Request Specification
		RequestSpecification httpRequest = RestAssured.given();		
		
		// Get Response
		Response response = httpRequest.queryParam("q", "Vung%20Tau")
				.queryParam("appid", "439d4b804bc8187953eb36d2a8c26a02")
				.queryParam("units", "metric")
				.get("/find");
		
		
		// Check response cotains city name
		String jsonResponse = response.asString();
		boolean check =  jsonResponse.contains("Vung Tau");
		
		Assert.assertTrue(check, "Json string contains 'Vung Tau'");
	}

}
