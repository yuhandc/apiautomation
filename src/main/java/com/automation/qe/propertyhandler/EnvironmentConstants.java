package com.automation.qe.propertyhandler;

/**
 * This class contains the constant values used for the environment configurations
 */
public class EnvironmentConstants {

    private EnvironmentConstants() {
    }

    public static String ENV_CONFIGS = null;
    public static String TEST_ENV = null;
    public static String TEST_PROTOCOL = null;
    public static String TEST_API_VERSION = null;
    public static String TEST_HOST = null;
    public static String TEST_PORT = null;
    public static String JSON_PATH_ENVIRONMENT = "$.environmentList[?(@.environment==\"";
    public static String JSON_PATH_PROTOCOL_ENDTAG = "\")].protocol";
    public static String JSON_PATH_HOST_ENDTAG = "\")].host";
    public static String JSON_PATH_APIVERSION_ENDTAG = "\")].apiVersion";
    public static String JSON_PATH_PORT_ENDTAG = "\")].port";


}

