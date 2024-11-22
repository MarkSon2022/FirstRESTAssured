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

    @Test
    public static void ValidFlowUser() {
        UserApi.testRegisterUser();
        UserApi.testSearchUserByUsername();
        UserApi.testEditUser();
        UserApi.testEditPartOfUser();
        UserApi.testDeleteUser();
    }


}
