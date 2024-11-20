package rest_assured.endpoints;

import io.restassured.response.Response;
import rest_assured.endpoints.keywords.Routes;
import rest_assured.endpoints.keywords.SpecBuilder;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;
import rest_assured.utils.LogUtils;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response registerUser(RegisterUserRequest userRequest) {
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .body(userRequest)
                .when().post(Routes.postUser_URL)
                .then().spec(SpecBuilder.getResponseSpecBuilder(200))
                .extract().response();
        LogUtils.info("Response: \n" + response.asPrettyString());
        return response;
    }

    public static Response getUserByUsername(String username){
        Response response = given()
                .spec(SpecBuilder.getRequestSpecNoAuthBuilder())
                .queryParam("username",username)
                .when().get(Routes.getUserByUsername_URL)
                .then().spec(SpecBuilder.getResponseSpecBuilder(200))
                .extract().response();

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

    public static Response updatePartiallyUser(UpdateUser updateUser, String userId, String TOKEN) {
        Response response= given()
                .spec(SpecBuilder.getRequestSpecBuilder())
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

}
