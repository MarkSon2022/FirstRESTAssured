package rest_assured.endpoints.keywords;

public class Routes {
    //Base Url to use
    public static String base_URL = "https://api.anhtester.com/api";
    //Login Url
    public static String login_URL = base_URL + "/login";
    //Get all the users url
    public static String getAllUser_URL = base_URL + "/users";
    //Get user by username
    public static String getUserByUsername_URL = base_URL + "/user";
    //Register user url
    public static String postUser_URL = base_URL + "/register";
    //Update all parts of user url
    public static String putUser_URL = base_URL + "/user";
    //Update parts of user url
    public static String patchUser_URL = base_URL + "/user";
    //Delete user url
    public static String deleteUser_URL = base_URL + "/user";


}
