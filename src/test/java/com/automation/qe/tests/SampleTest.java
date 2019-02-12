package com.automation.qe.tests;

import com.automation.qe.commonutils.ManipulateResponse;
import com.automation.qe.resthandler.SetupRest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * This is the test class to write test cases
 */
public class SampleTest extends SetupRest {

    /**
     * The test method to validate the following AC's,
     * Name = "Carbon credits"
     * CanRelist = true
     * The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
     */

    @Test(description = "Validate AC's for the get request", priority = 0)
    public void validateAcceptanceCriteria() {

        Response response = createAPIRequest().param("catalogue", "false").get("Categories/6327/Details.json");
        ManipulateResponse.logResponseData(response);
        softAssert.assertEquals(response.getStatusCode(), 200, "Invalid Status Code");
        softAssert.assertEquals(ManipulateResponse.extractValueByJsonPathString(response, "$.Name"), "Carbon credits", "Name does not equal Carbon credits");
        softAssert.assertEquals(ManipulateResponse.extractValueByJsonPathBoolean(response, "$.CanRelist"), true, "CanRelist value is not true");
        softAssert.assertEquals(ManipulateResponse.jsonPathValueContainsString(response, "@.Promotions[?(@.Name==\"Gallery\")].Description", "2x larger image"), true, "Description does not contain value 2x larger image");
    }
}
