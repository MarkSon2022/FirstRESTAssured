package rest_assured.test.basetest;


import org.testng.annotations.Test;
import rest_assured.api.LoginApi;

public class BaseTest {
    @Test(priority = 0, testName = "Test Login User")
    public void testLoginUser() {
        LoginApi.testLoginAdmin();
    }

}
