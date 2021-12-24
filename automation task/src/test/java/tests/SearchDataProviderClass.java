package tests;

import org.testng.annotations.DataProvider;

public class SearchDataProviderClass {

    @DataProvider(name = "SearchDataProvider")
    public static Object[][] SearchDataProvider() {

        Object[][] searchData = new Object[2][2];

        searchData[0][0] = "Namshi"; searchData[0][1] = true;
        searchData[1][0] = "Bla"; searchData[1][1] = true;

        return searchData;

    }


}
