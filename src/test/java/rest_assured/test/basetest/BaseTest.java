package rest_assured.test.basetest;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import rest_assured.helpers.common.TokenGlobal;
import rest_assured.helpers.services.LoginServices;
import rest_assured.payload.request.LoginRequest;
import rest_assured.payload.response.LoginResponse;

public class BaseTest {


   @BeforeTest
    public void testLoginUser() {
        //Generate data for user
        LoginRequest user = new LoginRequest("anhtester", "Demo@123");

        //Get Response
        LoginResponse loginResponse = LoginServices.Login(user).as(LoginResponse.class);
        //LoginResponse loginResponse = LoginServices.LoginWithJson("Login.json").as(LoginResponse.class);
        //LoginResponse loginResponse = LoginServices.LoginWithXML("Login.xml", "login").as(LoginResponse.class);

        //Validate response
        TokenGlobal.TOKEN = loginResponse.getToken();
        System.out.println("Login success with token: " + TokenGlobal.TOKEN+"\n");
    }

}
