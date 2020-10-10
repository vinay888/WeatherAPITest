package UtilityClasses;

import java.util.ArrayList;

public class Weather
{
	private double temperature;
	private double windSpeed;
	private double humidity;
	
	public Weather(ArrayList<String> weatherData)
	{
		for(String data : weatherData)
		{
			if(data.contains(":"))
				if(data.split(":")[0].trim().contains("Wind"))
					setwindSpeed(data);
				else if(data.trim().contains("Humidity"))
					setHumidity(data);
				else if(data.trim().contains("Fahrenheit"))
					setTemperature(data);
		}
	}
	
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
		windString = windString.split(":")[1].trim().split(" ")[0].trim();
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
}
