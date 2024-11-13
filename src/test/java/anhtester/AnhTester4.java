package anhtester;

import org.testng.annotations.*;

public class AnhTester4 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void  afterSuite(){
        System.out.println("After suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After class");
    }

    @BeforeGroups(groups = {"testOne"})
    public void  beforeGroupOne(){
        System.out.println("Before Group testOne");
    }

    @AfterGroups(groups = {"testOne"})
    public void afterGroupOne(){
        System.out.println("After Group testOne");
    }
    @BeforeGroups(groups = {"testTwo"})
    public void  beforeGroupTwo(){
        System.out.println("Before Group testTwo");
    }

    @AfterGroups(groups = {"testTwo"})
    public void afterGroupTwo(){
        System.out.println("After Group testTwo");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before method: ");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After method: ");
    }

    @Test(groups = {"testOne"}, priority = 1)
    public void testOneMethod(){
        System.out.println("Test method 1");
    }

    @Test(groups = {"testOne"}, priority = 3)
    public void testThreeMethod(){
        System.out.println("Test method 3");
    }

    @Test(groups = {"testTwo"}, priority = 2)
    public void testTwoMethod(){
        System.out.println("Test method 2");
    }

    @Test(groups = {"testTwo"}, priority = 4)
    public void testFourMethod(){
        System.out.println("Test method 4");
    }
}
