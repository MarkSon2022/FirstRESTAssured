package file;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {

    public static JsonPath rawToJson(String response){
        JsonPath json= new JsonPath(response);
        return json;
    }
}
