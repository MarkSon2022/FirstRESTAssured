package udemy_specbuilder_12;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import udemy_specbuilder_12.pojo.AddPlace;
import udemy_specbuilder_12.pojo.DeletePlace;
import udemy_specbuilder_12.pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    private String place_id;
    RequestSpecification requestAll;
    ResponseSpecification responseExpectAll;

    @BeforeTest
    public void setUp() {
        //baseURI = "https://rahulshettyacademy.com";
        requestAll = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", " qaclick123")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        responseExpectAll = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 1)
    public void addPlace() {
        AddPlace addedPlace = new AddPlace();
        addedPlace.setAccuracy("100");
        addedPlace.setAddress("69, side layout, cohen 69");
        addedPlace.setLanguage("Vietnam-VI");
        addedPlace.setWebsite("http://google.com");
        addedPlace.setName("Iphone sick2077");

        List<String> addedTypes = new ArrayList<String>();
        addedTypes.add("hair park");
        addedTypes.add("porn shop");
        addedPlace.setTypes(addedTypes);

        Location addedLocation = new Location(-38.693494, 33.697362);
        addedPlace.setLocation(addedLocation);


        RequestSpecification requestSpecification = given().log().all()
                .spec(requestAll)
                .body(addedPlace);

        Response response = requestSpecification.when().post("/maps/api/place/add/json")
                .then().spec(responseExpectAll).extract().response();
        //
        place_id = response.jsonPath().getString("place_id");
        String responseString = response.asPrettyString();
        System.out.println(responseString);
    }

    @Test(priority = 4)
    public void deletePlace() {
        DeletePlace deletePlace = new DeletePlace(place_id);

        RequestSpecification request = given().log().all()
                .spec(requestAll)
                .body(deletePlace);

        Response response = request.when().delete("maps/api/place/delete/json")
                .then().spec(responseExpectAll).extract().response();
        //
        String statusMessage = response.jsonPath().getString("status");
        System.out.println("Status: " + statusMessage);
        Assert.assertEquals(statusMessage, "OK");
    }

}
