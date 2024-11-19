package udemy_e2e_ecom_13;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import udemy_e2e_ecom_13.pojo.LoginRequest;
import udemy_e2e_ecom_13.pojo.LoginResponse;
import udemy_e2e_ecom_13.pojo.OrderDetail;
import udemy_e2e_ecom_13.pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
public class EcommerceAPITest {
    //son@gmail.com
    //Automate@1

    String token;
    String userId;
    String productId;
    RequestSpecification req;
    RequestSpecification reqAuth;
    ResponseSpecification res;

    @BeforeTest
    void setUp(){
        req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 0)
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
        //
        userId=loginResponse.getUserId();
        //
        token=loginResponse.getToken();

    }

    //Add product
    @Test(priority = 1)
    public void testAddProduct(){
        reqAuth = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();
        //
         RequestSpecification reqAddProduct=given().log().all().spec(reqAuth)
                .param("productName","Laptop")
                .param("productAddedBy",userId)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice",20500)
                .param("productDescription","Spider no Gwen")
                .param("productFor","men")
                .multiPart("productImage",new File("src/test/resources/testdata/Croissant.png"));

        Response resAddProduct=reqAddProduct.when().post("api/ecom/product/add-product")
                .then().log().all().extract().response();

        //
        productId=resAddProduct.jsonPath().getString("productId");
        System.out.println(productId);
    }

    //Create Order
    @Test(priority = 2)
    public void testCreateOrder(){
        reqAuth = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        //
        Orders orders= new Orders();

        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setCountry("Vietnam");
        orderDetail.setProductOrderedId(productId);

        List<OrderDetail> list= new ArrayList<OrderDetail>();
        list.add(orderDetail);
        orders.setOrders(list);
        //
        RequestSpecification reqCreateOrder= given().log().all()
                .spec(reqAuth)
                .body(orders);
        Response resCreateOrder=reqCreateOrder
                .when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response();

        resCreateOrder.prettyPrint();
    }

    //View order
    @Test(priority = 3, enabled = false)
    public void testViewOrder_Product(){
        reqAuth = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        RequestSpecification reqViewOrder=given().log().all()
                .spec(reqAuth)
                .queryParam("id",productId);
        Response resViewOrder=reqViewOrder
                .when().get("/api/ecom/order/get-orders-details")
                .then().log().all().extract().response();
        //
        resViewOrder.prettyPrint();
    }
    //Delete order
    @Test(priority = 4, enabled = false)
    public void testDeleteOrder(){
        reqAuth = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        RequestSpecification reqDeleteProduct=given().log().all()
                .spec(reqAuth)
                .pathParam("orderId",productId);
        Response resDeleteProduct=reqDeleteProduct
                .when().delete("/api/ecom/order/delete-order/{orderId}")
                .then().log().all().extract().response();

        //
        resDeleteProduct.prettyPrint();
    }
    //Delete product
    @Test(priority = 5)
    public void testDeleteProduct(){
        reqAuth = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        RequestSpecification reqDeleteProduct=given().log().all()
                .spec(reqAuth)
                .pathParam("productId",productId);
        Response resDeleteProduct=reqDeleteProduct
                .when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response();

        //
        resDeleteProduct.prettyPrint();
    }
}
