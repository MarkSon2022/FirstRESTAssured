package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.CommonAssertion;
import rest_assured.helpers.assertions.UserAssertion;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.request.RegisterUserRequest;

public class InvalidRegisterUserAPI {

    @Test(testName = "Test Register User With Same Username")
    public static void testRegisterUserWithSameUsername() {
        //Create a new instance of RegisterUserRequest to hold the user registration details with same Username
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("anhtester");
        registerUser.setPassword("LNG_Esport");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("sonHandsome1999@gmail.com");
        registerUser.setPhone("0343777444");
        registerUser.setUserStatus(1);

        //Call registerUser method from the UserServices to send the registration request and store the response
        Response response = UserServices.registerUser(registerUser);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 422);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        String message = "The username has already been taken.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorUsername(response, message);
    }

    @Test(testName = "Test Register User With Same Email")
    public static void testRegisterUserWithSameEmail() {
        //Create a new instance of RegisterUserRequest to hold the user registration details with same Email
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("sonProVIP2099");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("thaian.it15@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //Call registerUser method from the UserServices to send the registration request and store the response
        Response response = UserServices.registerUser(registerUser);

        //Perform assertions to validate the response
        CommonAssertion.assertStatusCode(response, 422);
        CommonAssertion.assertHeader(response, "vary", "Accept-Encoding");
        CommonAssertion.assertContentType(response, "application/json");
        String message = "The email has already been taken.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorEmail(response, message);
    }
}
