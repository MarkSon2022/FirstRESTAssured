package rest_assured.test.run_test;

import org.testng.annotations.Test;
import rest_assured.api.InvalidRegisterUserAPI;
import rest_assured.api.LoginApi;
import rest_assured.api.UnauthorizedUserApi;

public class FailTest {

    @Test(testName = "Test when not using authenticate")
    public void testUnauthorizedUser() {
        UnauthorizedUserApi.testEditPartOfUser();
        UnauthorizedUserApi.testEditUser();
        UnauthorizedUserApi.testDeleteUser();
    }

    @Test(testName = "Test when register with the same username that exist")
    public void testRegisterUser_SameUsername() {
        InvalidRegisterUserAPI.testRegisterUserWithSameUsername();
        LoginApi.testLoginWithFailPassword("anhtester", "LNG_Esport");
    }

    @Test(testName = "Test when register with the same email that exist")
    public void testRegisterUser_SameEmail() {
        InvalidRegisterUserAPI.testRegisterUserWithSameEmail();
        LoginApi.testLoginWithFailUsername_NotExistAccount("sonProVIP2099", "Demo@123");
    }
}
