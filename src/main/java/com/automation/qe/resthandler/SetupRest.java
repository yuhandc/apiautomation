package com.automation.qe.resthandler;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import static com.automation.qe.commonutils.CommonConstants.STR_COLON;
import static com.automation.qe.commonutils.CommonConstants.STR_DBL_SLASH;
import static com.automation.qe.propertyhandler.SetupEnvironmentConfigs.extractEnvironmentConfigs;
import static io.restassured.RestAssured.given;

/**
 * This class contains the RestAssured setup and the TestNG annotation setup
 */

public class SetupRest {

    public static RequestSpecification requestSpecification = null;
    public SoftAssert softAssert;
    private static final Logger LOGGER = Logger.getLogger(SetupRest.class);
    public static JSONObject environmentParams = extractEnvironmentConfigs();

    /**
     * This method setups the RestAssured RequestSpecification which is used to initiate the API calls
     * The RestAssured basePath, baseURI and HTTPS validation is setup
     */
    @BeforeClass
    public static void setup() {


        requestSpecification = new RequestSpecBuilder()
                .setBasePath("/" + environmentParams.getString("TEST_API_VERSION") + "/").addHeader("Accept", "application/json")
                .setBaseUri(environmentParams.getString("TEST_PROTOCOL") + STR_COLON + STR_DBL_SLASH + environmentParams.getString("TEST_HOST"))
                .setRelaxedHTTPSValidation()
                .build();
        LOGGER.info("Request specification initialized.");
    }

    /**
     * This method is used to initialize the TestNG softAsserts
     */
    @BeforeClass
    public void initializeTestAsserts() {
        softAssert = new SoftAssert();
    }


    /**
     * This method uses the RequestSpecification and setups the initial RestAssured setup to execute API request
     *
     * @return RestAssured RequestSpecification
     */
    public RequestSpecification createAPIRequest() {
        LOGGER.info("----------- REQUEST DETAILS -----------");
        return given().spec(requestSpecification).when().log().all();
    }

    /**
     * This class is used to setup the AssertAll in TestNG after all asserts of the test
     */
    @AfterTest
    public void validateAsserts() {
        softAssert.assertAll();
    }


}
