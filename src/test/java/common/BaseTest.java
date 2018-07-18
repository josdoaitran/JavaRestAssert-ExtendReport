package common;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.specification.RequestSpecification;
import common.listeners.AllureListener;
import common.reports.AllureRestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.logging.Logger;

import static common.Utilities.sDirPath;
import common.reports.AllureRestAssured;
/**
 * Created by DoaiTran
 */

public class BaseTest {
    private static Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());
    public static String baseAPIUrl;
    public static RequestSpecification spec;

    @BeforeSuite
    public void beforeTest(){
        baseAPIUrl = Utilities.getConfigValue(sDirPath+"/API.properties","API_URL");
    }

    @BeforeClass
    public void initSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseAPIUrl)
                //.addFilter((Filter) new AllureRestAssured())
                .build();
    }

}
