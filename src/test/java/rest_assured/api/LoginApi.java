package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.common.TokenGlobal;
import rest_assured.helpers.services.LoginServices;
import rest_assured.payload.request.LoginRequest;
import rest_assured.payload.response.LoginResponse;

public class LoginApi {
    @Test
    public static void testLoginUser() {
        //Generate data for user
        LoginRequest user = new LoginRequest("anhtester", "Demo@123");

        //Get Response
        Response loginResponse = LoginServices.Login(user);
        //LoginResponse loginResponse = LoginServices.LoginWithJson("Login.json").as(LoginResponse.class);
        //LoginResponse loginResponse = LoginServices.LoginWithXML("Login.xml", "login").as(LoginResponse.class);

        //Validate response
        TokenGlobal.TOKEN = loginResponse.jsonPath().getString("token");
        CommonAssertion.assertHeader(loginResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(loginResponse, 200);
        CommonAssertion.assertContentType(loginResponse, "application/json");
        CommonAssertion.assertTokenExist(loginResponse);
    }
}
