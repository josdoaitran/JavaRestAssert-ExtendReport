package common;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Logger;

import static common.Utilities.sDirPath;

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
                .build();
    }

}
