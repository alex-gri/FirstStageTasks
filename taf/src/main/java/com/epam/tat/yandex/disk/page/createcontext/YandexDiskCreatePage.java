package com.epam.tat.yandex.disk.page.createcontext;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.util.TextSelectedCondition;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;


public class YandexDiskCreatePage extends AbstractMenuPage {

    private By createFolderOption = By.xpath("//button[contains(.,'Папку')]");
    private By createTextDocumentOption = By.xpath("//button[contains(.,'Текстовый документ')]");
    private By folderNameField = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private By saveButton = By.xpath("//button[contains(.,'Сохранить')]");
    private String folderPartialXpath = "//span[text()='%s']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_dir listing-item_selected js-prevent-deselect']";

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

    public YandexDiskCreatePage setFolderName(String folderName) {
        Log.report("Setting folder's name as: " + folderName);

        // Is default folder name "Новая папка" selected check.
        new WebDriverWait(browserInstance.getWrappedDriver(), 10)
                .until(TextSelectedCondition.selectedTextIs("Новая папка"));

        this.folderName = folderName;
        browserInstance.type(folderNameField, folderName);
        return this;
    }

    public YandexDiskCreatePage saveButtonClick() {

        // Is folder name is fully typed in check.
        browserInstance.waitForAttributeToBe(folderNameField, "value", folderName);
        browserInstance.click(saveButton);
        return this;
    }

    public YandexDiskFolderPage openCreatedFolder(Folder folder) {
        Log.report("Opening folder: " + folder.getName());
        By folderXpath = browserInstance.xpathBuilder(folderPartialXpath, folder.getName());
        browserInstance.doubleClick(folderXpath);
        browserInstance.waitForAttributeToBe(contentTitle, "title", folder.getName());
        return folder.getFolderPage();
    }
}
