package rest_assured.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private final int maxRetryCount = 3;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
