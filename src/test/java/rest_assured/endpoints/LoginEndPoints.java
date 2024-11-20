package rest_assured.endpoints;

import io.restassured.response.Response;
import rest_assured.endpoints.keywords.Routes;
import rest_assured.endpoints.keywords.SpecBuilder;
import rest_assured.payload.request.LoginRequest;
import rest_assured.utils.LogUtils;

import static io.restassured.RestAssured.given;

public class LoginEndPoints {

    public static Response Login(LoginRequest userLogin) {
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(userLogin)
                .when().post(Routes.login_URL).then().extract().response();
        return response;
    }

}
