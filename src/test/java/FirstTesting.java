import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class FirstTesting {
    @Test
    public void testGetRequest() {
        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Make a GET request and verify the response
        RestAssured.given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200) // Check if the status code is 200
                .body("userId", equalTo(1)) // Verify the userId is 1
                .body("id", equalTo(1)) // Verify the id is 1
                .body("title", notNullValue()); // Verify the title is present
    }

    @Test
    public void testGetRequest2() {
        // Define the base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Perform the GET request
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1");  // The endpoint for the GET request

        // Verify the response status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Correct status code returned");

        // Verify specific fields in the response body
        int userId = response.jsonPath().getInt("userId");
        int id = response.jsonPath().getInt("id");
        String title = response.jsonPath().getString("title");

        Assert.assertEquals(userId, 1, "Correct userId returned");
        Assert.assertEquals(id, 1, "Correct id returned");
        Assert.assertNotNull(title, "Title is present");

        // Print the response body (optional)
        System.out.println("Response Body is: " + response.getBody().asPrettyString());
    }

    @Test
    public void testGetRequest3() {
        // Define the base URL
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";

        // Perform the GET request
        Response response = RestAssured
                .given()
                .when()
                .get("/Books");  // The endpoint for the GET request

        // Verify the response status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Correct status code returned");

        // Print the response body
        System.out.println("Response Body is: " + response.getBody().asPrettyString());
    }

    @Test
    public void testQueryParameters(){
        RestAssured.baseURI="https://api.example.com";
        RequestSpecification request= RestAssured.given()
                .queryParam("page",2) //QUERY PARAMETERS
                .queryParam("limit",10); //QUERY PARAMETERS

        Response response= request.when().get("/users")
                .then().statusCode(200).extract().response();
    }

    @Test
    public void testPathParameters(){
        RestAssured.baseURI="https://api.example.com";
        RequestSpecification request= RestAssured.given().pathParams("userId",123);

        Response response= request.when().get("/users/{userId}")
                .then().statusCode(200).extract().response();
    }


    @Test
    public void testCookies(){
        RestAssured.baseURI="https://api.example.com";
        RequestSpecification request= RestAssured.given()
                .cookies("session_id","abc123"); //COOKIES
        Response response= request.when().get("/dashboard")
                .then().statusCode(200).extract().response();
    }

    @Test
    public void testHeader(){
        RestAssured.baseURI="https://api.example.com";
        RequestSpecification request= RestAssured.given()
                .header("Authorization","Bearer <token>")
                .contentType(ContentType.JSON);

        Response response= request.when().get("/userprofile")
                .then().statusCode(200).extract().response();
    }

    @Test
    public  void testContentType(){

    }
}
