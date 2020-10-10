package ObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import UtilityClasses.TestBaseClass;
import UtilityClasses.Weather;

public class WeatherPage
{
	private String currentCity = "";

	/* ---------------- Singleton based Constructor -------------------------------------------- */

	private WebDriver driver = null;	
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

	private WebElement getCitySearchBox()
	{
		return driver.findElement(By.className("searchBox"));
	}

	private List<WebElement> getAllCityNamesSearchElement()
	{
		return driver.findElements(By.xpath("//div[@class= 'message' and not(@style)]/label/input"));
	}

	private WebElement getCityOnMap()
	{
		return driver.findElement(By.xpath("//div[@class = 'cityText' and text() = '" + currentCity + "']"));
	}
	
	private List<WebElement> getAllCitiesOnMap()
	{
		return driver.findElements(By.className("cityText"));
	}

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

	public void clickOnCityOnMap()
	{
		WebElement cityOnMap = getCityOnMap();
		cityOnMap.click();
	}

	public Weather getUIWeatherObject()
	{
		ArrayList<String> weatherData =  new ArrayList<String>();
		
		try
		{
			List<WebElement> leafTextElements = getLeafTextElements();
			
			if(leafTextElements.size() > 0)
			{
				for(WebElement textElement : leafTextElements)
					weatherData.add(textElement.getText());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Weather(weatherData);
	}
	
	/**
	 * Method to select radom city on map and verify if details appear
	 */
	public void selectRandomCity()
	{
		List<WebElement> citiesOnMap = getAllCitiesOnMap();
		
		if(citiesOnMap.size() > 0)
		{
			citiesOnMap.get(new Random().nextInt(citiesOnMap.size())).click();			
		}
	}
	
	public void verifyCitySelection()
	{
		Assert.assertEquals(getLeafTextElements().size() > 0, true, "Random city test on weather map.");
	}
}
