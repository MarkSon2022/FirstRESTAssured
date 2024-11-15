package rest_assured.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest_assured.payload.request.RegisterUserRequest;
import rest_assured.payload.request.UpdateUser;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response registerUser(RegisterUserRequest userRequest) {
        return given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when().post(Routes.postUser_URL);
    }

    public static Response updateUser(UpdateUser updateUser, String TOKEN) {
        return given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + TOKEN)
                .body(updateUser)
                .when().put(Routes.putUser_URL);
    }

    public static Response updatePartiallyUser(UpdateUser updateUser, String TOKEN) {
        return given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + TOKEN)
                .body(updateUser)
                .when().patch(Routes.patchUser_URL);
    }

    public static Response deleteUser(String username, String TOKEN) {
        return given().accept("*/*")
                .queryParam("username", username)
                .headers("Authorization", "Bearer " + TOKEN)
                .when().delete(Routes.deleteUser_URL);
    }

}
