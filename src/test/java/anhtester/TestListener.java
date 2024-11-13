package anhtester;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogUtils;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("This is test cases success with name: "+result.getName());
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("This is fail test case with name: "+result.getName());
        ITestListener.super.onTestFailure(result);
    }


}
