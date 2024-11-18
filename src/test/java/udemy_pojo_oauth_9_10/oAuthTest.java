package udemy_pojo_oauth_9_10;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import udemy_pojo_oauth_9_10.pojo.Api;
import udemy_pojo_oauth_9_10.pojo.GetCourse;
import udemy_pojo_oauth_9_10.pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    private String accessToken = "";
    private String[] coursesTitle = {"Selenium Webdriver Java", "Cypress", "Protractor"};

    @Test(priority = 1)
    public void AuthorizationServer() {
        RequestSpecification request = given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust");
        Response response = request.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .then().extract().response();
        //print all
        response.prettyPrint();
        //get and print access token
        accessToken = response.jsonPath().getString("access_token");

    }

    @Test(priority = 2)
    public void getCourseDetails() {
        //
        RequestSpecification request =
                given().queryParam("access_token", accessToken);
        //Convert response to Java Class
        GetCourse gc = request
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourse.class);
        //
        System.out.println("Instructor: " + gc.getInstructor());
        System.out.println("LinkedIn: " + gc.getLinkedIn());
        System.out.println("Courses: " + gc.getCourses().getWebAutomation().get(0).getCourseTitle());

        //Get api follow name
        List<Api> apiCourse = gc.getCourses().getApi();
        System.out.println("\nThe price of api course with title 'SoapUI Webservices testing': ");
        for (int index = 0; index < apiCourse.size(); index++) {
            if (apiCourse.get(index).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println(apiCourse.get(0).getCourseTitle());
                break;
            }
        }

        //Print all course automation
        //Not know the size use dynamic list -> array list
        ArrayList<String> actualList = new ArrayList<String>();
        List<WebAutomation> webAutomationList = gc.getCourses().getWebAutomation();
        System.out.println("\nList automation courses: ");
        for (WebAutomation webAutomation : webAutomationList) {
            actualList.add(webAutomation.getCourseTitle());
        }
        List<String> expectedList = Arrays.asList(coursesTitle);
        //compare two list
        Assert.assertEquals(expectedList, actualList, "The list should be the same");
        Assert.assertTrue(actualList.equals(expectedList), "The 2 list should be the same");
    }


}
