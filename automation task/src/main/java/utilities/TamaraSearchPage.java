package utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TamaraSearchPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public TamaraSearchPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);

	}

	private By searchFeild = By.xpath("//input[@placeholder='Search Stores...']");
	private By featuredStoresResultParentList = By.id("all-stores");
	private By newResultParentList = By.id("search-stores");
	private By featuredStoresHeader = By.xpath("//*[@id='all-stores']/div/h3[1]");
	private By inValidSearchMessageLocation = By.xpath("//div[@id='search-stores']/span");



	// Validate the display of search field
	public boolean isSearchFieldDisplayed() {
		wait.until(ExpectedConditions.elementToBeClickable(searchFeild));
		WebElement element = driver.findElement(searchFeild);
		return element.isDisplayed();
	}

	public boolean enterSearchKeywordAndWaitResult(String searchKeyword) {

		WebElement element = driver.findElement(searchFeild);
		element.sendKeys(searchKeyword);
		WebElement messageToBeHidden = driver.findElement(featuredStoresHeader);
		System.out.println("text: ==> " + messageToBeHidden.getText());
		return wait.until(ExpectedConditions.invisibilityOfElementWithText(featuredStoresHeader, messageToBeHidden.getText()));
	}

	public boolean clearSearchInputFieldAndWaitResult(){
		driver.findElement(searchFeild).clear();

		driver.navigate().refresh();

		WebElement messageToBeShown = driver.findElement(featuredStoresHeader);
		wait.until(ExpectedConditions.elementToBeClickable(messageToBeShown));

		// wait the text ot be loaded in the element
		return wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {

				return messageToBeShown.getText().length() != 0;
			}
		});
	}


	public List<WebElement> validateTheDefualtSearch() {

		// Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(featuredStoresResultParentList));
		return Helper.getAllSearchResult(driver, featuredStoresResultParentList);

	}

	// will return all search results in case the user enters VALID search key
	public List<WebElement> validateTheNewKeySearch() {

		// Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(newResultParentList));
		return Helper.getAllSearchResult(driver, newResultParentList);

	}

	// will return all search results in case the user enters INVALID search key like @#ASF or any non-existing store name
	// the method is locating the error message location then return the text to be validated
	public boolean checkInvalidSearchResult(String wrongSearchMessage){
		wait.until(ExpectedConditions.presenceOfElementLocated(inValidSearchMessageLocation));
		return driver.findElement(inValidSearchMessageLocation).getText().contains(wrongSearchMessage);
      }

	public boolean checkIfTheResultContainsTheRequiredStore(String Target) {

		List<WebElement> allRes = validateTheNewKeySearch();
		// means that the user enters undefined value;
		if(allRes.size()==0) return false ;

		List<String> allValues = new ArrayList<String>();

		for (WebElement webElement : allRes) {

			System.out.println("this is : ==> " + webElement.getText().toLowerCase());
			allValues.add(webElement.getText().toLowerCase());
		}

		for (Iterator<String> iterator = allValues.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.contains(Target.toLowerCase()))
				return true;
		}
		return false;
	}
}
