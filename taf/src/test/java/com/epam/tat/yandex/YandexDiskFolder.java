package com.epam.tat.yandex;

import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Check creation of new folder inside Файлы (use unique name for each new folder), check you can visit that folder.
 */

public class YandexDiskFolder extends TestBase {

    @Test
    public void createFolderAndVisitItTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        String openedFolderName = FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder)
                .getOpenedFolderName();
        assertThat(openedFolderName, is(equalTo(defaultTestFolder.getName())));
    }
}
