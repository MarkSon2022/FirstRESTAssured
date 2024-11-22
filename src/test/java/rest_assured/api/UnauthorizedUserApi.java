package rest_assured.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.request.UpdateUser;
import rest_assured.payload.response.error.UnauthorizedResponse;

public class UnauthorizedUserApi {

    private static String username="NguyenPhu";
    private static String userId="128";

    @Test(testName = "Test Edit User with POST method (No Authenticated)")
    public static void testEditUser() {
        //
        UpdateUser user = new UpdateUser();
        user.setUsername("anhtester1001");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen");
        user.setEmail("sonnt1001@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        Response response = UserServices.updateUserNoAuth(user, userId);

        CommonAssertion.assertUnauthorized(response);
    }

    @Test(testName = "Test Edit Part Of User with PATCH method (No Authenticated)")
    public static void testEditPartOfUser() {
        //
        UpdateUser user = new UpdateUser();
        //user.setUsername("anhtester2002");
        //user.setPassword("Demo@123");
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //
        Response response = UserServices.updatePartiallyUserNoAuth(user, userId);


        CommonAssertion.assertUnauthorized(response);
    }

    @Test(testName = "Test Delete User with DELETE method (No Authenticated)")
    public static void testDeleteUser() {
        //
        Response response = UserServices.deleteUserNoAuth(username);
        //
        CommonAssertion.assertUnauthorized(response);
    }

}
