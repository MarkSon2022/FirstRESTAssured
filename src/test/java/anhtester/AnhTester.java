package anhtester;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.BookingBody;
import pojo.BookingDates;
import pojo.LoginPOJO;
import pojo.RegisterUserPOJO;

import static io.restassured.RestAssured.given;

public class AnhTester {

    String token;

    @Test
    public void testVerifyResponseAssertTestNG() {
        //Custom request -- given
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json").log().all();
        int id = 1;// ID of book, assign it to path url
        Response response = request.when().get("/book/" + id);  // PATH PARAMETERS
        response.prettyPrint();

        //Verify result from response with Assert in TestNG
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not correct1");
        Assert.assertEquals(response.getContentType(), "application/json", "Content-Type is wrong!");

        //Verify body message
        ResponseBody body = response.getBody();
        Assert.assertEquals(body.asString().contains("Success"), true, "Message success does not exist");
        //Verify content
        // Assert name
        String name = response.jsonPath().getString("response.name");
        System.out.println("Name book: " + name);
        Assert.assertEquals(name.contains("Phương Nam"), true, "Name is not correct");
        // Assert price
        String price = response.jsonPath().get("response.price").toString();
        Assert.assertEquals(price, "120000", "Price is not correct");
        // Assert path of image 2
        String imagePath = response.jsonPath().getString("response.image[0].path");
        System.out.println("Image path: " + imagePath);
        Assert.assertTrue(imagePath.contains("public/images/skGFMNH7Nh6yRKmDVJANE04o6Ud20kaWhu4vF9aF"), "Path Image is not correct");
    }



    @Test
    public void testLoginUser() {
        RestAssured.baseURI = "https://api.anhtester.com/api";
        RequestSpecification request = given();
        request
                .headers("Accept", "application/json") //HEADERS
                .contentType("application/json") // CONTENT-TYPE
                .body("{\n" +
                        "    \"username\":\"anhtester\",\n" +
                        "    \"password\":\"Demo@123\"\n" +
                        "}"); // REQUEST BODY

        //Response with post
        Response response = request.when().post("/login");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        token= response.jsonPath().get("token");
    }

    @Test
    public void testLoginUser2() {
        // Generate data for user
        LoginPOJO loginPOJO = new LoginPOJO("anhtester", "Demo@123");
        //Use library Gson to change class POJO -> JSON
        Gson gson = new Gson();

        //
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        //Response with post
        Response response = request.when().post("/login");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        //Verify token
        String token = response.getBody().path("token");
        System.out.println("Login successs with token: " + token);
    }

