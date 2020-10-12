package UtilityClasses;

import java.io.FileReader;
import java.util.Properties;

import com.relevantcodes.extentreports.LogStatus;

public class ReadAPIConfig
{
	private static ReadAPIConfig _instance = null;
	private Properties p = null;
	
	public static ReadAPIConfig getInstance()
	{		
		if(_instance == null)
		{
			_instance = new ReadAPIConfig();
		}
		return _instance;
		
	}
	
	/**
	 * Method to read API config
	 */
	private ReadAPIConfig()
	{
		FileReader reader;
		try
		{
			reader = new FileReader("./src/TestCases/Resources/API.Properties");
			p = new Properties();  
		    p.load(reader);
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.FATAL, "API properties file not found");
		}	    
	}
	
	/**
	 * Method to return the API config based on key provided
	 * @param key - key for the locator in API.properties
	 * @return - value for the provided key
	 */
	public String getConfig(String key)
	{
		if(p.containsKey(key))
		{
			//System.out.println(p.get(key));
			return p.getProperty(key);
		}
		else
		{
			ExtentReportListner.test.log(LogStatus.ERROR, "Key not found in API properties file : " + key);
		}
		return null;
	}
}
