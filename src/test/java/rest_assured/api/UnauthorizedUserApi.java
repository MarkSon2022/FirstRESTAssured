package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.request.UpdateUser;

public class UnauthorizedUserApi {

    private static String username = "NguyenPhu";
    private static String userId = "128";

    @Test(testName = "Test Edit User with POST method (No Authenticated)")
    public static void testEditUser() {
        //Create a new instance of RegisterUserRequest to hold the update user details
        UpdateUser user = new UpdateUser();
        user.setUsername("anhtester1001");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen");
        user.setEmail("sonnt1001@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //Call updateUser method from the UserServices to send the update request for specified user and userId
        Response response = UserServices.updateUserNoAuth(user, userId);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 401);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        CommonAssertion.assertUnauthorized(response);
    }

    @Test(testName = "Test Edit Part Of User with PATCH method (No Authenticated)")
    public static void testEditPartOfUser() {
        //Create a new instance of RegisterUserRequest to hold the update user details
        UpdateUser user = new UpdateUser();
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        Response response = UserServices.updatePartiallyUserNoAuth(user, userId);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 401);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        CommonAssertion.assertUnauthorized(response);
    }

    @Test(testName = "Test Delete User with DELETE method (No Authenticated)")
    public static void testDeleteUser() {
        //Call deleteUser method from the UserServices to send the delete request for the user with specified username
        Response response = UserServices.deleteUserNoAuth(username);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 401);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        CommonAssertion.assertUnauthorized(response);
    }

}
