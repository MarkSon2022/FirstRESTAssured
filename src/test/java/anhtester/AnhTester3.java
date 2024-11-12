package anhtester;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import globals.ConfigsGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.LoginPOJO;
import utils.PropertiesHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AnhTester3 {

    @Test
    public void testDataFaker() {
        Faker faker = new Faker(new Locale("vi"));

        String computer = faker.computer().windows();
        String name = faker.name().fullName();
        String fullAddress = faker.address().fullAddress();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String phoneNumber = faker.phoneNumber().phoneNumberInternational();
        phoneNumber = phoneNumber.replace(" ", "")
                .replace("+1", "")
                .replace("34", "03")
                .replace("-", "");
        //0343-797-434
        System.out.println(phoneNumber);
        System.out.println(computer);
        System.out.println(name);
        System.out.println(fullAddress);
        System.out.println(email);
    }

    @Test
    public void testReadFile() {
        //Goi ham loadAllFiles truoc tien de load tat ca cac file
        PropertiesHelper.loadAllFiles();

        //Goi han getValue de lay  gia tri theo key
        System.out.println("URI: " + PropertiesHelper.getValue("URI"));
        System.out.println("USERNAME: " + PropertiesHelper.getValue("USERNAME"));
        System.out.println("PASSWORD: " + PropertiesHelper.getValue("PASSWORD"));
    }

    @Test
    public void testWriteValue() {
        //Truoc tien chi dinh file can set gia tri vao
        //Dung duong dan tuong doi
        //Vd file configs.properties
        PropertiesHelper.setFile("src/test/resources/config/configs.properties");

        //Goi ham setValue de gan gia tri theo key
        PropertiesHelper.setValue("author", "Anh Tester");
    }

    @Test
    public void testLoginUser() {
        // Generate data for user
        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        //Use library Gson to change class POJO -> JSON
        Gson gson = new Gson();
        //
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
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
    public void testLoginUserJson() {
        //Read Json file path
        String filePath = "src/test/resources/testdata/Login.json";

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));

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
    public void testRegisterUserJson() {
        //Read Json file path
        String filePath = "src/test/resources/testdata/RegisterUser.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));

        //Response with post
        Response response = request.when().post("/register");
        response.prettyPrint();
        //Verify status code
        response.then().statusCode(200);
        //Verify message
        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "This message does not match.");
    }

    @Test
    public void testUpdateValueInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            //
            Gson gson = new Gson();
            //Convert Json file into Json Objects
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original Json: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty("additionalneeds", "Update new value");
            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFileEdited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson03_ArrayObject() {
        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile03.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to JsonArray
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Original JSON: " + jsonArray);

            //Cap nhat gia tri cur field "lastname" in the fist object
            if (jsonArray.size() > 0) {
                //Get the 1st object
                JsonObject firstObject = jsonArray.get(0).getAsJsonObject();
                //update
                firstObject.addProperty("lastname", "News Last Name");
            }

            System.out.println("Modified JSON: " + jsonArray);

            //Store new Json data to old file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            //Save object jsonArray not jsonObject
            outputStream.write(gson.toJson(jsonArray).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson02() {
        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader=Files.newBufferedReader(Paths.get(filePath));

            Gson gson= new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject=gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: "+ jsonObject);

            //Update value for "checkout" field
            jsonObject.getAsJsonObject("bookingdates")
                    .addProperty("checkout","2024-03-09");
            System.out.println("Modified JSON: "+jsonObject);

            //Store new Json data to old file
            File jsonFile= new File(filePath);
            OutputStream outputStream= new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void  testAddNewPropertyInJson(){
        Reader reader;
        String filePath= "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader= Files.newBufferedReader(Paths.get(filePath));

            Gson gson= new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject=gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: "+jsonObject);

            //Create an array value
            JsonArray objectArray= new JsonArray();
            objectArray.add("Support");
            objectArray.add("ERP");

            //Add array to key department
            jsonObject.add("department",objectArray);

            //Add simple key:value
            jsonObject.addProperty("key1","Value for Key 1");

            //Add key: object
            Map<String, Object> objectMap= new HashMap<>();
            objectMap.put("name","Son Tester");
            objectMap.put("age",27);
            JsonElement jsonElement= gson.toJsonTree(objectMap);
            jsonObject.add("student",jsonElement);

            System.out.println("Modified JSON: "+jsonObject);

            //Store new Json data to old file
            File jsonFile= new File(filePath);
            OutputStream outputStream= new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //close reader
            reader.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void  testRemovePropertyInJson(){
        Reader reader;
        String filePath="src/test/resources/testdata/TestJsonFile02.json";

        try {
            reader=Files.newBufferedReader(Paths.get(filePath));

            Gson gson= new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject= gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: "+jsonObject);

            jsonObject.remove("age");

            //Get position of property needed deleting
            JsonObject positionObject= jsonObject
                    .get("department").getAsJsonObject()
                    .get("position").getAsJsonObject();

            //Delete key "years" in position
            positionObject.remove("years");

            System.out.println("Modified JSON: "+jsonObject);

            //Store new Json data to new file
            File jsonFile= new File("src/test/resources/testdata/TestJsonFile02Edited.json");
            OutputStream outputStream= new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
}
