package com.automation.qe.propertyhandler;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static com.automation.qe.propertyhandler.EnvironmentConstants.*;

/**
 * This class contains methods used for setting up environment configurations
 */
public class SetupEnvironmentConfigs {

    private static final Logger LOGGER = Logger.getLogger(SetupEnvironmentConfigs.class);

    private SetupEnvironmentConfigs() {

    }


    /**
     * This method extracts environment configs and returns a JSONObject which includes the configs in key value pairs
     *
     * @return
     */
    public static JSONObject extractEnvironmentConfigs() {
        JSONObject environmentProperties = new JSONObject();
        ENV_CONFIGS = readEnvironmentJSONFile("environments.json");
        TEST_ENV = JsonPath.parse(ENV_CONFIGS).read("$.executionEnv").toString().replaceAll("", "").replaceAll("\\[", "").replaceAll("\\]", "").replace("\"", "");
        TEST_PROTOCOL = JsonPath.parse(ENV_CONFIGS).read(JSON_PATH_ENVIRONMENT + TEST_ENV + JSON_PATH_PROTOCOL_ENDTAG).toString().replaceAll("", "").replaceAll("\\[", "").replaceAll("\\]", "").replace("\"", "");
        TEST_HOST = JsonPath.parse(ENV_CONFIGS).read(JSON_PATH_ENVIRONMENT + TEST_ENV + JSON_PATH_HOST_ENDTAG).toString().replaceAll("", "").replaceAll("\\[", "").replaceAll("\\]", "").replace("\"", "");
        TEST_API_VERSION = JsonPath.parse(ENV_CONFIGS).read(JSON_PATH_ENVIRONMENT + TEST_ENV + JSON_PATH_APIVERSION_ENDTAG).toString().replaceAll("", "").replaceAll("\\[", "").replaceAll("\\]", "").replace("\"", "");
        TEST_PORT = System.getProperty("base.port", JsonPath.parse(ENV_CONFIGS).read(JSON_PATH_ENVIRONMENT + TEST_ENV + JSON_PATH_PORT_ENDTAG).toString().replaceAll("", "").replaceAll("\\[", "").replaceAll("\\]", "").replace("\"", ""));
        environmentProperties.put("TEST_ENV", TEST_ENV);
        environmentProperties.put("TEST_PROTOCOL", TEST_PROTOCOL);
        environmentProperties.put("TEST_API_VERSION", TEST_API_VERSION);
        environmentProperties.put("TEST_HOST", TEST_HOST);
        return environmentProperties;
    }


    /**
     * This method is used to read a specified Json file and return its content as a String
     *
     * @param jsonFile
     * @return
     */
    public static String readEnvironmentJSONFile(String jsonFile) {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/config/" + jsonFile + "");
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException exception) {
            LOGGER.info(exception);
        }
        return content;
    }
}
