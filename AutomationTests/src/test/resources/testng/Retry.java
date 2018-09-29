package testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    public static final int MAXRETRYCOUNT = 0;
    public static int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAXRETRYCOUNT) {
            retryCount++;
            return true;
        } 
        return false;
    }
}
