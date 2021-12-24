package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class TheCatAPI_SearchBeers {
    Response currentRes=null;
    static String baseURI = "https://api.thecatapi.com";


    public Response getResponse(String queryParamName, String queryParamValue,String apiPath){
        Response tempRes = RestAssured.given().queryParam(queryParamName, queryParamValue)
                .get(apiPath);

    return tempRes;
    }


    public String returnRequiredValue(Response res, String path){

        JsonPath jsonPath = new JsonPath(res.getBody().asPrettyString());
        System.out.println(res.getBody().asPrettyString());
        return jsonPath.get(path).toString();

    }

    public void checkStatusCode(Response res){

        res.then().statusCode(200);
    }

    @Test
    public void callBaseURI() {

        RestAssured.baseURI = baseURI;

    }


    @Test
    public void getByFullName() {

       currentRes = getResponse("q","Siberian","/v1/breeds/search");
       checkStatusCode(currentRes);
       Assert.assertEquals("[Siberian]",returnRequiredValue(currentRes,"name"));

    }

    @Test
    public void checkInvalidChars() {

        currentRes = getResponse("q","*","/v1/breeds/search");
        checkStatusCode(currentRes);
        Assert.assertEquals("[]",returnRequiredValue( currentRes,"name"));

    }
}
