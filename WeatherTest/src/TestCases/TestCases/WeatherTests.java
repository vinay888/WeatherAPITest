package TestCases;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import APIClasses.FileandEnv;
import ObjectRepository.HomePage;
import ObjectRepository.WeatherPage;
import UtilityClasses.ReadAPIConfig;
import UtilityClasses.TestBaseClass;
import UtilityClasses.Weather;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherTests extends TestBaseClass
{
	@Test(testName = "Check data for selected city", priority = 1)
	public void tempValidationForSpecificCity() 
	{
		HomePage.getInstance().clickSubmenuElement();
		HomePage.getInstance().clickOnWeatherSubmenuElement();
		
		WeatherPage.getInstance().setCurrentCity("Pune");
		waitForPageLoad();
		WeatherPage.getInstance().searchForCity();
				
		WeatherPage.getInstance().selectSearchedCity();
			
		WeatherPage.getInstance().verifyIfCityVisibleOnMap();
		
		WeatherPage.getInstance().clickOnCityOnMap();
		WeatherPage.getInstance().verifyCitySelection();			
	}
	
	@Test(testName = "Check data for random city", priority = 2)
	public void tempValidationForRandomCity() 
	{
		WeatherPage.getInstance().selectRandomCity();
		WeatherPage.getInstance().verifyCitySelection();				
	}
	
	@Test(testName = "Check temperature from UI and API", priority = 3)
	public void tempValidationForUIAndAPI() 
	{
		WeatherPage.getInstance().clickOnCityOnMap();
		Weather uiWeather = WeatherPage.getInstance().getUIWeatherObject();	
		
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
}
