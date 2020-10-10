package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.ReadLocators;
import UtilityClasses.TestBaseClass;

public class HomePage
{
	/* ---------------- Singleton based Constructor -------------------------------------------- */ 
	private WebDriver driver = null;	
	private static HomePage _instance = null;
	
	public static HomePage getInstance()
	{		
		if(_instance == null)
		{
			_instance = new HomePage();
		}
		return _instance;		
	}
	
	private HomePage() 
	{
		this.driver = TestBaseClass.driver;
	}
	
	/* ---------------- Element Declarations -------------------------------------------- */
	private WebElement getSubMenu()
	{
		return driver.findElement(By.id(ReadLocators.getInstance().getLocator("HOME_SUB_MENU")));	  
	}
	
	private WebElement getWeatherSubMenuElement()
	{
		return driver.findElement(By.linkText(ReadLocators.getInstance().getLocator("HOME_WEATHER_MENU")));	  
	} 
	
	private WebElement getNoNotificationAlert()
	{
		return driver.findElement(By.className(ReadLocators.getInstance().getLocator("HOME_NOTIFICATION_ALERT")));	  
	}
	 
	/* ---------------- Object and other Methods ---------------------------------------------------- */
	
	 /**
	  * Click on ... sub-menu element
	  */
	 public void clickSubmenuElement()
	 {
		 getSubMenu().click();
	 }
	 
	 /**
	  * Click on Weather sub-menu element
	  */
	 public void clickOnWeatherSubmenuElement()
	 {
		 getWeatherSubMenuElement().click();
	 }
	 
	 /**
	  * Click on "No Thanks" link on notification alert
	  */
	 public void clickOnNoThanks()
	 {
		 try
		{
			 getNoNotificationAlert().click();
		}
		catch (Exception e)
		{
			// do nothing as all tests will eventually fail if this is not handled
		}		 
	 }
}
