package com.epam.tat.yandex.disk.page.createcontext;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.util.TextSelectedCondition;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;


public class YandexDiskCreatePage extends AbstractMenuPage {

    private By createFolderOption = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']/button[1]");
    private By createTextDocumentOption = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']/button[2]");
    private By folderNameField = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private By saveButton = By.xpath("//div[@class='confirmation-dialog__footer']/button");
    private String folderPartialXpath = "//span[text()='%s']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_dir listing-item_selected js-prevent-deselect']";

    private String folderName;

    public YandexDiskCreatePage createFolderOptionClick() {
        Browser.getInstance().click(createFolderOption);
        return this;
    }

    public YandexDiskTextDocumentPage createTextDocumentOptionClick() {
        Browser.getInstance().click(createTextDocumentOption);
        Browser.getInstance().swtichToTab(1);
        return new YandexDiskTextDocumentPage();
    }

    public YandexDiskCreatePage setFolderName(String folderName) {
        Log.report("Setting folder's name as: " + folderName);

        // Is default folder name "Новая папка" selected check.
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), 10)
                .until(TextSelectedCondition.selectedTextIs("Новая папка"));

        this.folderName = folderName;
        Browser.getInstance().type(folderNameField, folderName);
        return this;
    }

    public YandexDiskCreatePage saveButtonClick() {

        // Is folder name is fully typed in check.
        Browser.getInstance().waitForAttributeToBe(folderNameField, "value", folderName);
        Browser.getInstance().click(saveButton);
        return this;
    }

    public YandexDiskFolderPage openCreatedFolder(Folder folder) {
        Log.report("Opening folder: " + folder.getName());
        By folderXpath = Browser.getInstance().xpathBuilder(folderPartialXpath, folder.getName());
        Browser.getInstance().doubleClick(folderXpath);
        waitForContentTitleToBe(folder.getName());
        return folder.getFolderPage();
    }
}
