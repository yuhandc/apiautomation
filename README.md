# API Automation Assignment
The following project is created to demonstrate a simple API automation framework 
 
## Overview and Requirements

The Demo test case is written for the following test endpoint
   https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false
 
The main task was to capture the following acceptance criteria,

    1.)Name = "Carbon credits"
    
    2.)CanRelist = true
    
    3.)The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
    
This is covered in the sample test case written in SampleTest class.

### Tech Stack
    Java 8
    Tests written in RestAssured,JsonPath & TestNG
    Log management using log4j
    Version management using Git 
 
## Configurations and environment variables
    1.) Environment configurations can be found in the environments.json located in the path (apiautomation/src/main/resources/config)
    
    2.)Log properties can be found in log4j.properties file located in path (apiautomation/src/main/resources/log4j.properties)

 
## How to Run

1.) Clone the required branch from the repository at 

2.) Build the project using command mvn clean install

3.) Run tests through command line using command mvn clean install -Pacceptance -DskipTests=false
  

##How to right new test case

New test cases can be written in the tests package. Test class should be extended with the SetupRest class which includes the basic test setups needed. Users can use the ManipulateResponse class to come up with utility methods to support test writing.

The users can use the environments.json (Path:apiautomation/src/main/resources/config/environments.json) file to define the environment, apiVersion, protocol, host and port by adding a new environment JSON object.
The executionEnv tag can be used to define what environment to execute the test on.

All Tests should start with createAPIRequest() method which returns a Response object that could be manipulated to extract information and to create assertions to verify.

Verifications are done using JSONPath library and TestNG. JSONPath is used to locate values within the JSON Response which enables the users to easily manipulate the response.
