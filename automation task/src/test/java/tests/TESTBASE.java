package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utilities.TamaraSearchPage;

public class TESTBASE {

	protected WebDriver driver;
	protected TamaraSearchPage searchPageObject;
	protected SoftAssert softAssert;
	@BeforeClass
	@Parameters({"browser"})
	public void startUP(@Optional("chrome") String browserName)
	{

		if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		else {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.get("https://tamara.co/en/tamara-stores.html");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

}
