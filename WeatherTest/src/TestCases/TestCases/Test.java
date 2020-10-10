package TestCases;

import org.json.JSONArray;

import ObjectRepository.WeatherPage;
import UtilityClasses.ReadAPIConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test
{

	public static void main(String[] args)
	{		
		try
		{
			RestAssured.baseURI = ReadAPIConfig.getInstance().getConfig("BASE_URL");
			Response response = RestAssured.given()
						.param("city name", WeatherPage.getInstance().getCurrentCity())
						.param("API key", ReadAPIConfig.getInstance().getConfig("API_KEY"))
						.when().get();
			JSONArray array = new JSONArray(response.getBody().asString());
			
			for(int i=0; i<array.length();i++) 
			{
				//System.out.println(array.get(i));
				
				//JSONObject obj = array.getJSONObject(i);
				//System.out.println(obj.get("title"));
			}
		}
		catch (Exception e)
		{
			System.out.println("Locators file not found");
		}	 
	}

}
