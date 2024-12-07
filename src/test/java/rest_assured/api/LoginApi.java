package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.common.SpecBuilder;
import rest_assured.helpers.services.LoginServices;
import rest_assured.payload.request.LoginRequest;

public class LoginApi {
    @Test
    public static void testLoginAdmin() {
        //Create a new instance of LoginRequest to login
        LoginRequest user = new LoginRequest("anhtesterAdmin", "Demo@123");

        //Get Response
        Response loginResponse = LoginServices.Login(user);

        //Set Global Token
        SpecBuilder.TOKEN = loginResponse.jsonPath().getString("token");
        System.out.println("Login success with token: " + SpecBuilder.TOKEN);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(loginResponse, 200);
        CommonAssertion.assertHeader(loginResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(loginResponse, "application/json");
        CommonAssertion.assertTokenExist(loginResponse);
    }


    public static void testLoginUser(String username, String password) {
        //Create a new instance of LoginRequest to login
        LoginRequest user = new LoginRequest(username, password);

        //Get Response
        Response loginResponse = LoginServices.Login(user);

        //Set Global Token
        SpecBuilder.TOKEN = loginResponse.jsonPath().getString("token");
        System.out.println("Login success with token: " + SpecBuilder.TOKEN);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(loginResponse, 200);
        CommonAssertion.assertHeader(loginResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(loginResponse, "application/json");
        CommonAssertion.assertTokenExist(loginResponse);
    }


    public static void testLoginWithFailPassword(String username, String password) {
        //Create a new instance of LoginRequest to login
        LoginRequest user = new LoginRequest(username, password);

        //Get Response
        Response loginResponse = LoginServices.Login(user);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(loginResponse, 400);
        CommonAssertion.assertHeader(loginResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(loginResponse, "application/json");
        CommonAssertion.assertLoginError(loginResponse, "Password is incorrect");
    }


    public static void testLoginWithFailUsername_NotExistAccount(String username, String password) {
        //Create a new instance of LoginRequest to login
        LoginRequest user = new LoginRequest(username, password);

        //Get Response
        Response loginResponse = LoginServices.Login(user);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(loginResponse, 400);
        CommonAssertion.assertHeader(loginResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(loginResponse, "application/json");
        CommonAssertion.assertLoginError(loginResponse, "User name not found");
    }
}
