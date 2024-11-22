package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.assertions.UserAssertion;
import rest_assured.helpers.common.TokenGlobal;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;
import rest_assured.test.basetest.BaseTest;

//@Listeners(TestListener.class)
public class UserApi extends BaseTest {

    private static String username;
    private static String userId;
    

    @Test(priority = 1, testName = "Test Register User")
    public static void testRegisterUser() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("anhtester2002");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Son1999");
        registerUser.setLastName("Nguyen1999");
        registerUser.setEmail("sonng1999@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response registerUserResponse = UserServices.registerUser(registerUser);

        //
        username = registerUserResponse.jsonPath().getString("response.username");

        //
        CommonAssertion.assertHeader(registerUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(registerUserResponse, 200);
        CommonAssertion.assertContentType(registerUserResponse, "application/json");
        UserAssertion.assertMessage(registerUserResponse, "Success");
        UserAssertion.assertUsername(registerUserResponse, registerUser.getUsername());
    }

    @Test(priority = 2, testName = "Test Search User By Username")
    public static void testSearchUserByUsername() {
        //
        Response response = UserServices.getUserByUsername(username);
        //
        userId = response.jsonPath().getString("response.id");

        //
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(response, 200);
        CommonAssertion.assertContentType(response, "application/json");
        UserAssertion.assertMessage(response, "Success");
        UserAssertion.assertUsername(response, username);
    }


    @Test(priority = 3, testName = "Test Edit User With POST method")
    public static void testEditUser() {
        //
        UpdateUser user = new UpdateUser();
        user.setUsername("anhtester2002");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen 2077");
        user.setEmail("sonnt2077@gmail.com");
        user.setPhone("0343653192");
        user.setUserStatus(1);

        //
        Response updateUserResponse = UserServices.updateUser(user, userId, TokenGlobal.TOKEN);

        //
        CommonAssertion.assertHeader(updateUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(updateUserResponse, 200);
        CommonAssertion.assertContentType(updateUserResponse, "application/json");
        UserAssertion.assertMessage(updateUserResponse, "Success");
        UserAssertion.assertUsername(updateUserResponse, user.getUsername());
    }

    @Test(priority = 4, testName = "Test Part Of User with PATCH method")
    public static void testEditPartOfUser() {
        //
        UpdateUser user = new UpdateUser();
        //user.setUsername("anhtester2002");
        //user.setPassword("Demo@123");
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("0343611732");
        user.setUserStatus(1);

        //
        Response updateUserResponse = UserServices.updatePartiallyUser(user, userId);

        //
        CommonAssertion.assertHeader(updateUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(updateUserResponse, 200);
        CommonAssertion.assertContentType(updateUserResponse, "application/json");
        UserAssertion.assertMessage(updateUserResponse, "Success");
        UserAssertion.assertFirstName(updateUserResponse, user.getFirstName());
    }

    @Test(priority = 5, testName = "Test Delete User")
    public static void testDeleteUser() {
        //
        Response deleteUserResponse = UserServices.deleteUser("anhtester2002");
        //
        CommonAssertion.assertHeader(deleteUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(deleteUserResponse, 200);
        CommonAssertion.assertContentType(deleteUserResponse, "application/json");
        UserAssertion.assertMessage(deleteUserResponse, "Success");
        UserAssertion.assertUsername(deleteUserResponse, username);
    }

}
