package tests;

import com.jayway.restassured.response.Response;
import common.BaseTest;
import common.Requests;
import common.jsonData.WeatherJSONData;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Logger;

import static common.BaseTest.baseAPIUrl;
import static common.jsonData.WeatherJSONData.triggerWeather;

/**
 * Example test with Rest Assert
 */


public class getWeatherData extends Requests{
    private static Logger logger = Logger.getLogger(getWeatherData.class.getSimpleName());
    @Test(description = "Test 1")
    @Description("Description Test 1")
    public void testGetWeatherDataFromALocation() {
        Response re = Requests.getRequest("/data/2.5/weather?q=London,uk?appid=e39b66bad0e2961268c73508c026551b");
        Requests.validateCodeResponse(re,200);
    }

    @Test(description = "Test 2")
    @Description("Description Test 2")
    public void testPostWeatherData() throws IOException {
        Response re = postRequest("/data/3.0/stations?appid=e39b66bad0e2961268c73508c026551b", triggerWeather);
        System.out.println(re.toString());
        Requests.validateCodeResponse(re, 201);
    }
}

