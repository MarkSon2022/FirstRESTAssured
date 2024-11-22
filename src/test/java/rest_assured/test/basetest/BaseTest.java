package rest_assured.test.basetest;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import rest_assured.api.LoginApi;
import rest_assured.helpers.common.TokenGlobal;
import rest_assured.helpers.services.LoginServices;
import rest_assured.payload.request.LoginRequest;
import rest_assured.payload.response.LoginResponse;

public class BaseTest {
   @Test(priority = 0, testName = "Test Login User")
    public void testLoginUser() {
       LoginApi.testLoginUser();
    }

}
