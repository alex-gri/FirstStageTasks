package com.epam.tat.framework.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TextSelectedCondition {
    public static ExpectedCondition<Boolean> isDefaultNameSelected(String defaultName) {
        return driver -> (Boolean) ((JavascriptExecutor)
                driver).executeScript("return document.getSelection().toString()").toString()
                .equals(defaultName);
    }
}
