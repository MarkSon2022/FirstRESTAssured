package rest_assured.helpers.assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import rest_assured.payload.response.UserResponse;
import rest_assured.payload.response.error.UnprocessableEntityResponse;

public class UserAssertion {

    //VALID ASSERTION
    public static void assertMessage(Response response, String message) {
        UserResponse messageResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(messageResponse.getMessage(), message, "This message should match");
    }


    public static void assertUsername(Response response, String username) {
        UserResponse registerUserResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(registerUserResponse.getResponse().getUsername(), username, "This username should match");
    }

    public static void assertFirstName(Response response, String firstName) {
        UserResponse registerUserResponse = response
                .then().extract().as(UserResponse.class);
        Assert.assertEquals(registerUserResponse.getResponse().getFirstName(), firstName, "This firstname does not match");
    }

    //ERROR ASSERTION
    public static void assertErrorMessage(Response response, String message) {
        UnprocessableEntityResponse messageResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(messageResponse.getMessage(), message, "This message should match");
    }

    public static void assertErrorUsername(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getUsername().get(0), message, "The username message should match");
    }

    public static void assertErrorFirstName(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getFirstName().get(0), message, "The first name message should match");
    }

    public static void assertErrorLastName(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getLastName().get(0), message, "The last name message should match");
    }

    public static void assertErrorPassword(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getPassword().get(0), message, "The password message should match");
    }

    public static void assertErrorEmail(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getEmail().get(0), message, "The email message should match");
    }

    public static void assertErrorPhone(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getPhone().get(0), message, "The email message should match");
    }

    public static void assertErrorUserStatus(Response response, String message) {
        UnprocessableEntityResponse errorResponse = response
                .then().extract().as(UnprocessableEntityResponse.class);
        Assert.assertEquals(errorResponse.getError().getUserStatus().get(0), message, "The email message should match");
    }

}
