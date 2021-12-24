package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helper {

	
	public static List<WebElement>getAllSearchResult(WebDriver driver, By locator){
		WebElement element = driver.findElement(locator);
		List<WebElement> allSearchResults = element.findElements(By.tagName("a"));
		return allSearchResults;
	}
	
	
}
