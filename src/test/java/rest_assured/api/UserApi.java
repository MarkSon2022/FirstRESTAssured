package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.assertions.UserAssertion;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.assertion.UserAssertionModel;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;

//@Listeners(TestListener.class)
public class UserApi {

    private static String username;
    private static String userId;

    @Test(priority = 1, testName = "Test Register User")
    public static void testRegisterUser() {
        //Create a new instance of RegisterUserRequest to hold the user registration details
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("anhtester2002");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Dung");
        registerUser.setLastName("Tran Van");
        registerUser.setEmail("dung1999@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //Call registerUser method from the UserServices to send the registration request and store the response
        Response registerUserResponse = UserServices.registerUser(registerUser);

        //Extract the username from the response using JSON path and assign it to a variable
        username = registerUserResponse.jsonPath().getString("response.username");

        //Perform assertions to validate the response
        CommonAssertion.assertHeader(registerUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertStatusCode(registerUserResponse, 200);
        CommonAssertion.assertContentType(registerUserResponse, "application/json");
        UserAssertion.assertMessage(registerUserResponse, "Success");
        UserAssertion.assertUsername(registerUserResponse, registerUser.getUsername());

        //Validate full user
        UserAssertionModel userAssertionModel = new UserAssertionModel();
        userAssertionModel.setUsername(registerUser.getUsername());
        userAssertionModel.setFirstName(registerUser.getFirstName());
        userAssertionModel.setLastName(registerUser.getLastName());
        userAssertionModel.setEmail(registerUser.getEmail());
        userAssertionModel.setPhone(registerUser.getPhone());
        userAssertionModel.setUserStatus(registerUser.getUserStatus());

        UserAssertion.assertUser(registerUserResponse, userAssertionModel);
    }

    @Test(priority = 2, testName = "Test Search User By Username")
    public static void testSearchUserByUsername() {
        //Call getUserByUsername method from the UserServices to send the request
        Response response = UserServices.getUserByUsername(username);

        //Extract the userId from the response using JSON path and assign it to a variable
        userId = response.jsonPath().getString("response.id");

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 200);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        UserAssertion.assertMessage(response, "Success");
        UserAssertion.assertUsername(response, username);

        //Validate full user
        UserAssertionModel userAssertionModel = new UserAssertionModel();
        userAssertionModel.setUsername("anhtester2002");
        userAssertionModel.setFirstName("Dung");
        userAssertionModel.setLastName("Tran Van");
        userAssertionModel.setEmail("dung1999@gmail.com");
        userAssertionModel.setPhone("0341884321");
        userAssertionModel.setUserStatus(1);

        UserAssertion.assertUser(response, userAssertionModel);
    }

    @Test(priority = 3, testName = "Test Edit User With POST method")
    public static void testEditUser() {
        //Create a new instance of RegisterUserRequest to hold the update user details
        UpdateUser user = new UpdateUser();
        user.setUsername("anhtester2002");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen Tien");
        user.setEmail("sonnt2077@gmail.com");
        user.setPhone("0343653192");
        user.setUserStatus(1);

        //Call updateUser method from the UserServices to send the update request for specified user and userId
        Response updateUserResponse = UserServices.updateUser(user, userId);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(updateUserResponse, 200);
        CommonAssertion.assertHeader(updateUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(updateUserResponse, "application/json");
        UserAssertion.assertMessage(updateUserResponse, "Success");
        UserAssertion.assertUsername(updateUserResponse, user.getUsername());

        //Validate full user
        UserAssertionModel userAssertionModel = new UserAssertionModel();
        userAssertionModel.setUsername(user.getUsername());
        userAssertionModel.setLastName(user.getLastName());
        userAssertionModel.setFirstName(user.getFirstName());
        userAssertionModel.setEmail(user.getEmail());
        userAssertionModel.setPhone(user.getPhone());
        userAssertionModel.setUserStatus(user.getUserStatus());

        UserAssertion.assertUser(updateUserResponse, userAssertionModel);
    }

    @Test(priority = 4, testName = "Test Part Of User with PATCH method")
    public static void testEditPartOfUser() {
        //Create a new instance of RegisterUserRequest to hold the update user details
        UpdateUser user = new UpdateUser();
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("0343611732");
        user.setUserStatus(1);

        //Call updateUser method from the UserServices to send the update request for specified user and userId
        Response updateUserResponse = UserServices.updatePartiallyUser(user, userId);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(updateUserResponse, 200);
        CommonAssertion.assertHeader(updateUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(updateUserResponse, "application/json");
        UserAssertion.assertMessage(updateUserResponse, "Success");
        UserAssertion.assertFirstName(updateUserResponse, user.getFirstName());

        //Validate full user
        UserAssertionModel userAssertionModel = new UserAssertionModel();
        userAssertionModel.setUsername("anhtester2002");
        userAssertionModel.setFirstName(user.getFirstName());
        userAssertionModel.setLastName(user.getLastName());
        userAssertionModel.setEmail(user.getEmail());
        userAssertionModel.setPhone(user.getPhone());
        userAssertionModel.setUserStatus(user.getUserStatus());

        UserAssertion.assertUser(updateUserResponse, userAssertionModel);
    }

    @Test(priority = 5, testName = "Test Delete User")
    public static void testDeleteUser() {
        //Call deleteUser method from the UserServices to send the delete request for the user with specified username
        Response deleteUserResponse = UserServices.deleteUser("anhtester2002");

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(deleteUserResponse, 200);
        CommonAssertion.assertHeader(deleteUserResponse, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(deleteUserResponse, "application/json");
        UserAssertion.assertMessage(deleteUserResponse, "Success");
        UserAssertion.assertUsername(deleteUserResponse, username);
    }

    @BeforeClass
    public void setUpLogin() {
        LoginApi.testLoginAdmin();
    }
}
