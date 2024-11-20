package rest_assured.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import rest_assured.endpoints.UserEndPoints;
import rest_assured.globals.TokenGlobal;
import rest_assured.listener.TestListener;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;
import rest_assured.payload.response.DeleteUserResponse;
import rest_assured.payload.response.RegisterUserResponse;
import rest_assured.payload.response.UpdateUserResponse;

@Listeners(TestListener.class)
public class UserTest extends BaseTest {

    private String username;
    private String userId;

    @Test(priority = 1)
    public void testRegisterUser() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("anhtester2002");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Son1999");
        registerUser.setLastName("Nguyen1999");
        registerUser.setEmail("sonng1999@gmail.com");
        registerUser.setPhone("01234456789");
        registerUser.setUserStatus(1);

        //
        RegisterUserResponse registerUserResponse = UserEndPoints.registerUser(registerUser).as(RegisterUserResponse.class);

        //
        username = registerUserResponse.getResponse().getUsername();
        //
        String message = registerUserResponse.getMessage();
        Assert.assertEquals(message, "Success", "This message does not match");
        Assert.assertEquals(registerUser.getUsername(), registerUserResponse.getResponse().getUsername(), "The username should match");
    }

    @Test(priority = 2)
    public void testSearchUserByUsername() {
        //
        Response response = UserEndPoints.getUserByUsername(username);
        //
        String message = response.jsonPath().getString("message");
        userId = response.jsonPath().getString("response.id");
        ;
        Assert.assertEquals(message, "Success", "This message does not match");
        //Assert.assertEquals(registerUser.getUsername(), registerUserResponse.getResponse().getUsername(),"The username should match");
    }


    @Test(priority = 3)
    public void testEditUser() {
        //
        UpdateUser user = new UpdateUser();
        user.setUsername("anhtester2002");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen 2077");
        user.setEmail("sonnt2077@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //
        UpdateUserResponse updateUserResponse = UserEndPoints.updateUser(user, userId, TokenGlobal.TOKEN).as(UpdateUserResponse.class);

        //
        String message = updateUserResponse.getMessage();
        Assert.assertEquals(message, "Success", "This message does not match");
        Assert.assertEquals(user.getUsername(), updateUserResponse.getResponse().getUsername(), "The username should match");
    }

    @Test(priority = 4)
    public void testEditPartOfUser() {
        //
        UpdateUser user = new UpdateUser();
//        user.setUsername("anhtester2002");
        user.setPassword("Demo@123");
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //
        UpdateUserResponse updateUserResponse = UserEndPoints.updatePartiallyUser(user, userId, TokenGlobal.TOKEN).as(UpdateUserResponse.class);

        //
        String message = updateUserResponse.getMessage();
        Assert.assertEquals(message, "Success", "This message does not match");
        Assert.assertEquals(user.getFirstName(), updateUserResponse.getResponse().getFirstName(), "The username should match");
    }

    @Test(priority = 5)
    public void testDeleteUser() {
        //
        DeleteUserResponse deleteUserResponse = UserEndPoints.deleteUser("anhtester2002", TokenGlobal.TOKEN).as(DeleteUserResponse.class);
        //
        String message = deleteUserResponse.getMessage();
        Assert.assertEquals(message, "Success", "This message does not match");
        Assert.assertEquals(username, deleteUserResponse.getResponse().getUsername(), "The username should match");
    }
}
