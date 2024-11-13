package rest_assured.test;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest_assured.endpoints.LoginEndPoints;
import rest_assured.globals.TokenGlobal;
import rest_assured.payload.request.LoginRequest;

public class BaseTest {

    @BeforeTest
    public void testLoginUser(){
        //Generate data for user
        LoginRequest user= new LoginRequest("anhtester","Demo@123");

        //Get Response
        Response loginResponse= LoginEndPoints.Login(user);

        //Validate response
        loginResponse.prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);

        TokenGlobal.TOKEN= loginResponse.jsonPath().getString("token");
        System.out.println("Login success with token: "+ TokenGlobal.TOKEN);
    }

}
