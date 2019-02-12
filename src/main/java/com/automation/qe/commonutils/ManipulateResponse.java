package com.automation.qe.commonutils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

/**
 * The following class is used to Manipulate the Response returned from the request.
 * This includes utility methods which can be reused for testing
 */

public class ManipulateResponse {

    private static final Logger LOGGER = Logger.getLogger(ManipulateResponse.class);

    private ManipulateResponse() {

    }

    /**
     * This method returns a string value which is the result of the specified JSONPath
     *
     * @param response
     * @param jsonPath
     * @return
     */

    public static String extractValueByJsonPathString(Response response, String jsonPath) {
        String value = "";
        try {

            value = JsonPath.parse(response.getBody().asString()).read(jsonPath).toString();
        } catch (Exception exception) {
            LOGGER.info(exception);
        }
        return value;
    }

    /**
     * This method returns a boolean value which is the result of the specified JSONPath
     *
     * @param response
     * @param jsonPath
     * @return
     */
    public static boolean extractValueByJsonPathBoolean(Response response, String jsonPath) {
        Boolean value = null;
        try {

            value = Boolean.valueOf(JsonPath.parse(response.getBody().asString()).read(jsonPath).toString());
        } catch (Exception exception) {
            LOGGER.info(exception);
        }
        return value;
    }

    /**
     * This method validates if result of a JSONPath contains a particular string
     *
     * @param response
     * @param jsonPath
     * @param containsValue
     * @return
     */
    public static boolean jsonPathValueContainsString(Response response, String jsonPath, String containsValue) {
        Boolean value = null;
        try {

            value = JsonPath.parse(response.getBody().asString()).read(jsonPath).toString().contains(containsValue);
        } catch (Exception exception) {
            LOGGER.info(exception);
        }
        return value;
    }

    /**
     * This method is used to log the Response details of a particular request
     *
     * @param response
     */
    public static void logResponseData(Response response) {
        LOGGER.info("---------------------- BEGINING OF RESPONSE DETAILS ----------------------");
        response.getBody().prettyPeek();
        LOGGER.info("---------------------- END OF RESPONSE DETAILS ----------------------");
    }


}
