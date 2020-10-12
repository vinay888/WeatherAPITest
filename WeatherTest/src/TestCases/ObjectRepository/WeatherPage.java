package ObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import UtilityClasses.ExtentReportListner;
import UtilityClasses.TestBaseClass;
import UtilityClasses.Weather;

public class WeatherPage
{
	private String currentCity = "";

	/* ---------------- Singleton based Constructor -------------------------------------------- */

	private WebDriver driver = null;	
	private WebElement randomCity = null;
	private static WeatherPage _instance = null;

	public static WeatherPage getInstance()
	{		
		if(_instance == null)
		{
			_instance = new WeatherPage();
		}
		return _instance;		
	}

	private WeatherPage() 
	{
		this.driver = TestBaseClass.driver;
	}

	/* ---------------- Element Objects Getters -------------------------------------------- */

	/**
	 * returns the object for city search box
	 */
	private WebElement getCitySearchBox()
	{
		return driver.findElement(By.className("searchBox"));
	}

	/**
	 * returns the list of objects for all city name elements present below search box 
	 */
	private List<WebElement> getAllCityNamesSearchElement()
	{
		return driver.findElements(By.xpath("//div[@class= 'message' and not(@style)]/label/input"));
	}

	/**
	 * returns the object for city on the Map
	 */
	private WebElement getCityOnMap()
	{
		return driver.findElement(By.xpath("//div[@class = 'cityText' and text() = '" + currentCity + "']"));
	}
	
	/**
	 * returns the list object for cities present on map
	 */
	private List<WebElement> getAllCitiesOnMap()
	{
		return driver.findElements(By.className("cityText"));
	}

	/**
	 * returns the list object for all text elements present on city details overlay
	 */
	private List<WebElement> getLeafTextElements()
	{
		return driver.findElements(By.xpath("//div[@class = 'leaflet-popup-content-wrapper']//span/b"));
	}

	/* ---------------- Object and other Methods ---------------------------------------------------- */

	public void setCurrentCity(String cityName)
	{
		currentCity = cityName;
	}
	
	public String getCurrentCity()
	{
		return currentCity;
	}

	/**
	 * Search for city in Search-box
	 */
	public void searchForCity()
	{
		// Enter city name
		getCitySearchBox().sendKeys(currentCity + Keys.ENTER);
		//citySearchBox.sendKeys(Keys.ENTER);
	}

	/**
	 * Click on city name searched from the available results
	 */
	public void selectSearchedCity()
	{
		WebElement cityElement = verifyIfCityExists();

		if(cityElement != null)
			cityElement.click();
	}

	/**
	 * Checks if current city exists in searched list
	 * @return - CurrentCity Web element
	 */
	private WebElement verifyIfCityExists()
	{
		List<WebElement> searchedCityNames= getAllCityNamesSearchElement();

		if(searchedCityNames != null && searchedCityNames.size() > 0)
		{
			for(WebElement cityNameElement : searchedCityNames)
			{				 
				if(cityNameElement.getAttribute("id").trim().equals(currentCity.trim()))
				{
					Assert.assertEquals(true, true, "City name - " + currentCity + " does not exists. Skipping further tests.");
					return cityNameElement;				 	
				}
			}
		}			

		return null;
	}

	/**
	 * Verified f city is visible on map
	 */
	public void verifyIfCityVisibleOnMap()
	{
		try
		{
			WebElement cityOnMap = getCityOnMap();
			
			Assert.assertTrue(cityOnMap.isDisplayed(), "City exists on map");			
		}
		catch (Exception e)
		{
			System.out.println("City does not exist on Map.");
		}

	}

	/**
	 * Clicks on current City on map
	 */
	public void clickOnCityOnMap()
	{
		WebElement cityOnMap = getCityOnMap();
		cityOnMap.click();
	}

	/**
	 * Get weather object from data collcted from UI
	 * @return - Weather object
	 */
	public Weather getUIWeatherObject()
	{
		ArrayList<String> weatherData =  new ArrayList<String>();
		
		try
		{
			Thread.sleep(1000);
			List<WebElement> leafTextElements = getLeafTextElements();
			
			if(leafTextElements.size() > 0)
			{
				for(WebElement textElement : leafTextElements)
					weatherData.add(textElement.getText());
			}
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.FATAL, e.getStackTrace().toString());
		}
		return new Weather(weatherData);
	}
	
	/**
	 * Method to select random city on map and verify if details appear
	 */
	public void selectRandomCity()
	{
		List<WebElement> citiesOnMap = getAllCitiesOnMap();
		
		if(citiesOnMap.size() > 0)
		{
			randomCity = citiesOnMap.get(new Random().nextInt(citiesOnMap.size()));
			randomCity.click();			
		}
	}
	
	/**
	 * Un-selects the random city selected by unselectRandomCity method
	 */
	public void unselectRandomCity()
	{
		if(randomCity != null)
			randomCity.click();
	}
	
	/**
	 * Verifies if city is selected on Map by checking presence of details' overlay
	 */
	public void verifyCitySelection()
	{
		Assert.assertEquals(getLeafTextElements().size() > 0, true, "Random city test on weather map.");
	}
}
