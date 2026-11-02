package com.qa.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Simple API testing (Module 11) using Java + REST Assured + TestNG.
 *
 * Public APIs used:
 *  - JSONPlaceholder : https://jsonplaceholder.typicode.com  (CRUD / REST)
 *  - httpbin         : https://httpbin.org                   (headers, auth, XML, status codes)
 */
public class ApiTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String HTTPBIN = "https://httpbin.org";

    private RequestSpecification spec;

    @BeforeClass
    public void setUp() {
        // Common request settings reused by every test (HTTPS + default header)
        spec = new RequestSpecBuilder()
                .addHeader("User-Agent", "api-testing-demo/1.0")
                .build();
    }

    // ------------------------------------------------------------
    // 1. GET - request/response, status code, headers, JSON
    // ------------------------------------------------------------
    @Test
    public void getSinglePost() {
        given().spec(spec)
        .when().get(BASE_URL + "/posts/1")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1))
            .body("userId", notNullValue())
            .body("title", notNullValue())
            .body("body", notNullValue());
    }

    @Test
    public void getListOfPosts() {
        given().spec(spec)
        .when().get(BASE_URL + "/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(100));
    }

    // ------------------------------------------------------------
    // 2. Query parameters
    // ------------------------------------------------------------
    @Test
    public void getWithQueryParameters() {
        given().spec(spec).queryParam("userId", 1)
        .when().get(BASE_URL + "/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(1)));
    }

    // ------------------------------------------------------------
    // 3. POST - create (201 Created)
    // ------------------------------------------------------------
    @Test
    public void postCreateResource() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "QA post");
        payload.put("body", "Learning API testing");
        payload.put("userId", 1);

        given().spec(spec).contentType(ContentType.JSON).body(payload)
        .when().post(BASE_URL + "/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("QA post"))
            .body("id", notNullValue());
    }

    // ------------------------------------------------------------
    // 4. PUT - full update
    // ------------------------------------------------------------
    @Test
    public void putUpdateResource() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 1);
        payload.put("title", "Updated");
        payload.put("body", "Updated body");
        payload.put("userId", 1);

        given().spec(spec).contentType(ContentType.JSON).body(payload)
        .when().put(BASE_URL + "/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated"));
    }

    // ------------------------------------------------------------
    // 5. PATCH - partial update
    // ------------------------------------------------------------
    @Test
    public void patchPartialUpdate() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Patched");

        given().spec(spec).contentType(ContentType.JSON).body(payload)
        .when().patch(BASE_URL + "/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Patched"));
    }

    // ------------------------------------------------------------
    // 6. DELETE
    // ------------------------------------------------------------
    @Test
    public void deleteResource() {
        given().spec(spec)
        .when().delete(BASE_URL + "/posts/1")
        .then()
            .statusCode(200);
    }

    // ------------------------------------------------------------
    // 7. Negative testing - invalid resource -> 404
    // ------------------------------------------------------------
    @Test
    public void getNonExistingResourceReturns404() {
        given().spec(spec)
        .when().get(BASE_URL + "/posts/99999")
        .then()
            .statusCode(404);
    }

    // ------------------------------------------------------------
    // 8. Status codes (data-driven)
    // ------------------------------------------------------------
    @DataProvider(name = "statusCodes")
    public Object[][] statusCodes() {
        return new Object[][] { {200}, {201}, {400}, {401}, {404}, {500} };
    }

    @Test(dataProvider = "statusCodes")
    public void variousStatusCodes(int code) {
        given().spec(spec)
        .when().get(HTTPBIN + "/status/" + code)
        .then()
            .statusCode(code);
    }

    // ------------------------------------------------------------
    // 9. Custom request headers
    // ------------------------------------------------------------
    @Test
    public void customRequestHeader() {
        Response response = given().spec(spec).header("X-Course", "API-Testing")
                .when().get(HTTPBIN + "/headers")
                .then().statusCode(200)
                .extract().response();

        Assert.assertEquals(response.jsonPath().getString("headers.'X-Course'"), "API-Testing");
    }

    // ------------------------------------------------------------
    // 10. Authentication - Basic Auth (positive + negative)
    // ------------------------------------------------------------
    @Test
    public void basicAuthValidCredentials() {
        given().spec(spec).auth().preemptive().basic("user", "pass")
        .when().get(HTTPBIN + "/basic-auth/user/pass")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true));
    }

    @Test
    public void basicAuthInvalidCredentials() {
        given().spec(spec).auth().preemptive().basic("user", "wrong")
        .when().get(HTTPBIN + "/basic-auth/user/pass")
        .then()
            .statusCode(401);
    }

    // ------------------------------------------------------------
    // 11. Authorization - Bearer token (positive + negative)
    // ------------------------------------------------------------
    @Test
    public void bearerTokenValid() {
        given().spec(spec).header("Authorization", "Bearer my-token")
        .when().get(HTTPBIN + "/bearer")
        .then()
            .statusCode(200)
            .body("token", equalTo("my-token"));
    }

    @Test
    public void bearerTokenMissing() {
        given().spec(spec)
        .when().get(HTTPBIN + "/bearer")
        .then()
            .statusCode(401);
    }

    // ------------------------------------------------------------
    // 12. XML response processing
    // ------------------------------------------------------------
    @Test
    public void xmlResponse() {
        Response response = given().spec(spec)
                .when().get(HTTPBIN + "/xml")
                .then().statusCode(200)
                .contentType(containsString("xml"))
                .extract().response();

        XmlPath xml = new XmlPath(response.asString());
        Assert.assertEquals(xml.getString("slideshow.@title"), "Sample Slide Show");
    }
}
