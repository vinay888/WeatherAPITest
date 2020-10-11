package TestCases;

import org.testng.annotations.Test;

import APIClasses.APIHandler;
import APIClasses.WeatherAPIBuilder;
import ObjectRepository.HomePage;
import ObjectRepository.WeatherPage;
import Resources.VARIANCE_TYPES;
import UtilityClasses.TestBaseClass;
import UtilityClasses.Weather;
import UtilityClasses.WeatherComparator;
import io.restassured.path.json.JsonPath;
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
		
		WeatherAPIBuilder.setCurrentCity("Pune");
		//IApiBuilder builder = new WeatherAPIBuilder();
		//builder.buildAPIParameters();
		
		Response response = APIHandler.getRequestResponse(new WeatherAPIBuilder());
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		double temp = Double.parseDouble(jsonPathEvaluator.get("main.temp").toString());
		double humidity = Double.parseDouble(jsonPathEvaluator.get("main.humidity").toString());
		double windSpeed = Double.parseDouble(jsonPathEvaluator.get("wind.speed").toString());
		
		Weather apiWeather = new Weather(temp, windSpeed, humidity);
		
		WeatherComparator comp = new WeatherComparator();
		comp.setVarianceType(VARIANCE_TYPES.HIGH);
		int result = comp.compare(uiWeather, apiWeather);
		
		if(result == 0)
			System.out.println("Weather details on API and UI are same");
		else if(result == 1)
			System.out.println("Weather details on API and UI are within variance. Variance Type is : ");
		else
			System.out.println("Weather details on API and UI are not within variance");
	}
}
