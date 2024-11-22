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
                .when().post(Routes.login_URL)
                .then().spec(SpecBuilder.getResponseSpecBuilder(200))
                .extract().response();

        return response;
    }

    public static Response LoginWithJson(String fileName){
        String filePath = "src/test/resources/testdata/";
        Response response=given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(new File(filePath+fileName))
                .when().post(Routes.login_URL)
                .then().spec(SpecBuilder.getResponseSpecBuilder(200))
                .extract().response();

        return response;
    }

    public static Response LoginWithXML(String fileName, String key){
        Response response=given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(ConvertUtils.convertXmlFileToJsonString(fileName,key))
                .when().post(Routes.login_URL)
                .then().spec(SpecBuilder.getResponseSpecBuilder(200))
                .extract().response();

        return response;
    }
}
