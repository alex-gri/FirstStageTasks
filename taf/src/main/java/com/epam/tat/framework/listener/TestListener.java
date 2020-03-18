package com.epam.tat.framework.listener;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
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
        Browser.getInstance().makeScreenshot();
        Log.logAndReport("[TEST FAILED] " + result.getName());
    }
}
