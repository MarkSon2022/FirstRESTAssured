package anhtester;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.LoginPOJO;
import pojo.PatchUserPOJO;
import pojo.RegisterUserPOJO;

import static io.restassured.RestAssured.given;

public class AnhTester2 {
    String TOKEN;
    String ID;

    @Test(priority = 1)
    public void testRegisterUser() {
        //Initialize data for all fields of Register User
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("anhtester2002");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Son1999");
        registerUserPOJO.setLastName("Nguyen1999");
        registerUserPOJO.setEmail("sonng1999@gmail.com");
        registerUserPOJO.setPhone("01234456789");
        registerUserPOJO.setUserStatus(1);

        //RegisterUserPOJO registerUserPOJO2= new
        // RegisterUserPOJO("anhtester119","Demo@123","Son1999","sonng19@gmail.com","Demo@123","01234456789",1);

        //Use Gson to change class POJO into JSON
        Gson gson = new Gson();

        //
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(registerUserPOJO));

        //Response with post
        Response response = request.when().post("/register");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        //Verify message
        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "This message does not match.");
        ID=response.getBody().path("response.id").toString();
    }

    @Test(priority = 2)
    public void testLoginUser() {
        // Generate data for user
        LoginPOJO loginPOJO = new LoginPOJO("anhtester", "Demo@123");
        //Use library Gson to change class POJO -> JSON
        ///Gson gson = new Gson();

        //
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(loginPOJO);

        //Response with post
        Response response = request.when().post("/login");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        //Verify token
        String token = response.getBody().path("token");
        TOKEN=token;
        System.out.println("Login successs with token: " + token);

    }

    //Example with put:
    @Test(priority = 3)
    public  void testEditUser_HasAuth(){
        RegisterUserPOJO registerUserPOJO= new RegisterUserPOJO();
        registerUserPOJO.setUsername("sonng111");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Huy");
        registerUserPOJO.setLastName("Nguyen Minh");
        registerUserPOJO.setEmail("huy@gmail.com");
        registerUserPOJO.setPhone("0123456788");
        registerUserPOJO.setUserStatus(1);

        Gson gson= new Gson();

        RequestSpecification request= given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer "+TOKEN)
                .body(gson.toJson(registerUserPOJO));

        Response response= request.when().put("/user/128");
        response.prettyPrint();

        response.then().statusCode(200);

        String message= response.getBody().path("message");
        Assert.assertEquals(message,"Success","The message does not match.");
    }

    @Test(priority = 4)
    public  void  testUpdateUser_PATCH(){
        //Preparing data for edit user
        PatchUserPOJO patchUserPOJO =
                new PatchUserPOJO("MarkSon","The champion", "markson44@gmail.com","0123456789",1);
        //
        Gson gson=new Gson();
        RequestSpecification request= given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer "+TOKEN)
                .body(gson.toJson(patchUserPOJO));
        //
        Response response=request.when().patch("/user/128");
        response.prettyPrint();

        response.then().statusCode(200);

        String message= response.getBody().path("message");
        Assert.assertEquals(message,"Success"," The message does not match.");
    }

    @Test(priority = 5)
    public void testDeleteUser_DELETE(){
        //
        String username="anhtester2002";

        RequestSpecification request= given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("*/*")
                .headers("Authorization","Bearer "+TOKEN)
                .queryParam("username", username);

        Response response= request.when().delete("/user");
        response.prettyPrint();

        response.then().statusCode(200);

        String  message= response.getBody().path("message");
        Assert.assertEquals(message,"Success", "The message does not match.");
    }
}
