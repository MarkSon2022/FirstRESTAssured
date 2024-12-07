package rest_assured.helpers.assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import rest_assured.payload.assertion.UserAssertionModel;
import rest_assured.payload.response.UserResponse;
import rest_assured.payload.response.error.UnprocessableEntityResponse;

public class UserAssertion {

    public static SoftAssert softAssert = new SoftAssert();

    //VALID ASSERTION
    public static void assertMessage(Response response, String message) {
        UserResponse messageResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(messageResponse.getMessage(), message, "This message should match: ");
    }

    public static void assertUsername(Response response, String username) {
        UserResponse registerUserResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(registerUserResponse.getResponse().getUsername(), username, "This username should match: ");
    }

    public static void assertFirstName(Response response, String firstName) {
        UserResponse registerUserResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(registerUserResponse.getResponse().getFirstName(), firstName, "This firstname should match: ");
    }

    public static void assertUser(Response response, UserAssertionModel user) {

        UserResponse userResponse = response
                .then().extract().as(UserResponse.class);

        if (userResponse.getResponse().getUsername() != null && !userResponse.getResponse().getUsername().trim().isEmpty()) {
            Assert.assertEquals(userResponse.getResponse().getUsername(), user.getUsername(), "This username should match: ");
        }

        if (userResponse.getResponse().getFirstName() != null && !userResponse.getResponse().getFirstName().trim().isEmpty()) {
            Assert.assertEquals(userResponse.getResponse().getFirstName(), user.getFirstName(), "This first name should match: ");
        }

        if (userResponse.getResponse().getLastName() != null && !userResponse.getResponse().getLastName().trim().isEmpty()) {
            Assert.assertEquals(userResponse.getResponse().getLastName(), user.getLastName(), "This last name should match: ");
        }

        if (userResponse.getResponse().getPhone() != null && !userResponse.getResponse().getPhone().trim().isEmpty()) {
            Assert.assertEquals(userResponse.getResponse().getPhone(), user.getPhone(), "This phone should match: ");
        }

        if (userResponse.getResponse().getEmail() != null && !userResponse.getResponse().getEmail().trim().isEmpty()) {
            Assert.assertEquals(userResponse.getResponse().getEmail(), user.getEmail(), "This email should match: ");
        }

        if (userResponse.getResponse().getUserStatus() == 0 || userResponse.getResponse().getUserStatus() == 1) {
            Assert.assertEquals(userResponse.getResponse().getUserStatus(), user.getUserStatus(), "This user status should match: ");
        }

    }

    //ERROR ASSERTION
    public static void assertErrorUsername(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getUsername().get(0), message, "The username message should match: ");
    }

    public static void assertErrorEmail(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getEmail().get(0), message, "The email message should match: ");
    }

}
