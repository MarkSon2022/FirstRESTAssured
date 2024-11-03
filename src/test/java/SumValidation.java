import file.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourse() {
        //Get mock JSON
        JsonPath js = new JsonPath(payload.CoursePrice());
        //Print No of courses returned by API
        int noCourse = js.getInt("courses.size()");
        System.out.println("No of course: " + noCourse);
        //
        int sum = 0;
        for (int i = 0; i < noCourse; i++) {
            String title = js.getString("courses[" + i + "].title");
            int price = js.get("courses[" + i + "].price");
            int copies = js.get("courses[" + i + "].copies");
            int amount = price * copies;
            sum += amount;
            System.out.println("The amount of " + title + ": " + amount);
        }
        System.out.println("Sum: " + sum);
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount, "The sum is not equal");
        System.out.println("Purchase Amount: " + purchaseAmount);
    }

}
