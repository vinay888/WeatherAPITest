package UtilityClasses;

import java.util.Comparator;

import com.relevantcodes.extentreports.LogStatus;

import Resources.VARIANCE_TYPES;

public class WeatherComparator implements Comparator<Weather>
{
	private VARIANCE_TYPES varianceType;
	
	/** 
	 * Set variance type for the comparator
	 * @param : Enum-type VARIANCE_TYPES. One of HIGH, MEDIUM, LOW
	 */
	public void setVarianceType(VARIANCE_TYPES varianceType)
	{
		this.varianceType = varianceType;
	}
	
	@Override
	/**
	 * Compares the Weather objects based on variance type.
	 * Comparison happens for temperature, humidity and wind speed
	 * Variance can be set in calling method from one of - HIGH, MEDIUM, LOW
	 *  @retun : 1 if objects are within variance, 0 if equal and -1 if out of variance
	 */
	public int compare(Weather arg0, Weather arg1)
	{
		try
		{
			WeatherVariance weatherVariance = new WeatherVariance(varianceType);
			double tempDiffernce, windDifference, humidityDifference;
			
			tempDiffernce = getDifference(arg0.getTemperature(), arg1.getTemperature());
			humidityDifference = getDifference(arg0.getHumidity(), arg1.getHumidity());
			windDifference = getDifference(arg0.getwindSpeed(), arg1.getwindSpeed());
			
			if(tempDiffernce < weatherVariance.getTempVariance()
					&& humidityDifference < weatherVariance.getHumidityVariance()
					&& windDifference < weatherVariance.getWindSpeedVariance())
				return 1;
			
			else if(tempDiffernce > weatherVariance.getTempVariance()
					|| humidityDifference > weatherVariance.getHumidityVariance()
					|| windDifference > weatherVariance.getWindSpeedVariance())
				return -1;
			
			else
				return 0;
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.FATAL, e.getStackTrace().toString());
		}
		return -1;
	}
	/**
	 * Method to return the difference between 2 parameters being used in comparator  
	 * @param param1 : First parameter
	 * @param param2 : Second parameter
	 * @return : double difference
	 */
	private double getDifference(double param1, double param2)
	{
		double difference = param1 - param2;
		
		difference = (difference < 0) ? -difference : difference;
		
		return difference;
	}
	
}
