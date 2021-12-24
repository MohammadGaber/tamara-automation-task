package tests;

import org.testng.annotations.Test;
import utilities.TamaraSearchPage;

import static org.testng.Assert.*;

public class SearchTestScenarios extends TESTBASE {

    @Test(priority = 1)
    public void validateIFSearchFieldDisplayed() {
        searchPageObject = new TamaraSearchPage(driver);
        assertTrue(searchPageObject.isSearchFieldDisplayed());

    }

    @Test(priority = 2, dependsOnMethods = {"validateIFSearchFieldDisplayed"}
            ,dataProvider = "SearchDataProvider",dataProviderClass = SearchDataProviderClass.class)
    public void validateThatSearchHasResult(String searchKey, boolean flag) {
        assertTrue(searchPageObject.enterSearchKeywordAndWaitResult(searchKey));

        assertTrue(searchPageObject.validateTheNewKeySearch().size() > 0);

        System.out.println(searchPageObject.validateTheNewKeySearch().size());

        assertEquals(searchPageObject.checkIfTheResultContainsTheRequiredStore(searchKey),flag);

        assertTrue(searchPageObject.clearSearchInputFieldAndWaitResult());

    }

    @Test(priority = 4, dependsOnMethods = {"validateIFSearchFieldDisplayed"})
    public void validateSearchResultUsingNonExistingValue() {

        assertTrue(searchPageObject.clearSearchInputFieldAndWaitResult());

        assertTrue(searchPageObject.enterSearchKeywordAndWaitResult("Bla Bla Bla"));

        assertFalse(searchPageObject.validateTheNewKeySearch().size() > 0);

        assertTrue(searchPageObject.checkInvalidSearchResult("Bla Bla Bla"));

    }

}
