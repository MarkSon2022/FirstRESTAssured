package rest_assured.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest_assured.helpers.assertions.UserAssertion;
import rest_assured.helpers.services.UserServices;
import rest_assured.payload.request.RegisterUserRequest;

public class InvalidRegisterUserAPI {

    @Test(testName = "Test Register User With Empty Username")
    public static void testRegisterUserWithEmptyUsername() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Son1999");
        registerUser.setLastName("Nguyen1999");
        registerUser.setEmail("sonng1999@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);


        //
        String message = "The username field is required.";
        UserAssertion.assertErrorMessage(response, message);
        UserAssertion.assertErrorUsername(response, message);
    }

    @Test(testName = "Test Register User With Empty Password")
    public static void testRegisterUserWithEmptyPassword() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("");
        registerUser.setFirstName("Son1999");
        registerUser.setLastName("Nguyen1999");
        registerUser.setEmail("sonng3000@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The password field format is invalid.";
        UserAssertion.assertErrorMessage(response, message);
        UserAssertion.assertErrorPassword(response, message);
    }

    @Test(testName = "Test Register User With Empty First Name")
    public static void testRegisterUserWithEmptyFirstName() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("");
        registerUser.setLastName("Nguyen1999");
        registerUser.setEmail("sonng1999@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The first name field is required.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorFirstName(response, message);
    }

    @Test(testName = "Test Register User With Empty Last Name")
    public static void testRegisterUserWithEmptyLastName() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("");
        registerUser.setEmail("sonng1999@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The last name field is required.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorLastName(response, message);
    }

    @Test(testName = "Test Register User With Empty Email")
    public static void testRegisterUserWithEmptyEmail() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The email field is required.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorEmail(response, message);
    }

    @Test(testName = "Test Register User With Empty Phone")
    public static void testRegisterUserWithEmptyPhone() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("sonHandsome@gmail.com");
        registerUser.setPhone("");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The phone field format is invalid.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorPhone(response, message);
    }

    @Test(testName = "Test Register User With Invalid User Status (status>1 and status<0)")
    public static void testRegisterUserWithInvalidUserStatus() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("sonHandsome@gmail.com");
        registerUser.setPhone("0343777444");
        registerUser.setUserStatus(-1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The selected user status is invalid.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorUserStatus(response, message);
    }

    @Test(testName = "Test Register User With Same Username")
    public static void testRegisterUserWithSameUsername() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("anhtester");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("sonHandsome@gmail.com");
        registerUser.setPhone("0343777444");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The username has already been taken.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorUsername(response, message);
    }

    @Test(testName = "Test Register User With Same Email")
    public static void testRegisterUserWithSameEmail() {
        //Initialize data for all fields of Register User
        RegisterUserRequest registerUser = new RegisterUserRequest();
        registerUser.setUsername("Anhtester1999");
        registerUser.setPassword("Demo@123");
        registerUser.setFirstName("Anh");
        registerUser.setLastName("Nguyen");
        registerUser.setEmail("thaian.it15@gmail.com");
        registerUser.setPhone("0341884321");
        registerUser.setUserStatus(1);

        //
        Response response = UserServices.registerUser(registerUser);

        //
        String message = "The email has already been taken.";
        UserAssertion.assertMessage(response, message);
        UserAssertion.assertErrorEmail(response, message);
    }
}
