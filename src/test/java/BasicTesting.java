import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicTesting {
    //validate if Add place API is working as expect
    // given -- all input details
    // when -- submit the api, http method
    // then -- validate the response
    // content of the file to String -> content of the file can convert into Byte -> Byte data to String
    //Global id
    String globalPlace_id;
    String currentAddress;
    String newAddress;

    //Add place -> Upadate AddPlace with new address -> Get AddPlace to validate if New add is present in
    @Test(priority = 1)
    public void testAddPlace () throws IOException {
        //payload.addPlace()
        //Directly using assert
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("Downloads"))))
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();


       //System.out.println("RESPONSE: ");
        System.out.println(response);
        //Get all response
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        globalPlace_id = placeId;
        String scope = js.getString("scope");
        //Log out to check
        System.out.println("The place ID added: " + placeId);
        System.out.println("The place scope added: " + scope);
        currentAddress = js.getString("address");
    }

    @Test(priority = 2)
    public void updatePlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //
        String updateAddress = "70 Summer walk, USA";
        Response response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.updatePlace(globalPlace_id, updateAddress))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
        //
        System.out.println("Update API: ");
        System.out.println(response.asPrettyString());
        newAddress = updateAddress;
    }

    @Test(priority = 3)
    public void getPlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //
        Response response = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", globalPlace_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .extract().response();
        //
        String actualAddress = response.jsonPath().getString("address");
        System.out.println(response.asPrettyString());
        Assert.assertNotEquals(actualAddress, currentAddress);
        Assert.assertEquals(actualAddress,
                newAddress);
        System.out.println(actualAddress);
    }

    @Test(priority = 4)
    public void deletePlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //
        Response response = given().log().all().queryParam("key", "qaclick123")
                .body(payload.deletePlace(globalPlace_id))
                .when().delete("maps/api/place/delete/json")
                .then().assertThat().statusCode(200)
                .extract().response();
        //
        String statusMessage = response.jsonPath().getString("status");
        System.out.println("Status: " + statusMessage);
        Assert.assertEquals(statusMessage, "OK");
    }
}
