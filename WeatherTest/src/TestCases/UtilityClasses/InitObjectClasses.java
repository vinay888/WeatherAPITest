package UtilityClasses;

import ObjectRepository.HomePage;
import ObjectRepository.WeatherPage;

public class InitObjectClasses
{
	/**
	 * Initialize all POM object classes
	 */
	public void intiObjects()
	{
		HomePage.getInstance();
		WeatherPage.getInstance();
	}
}
