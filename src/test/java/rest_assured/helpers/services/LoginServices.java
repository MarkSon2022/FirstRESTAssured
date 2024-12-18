package rest_assured.helpers.services;

import io.restassured.response.Response;
import rest_assured.endpoints.Routes;
import rest_assured.helpers.common.SpecBuilder;
import rest_assured.payload.request.LoginRequest;
import rest_assured.utils.ConvertUtils;

import java.io.File;

import static io.restassured.RestAssured.given;

public class LoginServices {

    public static Response Login(LoginRequest userLogin) {
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(userLogin)
                //.cookie("session_id", "abc123")
                //.auth().form("admin", "admin")//Form Authentication
                .when().post(Routes.login_URL);

        return response;
    }

    public static Response LoginWithJson(String fileName) {
        String filePath = "src/test/resources/testdata/";
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(new File(filePath + fileName))
                //.cookie("session_id", "abc123")
                //.auth().basic("username", "password")//Basic Authentication
                .when().post(Routes.login_URL);

        return response;
    }

    public static Response LoginWithXML(String fileName, String key) {
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(ConvertUtils.convertXmlFileToJsonString(fileName, key))
                //.cookie("session_id", "abc123")
                .when().post(Routes.login_URL);

        return response;
    }

}
