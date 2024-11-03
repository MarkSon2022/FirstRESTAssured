import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import file.payload;

public class DynamicJson {
    //ID bcd227, 6464adsfs, 123abc, 456cde, 789efg
    @Test(dataProvider = "BooksData")
    public  void addBook(String name, String aisle, String isbn, String author){
        RestAssured.baseURI="http://216.10.245.166";
        RequestSpecification request= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload.addBook(name,aisle,isbn,author)).log().all();

        Response response= request.when().post("Library/Addbook.php")
                .then().statusCode(200).extract().response();

        response.prettyPrint();
        String id= response.jsonPath().get("ID");
        System.out.println("Unique ID: "+id);
    }
    //delete book
    //Create a data
    @DataProvider(name = "BooksData")
    public  Object[][]  getData(){
        // multi-dimensional array= collections of array
        // array= collection of elemets
        return new Object[][] {{"Learn Testing","abc","123","John Doe"}, {"Learn Automation Testing 2","cde","456","John Doe"}, {"Learn REST Assured","efg","789","John Doe"}};
    }

}
