package udemy_e2e_ecom_13;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import udemy_e2e_ecom_13.pojo.LoginRequest;
import udemy_e2e_ecom_13.pojo.LoginResponse;

import static io.restassured.RestAssured.*;
public class EcommerceAPITest {
    //son@gmail.com
    //Automate@1

    RequestSpecification req;
    ResponseSpecification res;

    @BeforeTest
    void setUp(){
        req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void testLogin(){
        LoginRequest loginRequest= new LoginRequest("son@gmail.com","Automate@1");

        RequestSpecification reqLogin= given().log().all()
                .spec(req)
                .body(loginRequest);

        LoginResponse loginResponse= reqLogin.when().post("/api/ecom/auth/login")
                .then().extract().response().as(LoginResponse.class);

        //
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getUserId());
    }

}
