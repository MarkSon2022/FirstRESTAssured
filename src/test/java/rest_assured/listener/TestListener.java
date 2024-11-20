package rest_assured.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rest_assured.utils.LogUtils;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //System.out.println("This is success test case: " + result.getName());
        LogUtils.info("This is success test case: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //System.out.println("This is fail test case: " + result.getName());
        LogUtils.error("This is fail test case: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //System.out.println("This is skipped test case: " + result.getName());
        LogUtils.error("This is skipped test case: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
//        System.out.println("All tests Completed. Passed: " + context.getPassedTests().size()
//                + ", Fail: " + context.getFailedTests().size()
//                + ", Skipped: " + context.getSkippedTests().size());

        LogUtils.info("All tests Completed. Passed: " + context.getPassedTests().size()
                + ", Fail: " + context.getFailedTests().size()
                + ", Skipped: " + context.getSkippedTests().size());
    }
}