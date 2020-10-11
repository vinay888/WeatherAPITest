package APIClasses;

import java.util.HashMap;

import Resources.HTTP_METHODS;
import UtilityClasses.ReadAPIConfig;

public class WeatherAPIBuilder implements IApiBuilder
{
	private static String currentCity;	
	
	public static void setCurrentCity(String city)
	{
		currentCity = city;
	}

	@Override
	public HTTP_METHODS getHttpMethod()
	{
		return HTTP_METHODS.GET;
	}

	@Override
	public String getEndpoint()
	{
		return ReadAPIConfig.getInstance().getConfig("BASE_URL");
	}
	
	@Override
	public String getBasePath()
	{
		return ReadAPIConfig.getInstance().getConfig("WEATHER_API_BASE_PATH");
	}

	@Override
	public HashMap<String, String> getPathParams()
	{
		// empty in this case 
		return new HashMap<String, String>();
	}

	@Override
	public HashMap<String, String> getQueryParams()
	{
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("id", currentCity);
		queryParams.put("appId", ReadAPIConfig.getInstance().getConfig("WEATHER_API_KEY"));
		queryParams.put("units", ReadAPIConfig.getInstance().getConfig("WEATHER_API_UNITS"));
		
		return queryParams;
	}

	@Override
	public HashMap<String, String> getHeaders()
	{
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("id", currentCity);
		headers.put("appId", ReadAPIConfig.getInstance().getConfig("WEATHER_API_KEY"));
		headers.put("units", ReadAPIConfig.getInstance().getConfig("WEATHER_API_UNITS"));
		
		return headers;
	}

	@Override
	public String getBodyString()
	{
		// not needed in this case
		return null;
	}
}
