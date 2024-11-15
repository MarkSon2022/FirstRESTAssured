package rest_assured.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest_assured.payload.request.LoginRequest;

import static io.restassured.RestAssured.given;

public class LoginEndPoints {

    public static Response Login(LoginRequest userLogin) {

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(userLogin)
                .when().post(Routes.login_URL);
    }

}
