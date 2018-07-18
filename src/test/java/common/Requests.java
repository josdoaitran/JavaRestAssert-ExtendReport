package common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.jayway.restassured.RestAssured.given;

public class Requests extends BaseTest{
    private static Logger logger = Logger.getLogger(Utilities.class.getSimpleName());

    /**
     * Method to make a request to API.
     * Return to raw response.
     * @param apiUrl
     * @return
     */
    public static Response getRequest(String apiUrl){
        logger.info("Get request to: "+ apiUrl);
        Response response = given().spec(spec).get(apiUrl);
        String responseBody = response.getBody().asString();
        return response;
    }

    /**
     * Validate Header response: Content-Type
     * @param response
     * @param expectResult
     */
    public static void validateContentTypeRespHeader(Response response, String expectResult){
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,expectResult);
    }

    /**
     * Validate Header response: Server
     * @param response
     * @param expectResult
     */
    public static void validateSeverRespHeader(Response response, String expectResult){
        String contentType = response.header("Server");
        Assert.assertEquals(contentType,expectResult);
    }

    /**
     * Validate Header response: Content-Encoding
     * @param response
     * @param expectResult
     */
    public static void validateContentEndcodingRespHeader(Response response, String expectResult){
        String contentType = response.header("Content-Encoding");
        Assert.assertEquals(contentType,expectResult);
    }

    /**
     * Validate response code
     * @param response
     * @param expectResult
     */
    public static void validateCodeResponse(Response response, int expectResult){
        int actualCode = response.getStatusCode();
        Assert.assertEquals(actualCode, expectResult);
        logger.info("Validated response code: "+ actualCode);

    }

    /**
     * Validate response value with a param
     * @param response
     * @param param
     * @param expectValue
     */
    public static void validateValueInParams(Response response, String param, String expectValue){
        JsonPath jsonPathEvaluator = response.jsonPath();
        String actualValue = jsonPathEvaluator.get(param);
        Assert.assertEquals(actualValue, expectValue);
    }

    public Response postRequest(String apiUrl, String jsonObjectStr) throws IOException {
        logger.info("Post request to: "+ apiUrl);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map = mapper.readValue(jsonObjectStr, new TypeReference<Map<String, String>>(){});
        Response response = given().spec(spec)
                .contentType("application/json").body(map).post(apiUrl);
        String responseBody = response.getBody().asString();
        return response;
    }
}
