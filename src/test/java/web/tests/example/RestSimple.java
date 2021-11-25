package web.tests.example;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestSimple {
	
	//@Test
	public void GetQueryParameter() {
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
		
		String jsonString = response.asString();
		System.out.println(jsonString);
		Assert.assertTrue(jsonString.contains("Vung Tau"), "Json string contains 'Vung Tau'");
	}

}
