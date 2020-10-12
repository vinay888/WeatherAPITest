package UtilityClasses;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.LogStatus;

@Listeners(ExtentReportListner.class)
public class TestBaseClass extends ExtentReportListner
{
	public static WebDriver driver = null;
	
	@BeforeClass
	public void setup()
	{
		// Initialize all object classes
		InitObjectClasses initObjects = new InitObjectClasses();
		
		// WebDriver setup and launch after disabling notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");  
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
		
		driver.get("https://www.ndtv.com/");
		waitForPageLoad();
		
		test.log(LogStatus.INFO, "Driver is launched.");
		//HomePage.getInstance().clickOnNoThanks();
	}
	
	@AfterClass
	public void tearDown()
	{
		test.log(LogStatus.INFO, "Driver is closed.");
		driver.close();
		driver.quit();
	}
	
	/**
	 * Method to wait for page load
	 */
	public void waitForPageLoad()
	{
		test.log(LogStatus.INFO, "Waiting for page to load");
		
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}
}
