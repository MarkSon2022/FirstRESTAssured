package rest_assured.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("This is success test case: " + result.getName() + "\n\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("This is fail test case: " + result.getName() + "\n\n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.error("This is skipped test case: " + result.getName() + "\n\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        LogUtils.info("All tests Completed. Passed: " + context.getPassedTests().size()
                + ", Fail: " + context.getFailedTests().size()
                + ", Skipped: " + context.getSkippedTests().size());
    }
}
