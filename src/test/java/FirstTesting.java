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
}
