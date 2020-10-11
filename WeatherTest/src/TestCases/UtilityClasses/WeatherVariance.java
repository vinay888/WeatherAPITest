package UtilityClasses;

import java.util.Objects;

import Resources.VARIANCE_TYPES;

public class WeatherVariance
{
	private double tempVariance, windSpeedVariance, humidityVariance;
	
	/**
	 * Constructor to create object based on type of variance passed
	 * @param varianceType - EnumType - VARIANCE_TYPES with values HIGH, MEDIUM or LOW
	 */
	public WeatherVariance(VARIANCE_TYPES varianceType)
	{
		// check if the argument if not null
		Objects.requireNonNull(varianceType, "varianceType passed as argument must not be null.");
		
		switch(varianceType)
		{
			case HIGH : 
						tempVariance = 5;
						windSpeedVariance = 1;
						humidityVariance = 5;
						break;
			
			case MEDIUM : 
						tempVariance = 3;
						windSpeedVariance = 0.5;
						humidityVariance = 3;
						break;
				
			case LOW : 
						tempVariance = 1.5;
						windSpeedVariance = 0.25;
						humidityVariance = 1.5;
						break; 
		}
	}

	/**
	 * returns the Temperature variance
	 */
	public double getTempVariance()
	{
		return tempVariance;
	}

	/**
	 * returns the WindSpeed variance
	 */
	public double getWindSpeedVariance()
	{
		return windSpeedVariance;
	}

	/**
	 * returns the Humidity variance
	 */
	public double getHumidityVariance()
	{
		return humidityVariance;
	}
}
