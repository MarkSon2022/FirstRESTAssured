import file.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String args[]) {
        //Get mock JSON
        JsonPath js = new JsonPath(payload.CoursePrice());
        //Print No of courses returned by API
        int noCourse = js.getInt("courses.size()");
        System.out.println("No of course: " + noCourse);
        //Print of purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount: " + purchaseAmount);
        //Print title of the first course:
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println("Title of the first course: " + firstCourseTitle);
        //Print All course titles and their respective Prices
        for (int i = 0; i < noCourse; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            System.out.println(courseTitle);
            String coursePrice = js.get("courses[" + i + "].price").toString();
            System.out.println(coursePrice);
        }
        //Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course: ");
        for (int i = 0; i < noCourse; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {
                String courseCopies = js.get("courses[" + i + "].copies").toString();
                System.out.println(courseCopies);
                break;
            }
        }
        //Verify if Sum of all Course prices matches with Purchase Amount

    }
}
