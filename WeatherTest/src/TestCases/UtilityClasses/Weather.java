package UtilityClasses;

import java.util.ArrayList;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Weather
{
	private double temperature;
	private double windSpeed;
	private double humidity;

	/*-------------------------------- Getters and Setters ------------------------------------------------*/

	public double getTemperature()
	{
		return temperature;
	}

	private void setTemperature(String temperatureString)
	{
		temperatureString = temperatureString.split(":")[1].trim();
		temperature = Double.parseDouble(temperatureString);
	}

	public double getwindSpeed()
	{
		return windSpeed;
	}

	private void setwindSpeed(String windString)
	{
		String[] windStringArr = windString.trim().split(" ");
		windString = windStringArr[windStringArr.length - 2].trim();
		windSpeed = Double.parseDouble(windString);
	}

	public double getHumidity()
	{
		return humidity;
	}

	private void setHumidity(String humidityString)
	{
		humidityString = humidityString.split(":")[1].trim().replace("%", "");
		humidity = Double.parseDouble(humidityString);
	}	

	/*-------------------------------- CONSTRUCTORS ------------------------------------------------*/

	/**
	 * Creates weather object from weather data fetched from UI
	 */
	public Weather(ArrayList<String> weatherData)
	{
		try
		{
			for(String data : weatherData)
			{
				if(data.contains(":")) // this is done to avoid cases when weather description has words like windy, humidity etc.
					if(data.split(":")[0].trim().contains("Wind"))
						setwindSpeed(data);
					else if(data.trim().contains("Humidity"))
						setHumidity(data);
					else if(data.trim().contains("Fahrenheit"))
						setTemperature(data);
			}
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.FATAL, e.getStackTrace().toString());
		}
	}

	/**
	 * Creates a Weather object from the API response object
	 * @param response - API response object for Weather API
	 */
	public Weather(Response response)
	{

		// Fetch temp, wind, humidity details from response
		JsonPath jsonPathEvaluator = response.jsonPath();		
		temperature = Double.parseDouble(jsonPathEvaluator.get("main.temp").toString());
		humidity = Double.parseDouble(jsonPathEvaluator.get("main.humidity").toString());
		windSpeed = Double.parseDouble(jsonPathEvaluator.get("wind.speed").toString());
	}
}
