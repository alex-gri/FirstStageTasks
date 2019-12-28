package pages.yandexdisk.createdelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class YandexDiskTextDocumentPage {

    private WebDriver driver;

    public YandexDiskTextDocumentPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    public YandexDiskTextDocumentPage typeHelloWorld() {

        // Switching driver to document tab.
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Some wait here.

        // Writing in the document.
        Actions builder = new Actions(driver);
        builder.sendKeys("Hello World!").build().perform();
        return this;
    }
}
