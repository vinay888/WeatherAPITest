package UtilityClasses;

import java.io.FileReader;
import java.util.Properties;


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
			System.out.println("Locators file not found");
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
			System.out.println("Key does not exist in locator file : " + key);
		}
		return null;
	}
	
}
