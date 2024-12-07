package rest_assured.helpers.assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import rest_assured.helpers.common.SpecBuilder;
import rest_assured.payload.response.LoginResponse;
import rest_assured.payload.response.error.LoginErrorResponse;
import rest_assured.payload.response.error.UnauthorizedResponse;

public class CommonAssertion {

    public static void assertUnauthorized(Response response) {
        UnauthorizedResponse res = response
                .then().extract().response().as(UnauthorizedResponse.class);
        Assert.assertEquals(res.getMessage(), "Unauthenticated.", "The message should be Unauthenticated.");
    }

    public static void assertStatusCode(Response response, int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "The status code should match!!");
    }

    public static void assertContentType(Response response, String contentType) {
        //application/json
        Assert.assertEquals(response.getContentType(), contentType, "The content-type should match!!");
    }

    public static void assertHeader(Response response, String headerName, String headerValue) {
        //application/json
        Assert.assertNotNull(response.getHeader(headerName), "The header should exist");
        Assert.assertEquals(response.getHeader(headerName), headerValue, "The header value should match!!");
    }

    public static void assertTokenExist(Response response) {
        LoginResponse loginResponse = response.then().extract().as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.getToken(), "This token should not be null");
        Assert.assertFalse(loginResponse.getToken().isEmpty(), "This token should not be empty");
        Assert.assertFalse(SpecBuilder.TOKEN.isEmpty(), "This token should not be empty");
    }

    public static void assertLoginError(Response response, String errorMessage) {
        LoginErrorResponse loginResponse = response.then().extract().as(LoginErrorResponse.class);
        Assert.assertEquals(loginResponse.getMessage(), "Login failed", "This should pop up error message");
        Assert.assertEquals(loginResponse.getErrors(), errorMessage, "This should pop up error message");
    }

}
