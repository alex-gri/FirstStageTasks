package framework.listener;

import framework.logger.Log;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.logAndReport("[TEST STARTED] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.logAndReport("[TEST FINISHED] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.logAndReport("[TEST FAILED] " + result.getName());
    }
}
