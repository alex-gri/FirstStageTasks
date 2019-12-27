package customconditions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TextSelectedCondition {
    public static ExpectedCondition<Boolean> isDefaultFolderNameSelected() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor)
                        driver).executeScript("return document.getSelection().toString()").toString()
                        .equals("Новая папка");
            }
        };
    }
}