    @Test
    public void testRegisterUser() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "        \"username\":\"anhtester11\",\n" +
                        "        \"firstName\":\"Anh\",\n" +
                        "        \"lastName\":\"Tester\",\n" +
                        "        \"email\":\"anhtester11@email.com\",\n" +
                        "        \"password\":\"Demo@123\",\n" +
                        "        \"phone\":\"0123456789\",\n" +
                        "        \"userStatus\":1\n" +
                        "    }");

        //Response with post
        Response response = request.when().post("/register");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
    }



    @Test
    public void testCreateBooking() {
        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification requestSpecification = given();
        requestSpecification.baseUri(baseUri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log().all();
        // Generate 2 class Pojo
        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();
        // Set fields
        bookingBody.setFirstname("Son");
        bookingBody.setLastname("Nguyen");
        bookingBody.setTotalprice(100);
        bookingBody.setDepositpaid(true);
        bookingBody.setAdditionalneeds("Technology");
        //
        bookingDates.setCheckin("2024-12-15");
        bookingDates.setCheckout("2024-12-30");
        //
        bookingBody.setBookingDates(bookingDates);

        Gson gson = new Gson();

        //Convert POJO to JSON
        requestSpecification.body(gson.toJson(bookingBody));
        System.out.println("RESPONSE HERE: -------------------------------------");
        Response response = requestSpecification.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    //Bai 7 Authentication/Authorization-----------------------------------------------------------------

    //Basic authentication là xác thực cơ bản, yêu cầu người dùng gửi id dùng
    // Và mật khẩu đc mã hóa dưới dạng Base64
    @Test
    public void testBasicAuth() {
        //.preemptive() if dev want this kind of information
        //Request
        RequestSpecification request = given().auth().basic("postman", "password");
        //Response
        Response response = request.get("https://postman-echo.com/basic-auth");
        //Verify content
        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());
    }

    //Digest Authentication là kiểu xác thực truy cập thông báo, một trong những phương pháp đã
    // được thống nhất mà máy chủ web có thể sử dụng để thương lượng thông tin xác thực,
    // như tên người dùng hoặc mật khẩu với web của người dùng.
    //Xác nhận thông tin người dùng trước khí gửi thông tin nhạy cảm -> vd: lịch sử giao dịch ngân hàng
    //Dùng băm mật mã MD5 , giao thức HTTP
    //Giống basic như username và password đc dùng hàm băm thông tin
    @Test
    public void testDigestAuth() {
        RequestSpecification request = given().auth().digest("postman", "password");
        Response response = request.get("https://postman-echo.com/basic-auth");
        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());
    }

    // Form Authentication trong REST Assured
    // xác thực form điền thông tin được hiển thị ra từ hệ thống khi truy cập vào trước khi làm thao tác gì đó.
    @Test
    public void testFormAuth(){
        Response response= given().auth().form("admin", "admin")
                .get("https://the-internet.herokuapp.com/basic_auth");
    }

    //Oauth
    //Có 2 dạng OAuth1 và OAuth2.
    //Trong lĩnh vực bảo mật API và cho phép quyền truy cập có kiểm soát, vào tài nguyên người dùng.
    //OAuth1 và OAuth2 có vai trò nổi bật, có chung mục đích cơ bản nhưng hai giao thức khác nhau (tiếp cận và cơ ché)

    //OAuth1 : (old version) tập trung vào cấp cho các ứng dụng bên 3rd quyền truy cập hạn chế vào tài nguyên nguyên
    //người dùng trên các dịch vụ trực tuyến khác nhau.
    //->Truy cập an toàn vào tài nguyên mà không cần chia sẻ thông tin xác thực thực tế của người dùng với ứng dụng thứ ba
    //  3 phần: người dùng (owner has resources), ứng dụng app (người tiêu dùng)
    //          máy chủ tài nguyên nhà cung cấp dịch vụ
    @Test
    public  void  testOAuth1(){
        given().auth()
                .oauth("consumerKey","consumerSecret","accessToken","tokenSecret")
                .get("your end poit URL");

    }

    //OAuth2 nhấn mạnh vào xác thực dựa trên mã thông báo -> ưu tiên cả tính bảo mật và thân thiện với người dùng
    //Phối hợp 3 : người dùng, ứng dụng và máy chủ ủy quyền => thông qua mã thông báo truy cập (Access Token) tồn tại thời gian ngắn
    @Test
    public void  testOAuth2(){
        given().auth().oauth2("Access token")
                .get("your end point URL");
    }

    //Sử dụng token để xác thực trong REST Assured
    // Authentication: xác minh danh tính người dùng/ứng dụng đang cố truy cập API
    // Authorization: quá trình cấp/từ chối quyền truy cập dựa trên quyền của người dùng hoặc ứng dụng

    //Cookies
    @Test
    public void  testUseToken_Cookies(){
        RequestSpecification requestSpecification=given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .accept("application/json")
                .headers("Cookie","token=abc123")
                .body("");

        Response response=requestSpecification.put("/booking/1");
    }
    //Bearer Token
    @Test
    public  void  testUseToken_Bearer(){
        RequestSpecification request=given();
        request.baseUri("https://api.anhtester.com/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization","Bearer "+token)
                .body("");
        Response response=request.when().put("/user/1");
    }





}
