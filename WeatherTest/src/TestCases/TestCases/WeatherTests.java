package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import APIClasses.APIHandler;
import APIClasses.WeatherAPIBuilder;
import ObjectRepository.HomePage;
import ObjectRepository.WeatherPage;
import Resources.VARIANCE_TYPES;
import UtilityClasses.TestBaseClass;
import UtilityClasses.Weather;
import UtilityClasses.WeatherComparator;
import io.restassured.response.Response;


public class WeatherTests extends TestBaseClass
{
	@Test(testName = "Verify UI data visibility for selected city", priority = 1)
	public void tempValidationForSpecificCity() 
	{
		// Click on sub-menu and weather element
		HomePage.getInstance().clickSubmenuElement();
		HomePage.getInstance().clickOnWeatherSubmenuElement();
		
		// Search for given city
		// City hard-coded currently but in actually could be read from any data source - Excel, Properties, xml, csv etc.
		WeatherPage.getInstance().setCurrentCity("Pune");
		waitForPageLoad();
		WeatherPage.getInstance().searchForCity();
		
		// Verify city is visible on map
		WeatherPage.getInstance().selectSearchedCity();			
		WeatherPage.getInstance().verifyIfCityVisibleOnMap();
		
		// Verify city details pop-up is visible on map
		WeatherPage.getInstance().clickOnCityOnMap();
		WeatherPage.getInstance().verifyCitySelection();
		WeatherPage.getInstance().clickOnCityOnMap();
	}
	
	@Test(testName = "Verify UI data visibility for random city", priority = 2)
	public void tempValidationForRandomCity() 
	{
		// Select Random city from the map and Verify city details pop-up is visible on map
		WeatherPage.getInstance().selectRandomCity();
		WeatherPage.getInstance().verifyCitySelection();
		WeatherPage.getInstance().unselectRandomCity();
	}
	
	@Test(testName = "Check weather from UI and API", priority = 3)
	public void tempValidationForUIAndAPI() 
	{
		// click on city details on map and create weather object from UI
		WeatherPage.getInstance().clickOnCityOnMap();
		Weather uiWeather = WeatherPage.getInstance().getUIWeatherObject();	
		
		test.log(LogStatus.INFO, "UI Weather Object Details : Temperature - " + uiWeather.getTemperature() + ", Humidity - " + uiWeather.getHumidity() + ", WindSpeed - " + uiWeather.getwindSpeed());
		
		// Set current city for API
		WeatherAPIBuilder.setCurrentCity("Pune");
				
		// Get Response for the API
		Response response = APIHandler.getRequestResponse(new WeatherAPIBuilder());		
		
		// Verify the response code
		Assert.assertEquals(response.getStatusCode(), 200, "Error in Response. Check status line :" + response.getStatusLine());
		
		// Create weather object from API response
		Weather apiWeather = new Weather(response);
		
		test.log(LogStatus.INFO, "API Weather Object Details : Temperature - " + apiWeather.getTemperature() + ", Humidity - " + apiWeather.getHumidity() + ", WindSpeed - " + apiWeather.getwindSpeed());
		
		// compare UI & API weather object and print result
		WeatherComparator comp = new WeatherComparator();
		comp.setVarianceType(VARIANCE_TYPES.HIGH);
		int result = comp.compare(uiWeather, apiWeather);
		
		Assert.assertEquals(result == 0 || result == 1, true, "Result for comparasion of Ui & API weather objects.");
		
		/*if(result == 0)
			System.out.println("Weather details on API and UI are same");
		else if(result == 1)
			System.out.println("Weather details on API and UI are within variance. Variance Type is : ");
		else
			System.out.println("Weather details on API and UI are not within variance");*/
	}
}
