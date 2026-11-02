# Java + REST Assured + Selenium Project (Eclipse / Maven)

## Requirements
- JDK 11 or higher (a JDK, not just a JRE)
- Eclipse IDE for Java Developers (Maven support is built in)
- Google Chrome (for the Selenium test)
- Internet connection (tests call public sites)

## Import into Eclipse
1. File -> Import -> Maven -> Existing Maven Projects -> Next
2. Browse to the `java_api_selenium_project` folder (the one containing pom.xml) -> Finish
3. Wait for Maven to download dependencies (bottom-right progress bar).
4. If you see red errors: right-click project -> Maven -> Update Project (Alt+F5) -> OK.

## Run the tests
Option A - Maven (no plugin needed):
  Right-click project -> Run As -> Maven test

Option B - TestNG (install the plugin once: Help -> Eclipse Marketplace -> search "TestNG" -> Install):
  Right-click testng.xml -> Run As -> TestNG Suite
  or right-click ApiTests.java / LoginUiTest.java -> Run As -> TestNG Test

Option C - Terminal:
  mvn test
  mvn test -Dheadless=true     (Selenium runs without opening a browser window)

## Structure
```
java_api_selenium_project/
├── pom.xml
├── testng.xml
└── src/test/java/com/qa/
    ├── api/ApiTests.java       # REST Assured API tests
    └── ui/LoginUiTest.java     # Selenium WebDriver tests
```

## Module 11 coverage (ApiTests.java)
| Topic                             | Test(s)                                            |
|-----------------------------------|----------------------------------------------------|
| GET / POST / PUT / PATCH / DELETE | getSinglePost, postCreateResource, putUpdateResource, patchPartialUpdate, deleteResource |
| Requests and responses            | status code, content type and body assertions      |
| Headers                           | customRequestHeader                                |
| Parameters                        | getWithQueryParameters                             |
| JSON and XML                      | JSON body checks, xmlResponse                      |
| Authentication / authorization    | basicAuth*, bearerToken*                           |
| Status codes                      | variousStatusCodes (DataProvider)                  |
| Positive and negative testing     | 404, wrong password (401), missing token (401)     |

## Selenium coverage (LoginUiTest.java)
Locators (id, CSS), sendKeys/click, explicit waits, positive and negative login, headless mode.
