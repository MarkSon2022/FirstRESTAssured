package rest_assured.helpers.services;

import io.restassured.response.Response;
import rest_assured.endpoints.Routes;
import rest_assured.helpers.common.SpecBuilder;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;
import rest_assured.utils.LogUtils;

import static io.restassured.RestAssured.given;

public class UserServices {

    public static Response registerUser(RegisterUserRequest userRequest) {
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(userRequest)
                .when().post(Routes.postUser_URL);
        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response getUserByUsername(String username){
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .queryParam("username",username)
                .when().get(Routes.getUserByUsername_URL);

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response updateUser(UpdateUser updateUser, String userId, String TOKEN) {
        Response response= given()
                .spec(SpecBuilder.getRequestSpecBuilder())
                .pathParam("userId",userId)
                .body(updateUser)
                .when().put(Routes.putUser_URL+"/{userId}");

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response updateUserNoAuth(UpdateUser updateUser, String userId) {
        Response response= given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .pathParam("userId",userId)
                .body(updateUser)
                .when().put(Routes.putUser_URL+"/{userId}");

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response updatePartiallyUser(UpdateUser updateUser, String userId, String TOKEN) {
        Response response= given()
                .spec(SpecBuilder.getRequestSpecBuilder())
                .pathParam("userId",userId)
                .body(updateUser)
                .when().patch(Routes.patchUser_URL+"/{userId}");

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response updatePartiallyUserNoAuth(UpdateUser updateUser, String userId) {
        Response response= given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .pathParam("userId",userId)
                .body(updateUser)
                .when().patch(Routes.patchUser_URL+"/{userId}");

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response deleteUser(String username, String TOKEN) {
        Response response= given().accept("*/*")
                .queryParam("username", username)
                .headers("Authorization", "Bearer " + TOKEN)
                .when().delete(Routes.deleteUser_URL);

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response deleteUserNoAuth(String username) {
        Response response= given().accept("*/*")
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .queryParam("username", username)
                .when().delete(Routes.deleteUser_URL);

        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

}
