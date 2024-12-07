package rest_assured.test.run_test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import rest_assured.api.UserApi;
import rest_assured.test.basetest.BaseTest;

@Listeners({
        org.uncommons.reportng.HTMLReporter.class,
        org.uncommons.reportng.JUnitXMLReporter.class
})
public class MainTest extends BaseTest {

    @Test(testName = "Test Full Flow From Login User - Register User - Search User - Edit User - Delete User")
    public static void testValidFlowUser() {
        UserApi.testRegisterUser();
        UserApi.testSearchUserByUsername();
        UserApi.testEditUser();
        UserApi.testEditPartOfUser();
        UserApi.testDeleteUser();
    }


}
