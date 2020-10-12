package TestCases;

import APIClasses.IApiBuilder;
import APIClasses.WeatherAPIBuilder;
import ObjectRepository.WeatherPage;
import UtilityClasses.ReadAPIConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test
{

	public static void main(String[] args)
	{		
		try
		{
			IApiBuilder builder = new WeatherAPIBuilder();
			/*RestAssured.baseURI = ReadAPIConfig.getInstance().getConfig("BASE_URL") 
					+ ReadAPIConfig.getInstance().getConfig("WEATHER_API_BASE_PATH");		
			
			WeatherPage.getInstance().setCurrentCity("Pune");
			
			Response response = RestAssured.given()
												.headers("Content-Type", "ContentType.JSON", "Accept", "ContentType.JSON")
												
												.param("q", WeatherPage.getInstance().getCurrentCity())
												.param("appId", ReadAPIConfig.getInstance().getConfig("WEATHER_API_KEY"))
												.param("units", "imperial")
											.when()
												.get()
											.then()
												.contentType(ContentType.JSON).extract().response();
			
			System.out.println(response.getBody().asString());
			
			JsonPath jsonPathEvaluator = response.jsonPath();
			
			System.out.println(jsonPathEvaluator.get("main.temp").toString());
			System.out.println(jsonPathEvaluator.get("main.humidity").toString());
			System.out.println(jsonPathEvaluator.get("wind.speed").toString());*/
			
			RestAssured.baseURI = builder.getEndpoint() + builder.getBasePath();	
			
			WeatherPage.getInstance().setCurrentCity("Pune");
			WeatherAPIBuilder.setCurrentCity("Pune");
			Response response = RestAssured.given()
												.headers(builder.getHeaders())												
												.params(builder.getQueryParams())												
											.when()
												.get()
											.then()
												.contentType(ContentType.JSON).extract().response();
			
			System.out.println(response.getBody().asString());
			
			JsonPath jsonPathEvaluator = response.jsonPath();
			
			System.out.println(jsonPathEvaluator.get("main.temp").toString());
			System.out.println(jsonPathEvaluator.get("main.humidity").toString());
			System.out.println(jsonPathEvaluator.get("wind.speed").toString());
			
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	 
	}

}
