package com.epam.tat.yandex.disk.page.context;

import com.epam.tat.framework.util.TextSelectedCondition;
import com.epam.tat.framework.util.PropertyManager;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;

import java.util.Random;

public class YandexDiskCreatePage extends AbstractMenuPage {

    private Random random = new Random();

    private By createFolderOption = By.xpath("//button[contains(.,'Папку')]");
    private By createTextDocumentOption = By.xpath("//button[contains(.,'Текстовый документ')]");
    private By folderNameField = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private By saveButton = By.xpath("//button[contains(.,'Сохранить')]");
    private By createdFolder = By.xpath("//div[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_dir listing-item_selected js-prevent-deselect']");

    private String folderName;

    public YandexDiskCreatePage createFolderOptionClick() {
        browserInstance.click(createFolderOption);
        return this;
    }

    public YandexDiskTextDocumentPage createTextDocumentOptionClick() {
        browserInstance.click(createTextDocumentOption);
        browserInstance.swtichToTab(1);
        return new YandexDiskTextDocumentPage();
    }

    public YandexDiskCreatePage setFolderName() {

        // Is default folder name "Новая папка" selected check.
        new WebDriverWait(browserInstance.getWrappedDriver(), 10)
                .until(TextSelectedCondition.isDefaultNameSelected("Новая папка"));

        folderName = String.valueOf(Math.abs(random.nextInt()));
        browserInstance.type(folderNameField, folderName);
        return this;
    }

    public YandexDiskCreatePage saveButtonClick() {

        // Is folder name is fully typed in check.
        new WebDriverWait(browserInstance.getWrappedDriver(), 10)
                .until(ExpectedConditions.attributeToBe(folderNameField, "value", folderName));
        browserInstance.click(saveButton);
        PropertyManager.writeProperty("folder.name", folderName);
        return this;
    }

    public YandexDiskFolderPage openCreatedFolder() {
        Actions action = new Actions(browserInstance.getWrappedDriver());
        action.doubleClick(browserInstance.waitForVisibilityOfElement(createdFolder)).build().perform();
        return new YandexDiskFolderPage(folderName);
    }
}
