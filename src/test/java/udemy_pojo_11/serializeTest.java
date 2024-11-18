package udemy_pojo_11;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import udemy_pojo_11.pojo.AddPlace;
import udemy_pojo_11.pojo.DeletePlace;
import udemy_pojo_11.pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
public class serializeTest {

    private String place_id;
    @BeforeTest
    public void setUp(){
        baseURI="https://rahulshettyacademy.com";
    }
    @Test(priority = 1)
    public void  addPlace(){
        AddPlace addedPlace = new AddPlace();
        addedPlace.setAccuracy("100");
        addedPlace.setAddress("69, side layout, cohen 69");
        addedPlace.setLanguage("Vietnam-VI");
        addedPlace.setWebsite("http://google.com");
        addedPlace.setName("Iphone sick2077");

        List<String> addedTypes=new ArrayList<String>();
        addedTypes.add("hair park");
        addedTypes.add("porn shop");
        addedPlace.setTypes(addedTypes);

        Location addedLocation= new Location(-38.693494,33.697362);
        addedPlace.setLocation(addedLocation);

        Response response=given().log().all()
                .queryParam("key"," qaclick123")
                .body(addedPlace)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        //
        place_id= response.jsonPath().getString("place_id");
        String responseString= response.asPrettyString();
        System.out.println(responseString);
    }

    @Test(priority = 4)
    public void deletePlace() {
        DeletePlace deletePlace= new DeletePlace(place_id);

        Response response = given().log().all().queryParam("key", "qaclick123")
                .body(deletePlace)
                .when().delete("maps/api/place/delete/json")
                .then().assertThat().statusCode(200)
                .extract().response();
        //
        String statusMessage = response.jsonPath().getString("status");
        System.out.println("Status: " + statusMessage);
        Assert.assertEquals(statusMessage, "OK");
    }
}
