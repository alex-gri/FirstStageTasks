package com.epam.tat.framework.listener;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.debug("[TEST STARTED] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.debug("[TEST FINISHED] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Browser.getInstance().makeScreenshot();
        Log.debug("[TEST FAILED] " + result.getName());
    }
}
