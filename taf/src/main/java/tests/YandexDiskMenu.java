package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.menuitems.YandexDiskFilesPage;

/**
 * Check that all main menu items works correctly and lead to correct page.
 */

public class YandexDiskMenu extends TestBase {

    @Test
    public void archiveMenuItemLeadsToValidPageTest() {
        boolean isItArchivePage = new YandexDiskFilesPage(driver)
                .archiveMenuItemClick()
                .isItArchivePage();

        Assert.assertTrue(isItArchivePage);
    }

    @Test
    public void filesMenuItemLeadsToValidPageTest() {
        boolean isItFilesPage = new YandexDiskFilesPage(driver)
                .filesMenuItemClickAfterClickOnAnotherItem()
                .isItFilesPage();

        Assert.assertTrue(isItFilesPage);
    }

    @Test
    public void historyMenuItemLeadsToValidPageTest() {
        boolean isItHistoryPage = new YandexDiskFilesPage(driver)
                .historyMenuItemClick()
                .isItHistoryPage();

        Assert.assertTrue(isItHistoryPage);
    }

    @Test
    public void photoMenuItemLeadsToValidPageTest() {
        boolean isItPhotoPage = new YandexDiskFilesPage(driver)
                .photoMenuItemClick()
                .isItPhotoPage();

        Assert.assertTrue(isItPhotoPage);
    }

    @Test
    public void recentMenuItemLeadsToValidPageTest() {
        boolean isItRecentPage = new YandexDiskFilesPage(driver)
                .recentMenuItemClickAfterClickOnAnotherItem()
                .isItRecentPage();

        Assert.assertTrue(isItRecentPage);
    }

    @Test
    public void sharedMenuItemLeadsToValidPageTest() {
        boolean isItSharedPage = new YandexDiskFilesPage(driver)
                .sharedMenuItemClick()
                .isItSharedPage();

        Assert.assertTrue(isItSharedPage);
    }

    @Test
    public void trashMenuItemLeadsToValidPageTest() {
        boolean isItTrashPage = new YandexDiskFilesPage(driver)
                .trashMenuItemClick()
                .isItTrashPage();

        Assert.assertTrue(isItTrashPage);
    }
}
