package UtilityClasses;

import java.io.FileReader;
import java.util.Properties;

import com.relevantcodes.extentreports.LogStatus;


public class ReadLocators
{
	private static ReadLocators _instance = null;
	private Properties p = null;
	
	public static ReadLocators getInstance()
	{		
		if(_instance == null)
		{
			_instance = new ReadLocators();
		}
		return _instance;
		
	}
	
	/**
	 * Method to read object locators
	 */
	private ReadLocators()
	{
		FileReader reader;
		try
		{
			reader = new FileReader("./src/TestCases/Resources/Locators.Properties");
			p = new Properties();  
		    p.load(reader);
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.ERROR, "Locators file not found");
		}	    
	}
	
	/**
	 * Method to return the locator based on key provided
	 * @param key - key for the locator in locator.properties
	 * @return - value for the provided key
	 */
	public String getLocator(String key)
	{
		if(p.containsKey(key))
			return p.getProperty(key);
		else
		{
			ExtentReportListner.test.log(LogStatus.ERROR, "Key not found in locators properties file : " + key);
		}
		return null;
	}
	
}
