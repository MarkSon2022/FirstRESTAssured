package anhtester;

import com.google.gson.Gson;
import globals.ConfigsGlobal;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.LoginPOJO;
import utils.PropertiesHelper;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeSuite
    public void  setupSuite(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void testLoginUser() {
        // Generate data for user
        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        //Use library Gson to change class POJO -> JSON
        Gson gson = new Gson();

        //
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        //Response with post
        Response response = request.when().post("/login");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        //Verify token
        TokenGlobal.TOKEN =response.getBody().path("token");;
        System.out.println("Login successs with token: " + TokenGlobal.TOKEN);
    }
}
