package rest_assured.test.run_test;

import org.testng.annotations.Test;
import rest_assured.api.InvalidRegisterUserAPI;
import rest_assured.api.UnauthorizedUserApi;

public class FailTest {

    @Test
    public void testUnauthorizedUser(){
        UnauthorizedUserApi.testEditPartOfUser();
        UnauthorizedUserApi.testEditUser();
        UnauthorizedUserApi.testDeleteUser();
    }

    @Test
    public void testInvalidRegisterUser(){
        InvalidRegisterUserAPI.testRegisterUserWithEmptyUsername();
        InvalidRegisterUserAPI.testRegisterUserWithEmptyPassword();
        InvalidRegisterUserAPI.testRegisterUserWithEmptyFirstName();
        InvalidRegisterUserAPI.testRegisterUserWithEmptyLastName();
        InvalidRegisterUserAPI.testRegisterUserWithSameEmail();
        InvalidRegisterUserAPI.testRegisterUserWithEmptyPhone();
        InvalidRegisterUserAPI.testRegisterUserWithInvalidUserStatus();
        InvalidRegisterUserAPI.testRegisterUserWithSameUsername();
        InvalidRegisterUserAPI.testRegisterUserWithSameEmail();
    }


}
