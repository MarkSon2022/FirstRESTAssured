import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    private String accessToken="";

    @Test(priority = 1)
    public void AuthorizationServer() {
        RequestSpecification request= given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust");
        Response response=request.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .then().extract().response();
        //print all
        response.prettyPrint();
        //get and print access token
        accessToken= response.jsonPath().getString("access_token");

    }

    @Test(priority = 2)
    public void getCourseDetails(){
        //
        RequestSpecification request=
                given().queryParam("access_token",accessToken);
        //
        String response= request
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .asPrettyString();
        //
        System.out.println(response);
    }


}
