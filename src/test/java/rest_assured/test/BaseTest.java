package rest_assured.test;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import rest_assured.endpoints.LoginEndPoints;
import rest_assured.globals.TokenGlobal;
import rest_assured.payload.request.LoginRequest;
import rest_assured.payload.response.LoginResponse;

public class BaseTest {

    @BeforeTest
    public void testLoginUser() {
        //Generate data for user
        LoginRequest user = new LoginRequest("anhtester", "Demo@123");

        //Get Response
        LoginResponse loginResponse = LoginEndPoints.Login(user).as(LoginResponse.class);

        //Validate response
        TokenGlobal.TOKEN = loginResponse.getToken();
        System.out.println("Login success with token: " + TokenGlobal.TOKEN+"\n");
    }

}
