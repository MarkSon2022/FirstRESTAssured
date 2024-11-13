package rest_assured.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest_assured.endpoints.UserEndPoints;
import rest_assured.globals.TokenGlobal;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;

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
        Response response= UserEndPoints.registerUser(registerUser);

        //
        userId=response.getBody().path("response.id").toString();
        username= response.getBody().path("response.username").toString();

        //
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        String message= response.getBody().path("message");
        Assert.assertEquals(message,"Success","This message does not match");
    }

    @Test(priority = 2)
    public void testEditUser(){
        //
        UpdateUser user = new UpdateUser();
        user.setUsername("sonnt2077");
        user.setPassword("Demo@123");
        user.setFirstName("Son");
        user.setLastName("Nguyen 2077");
        user.setEmail("sonnt2077@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //
        Response response= UserEndPoints.updateUser(user, TokenGlobal.TOKEN);

        //
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        String message= response.jsonPath().getString("message");
        Assert.assertEquals(message,"Success","This message does not match");
    }

    @Test(priority = 3)
    public void testEditPartOfUser(){
        //
        UpdateUser user = new UpdateUser();
        user.setUsername("nghiadt2099");
        user.setPassword("Demo@123");
        user.setFirstName("Nghia");
        user.setLastName("Dao");
        user.setEmail("nghiadt2099@gmail.com");
        user.setPhone("01234456789");
        user.setUserStatus(1);

        //
        Response response= UserEndPoints.updatePartiallyUser(user, TokenGlobal.TOKEN);

        //
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        String message= response.jsonPath().getString("message");
        Assert.assertEquals(message,"Success","This message does not match");
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        //
        Response response= UserEndPoints.deleteUser(username,TokenGlobal.TOKEN);

        //
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        String message= response.jsonPath().getString("message");
        Assert.assertEquals(message,"Success","This message does not match");
    }
}
