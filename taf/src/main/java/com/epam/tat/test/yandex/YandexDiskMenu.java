package com.epam.tat.test.yandex;

import com.epam.tat.test.testbase.TestBase;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Check that all main menu items works correctly and lead to correct page.
 */

public class YandexDiskMenu extends TestBase {

    @Test
    public void archiveMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .archiveMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Архив"));
    }

    @Test
    public void filesMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .filesMenuItemClickAfterClickOnAnotherItem()
                .getContentTitle();
        assertThat(contentTitle, is("Файлы"));
    }

    @Test
    public void historyMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .historyMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("История"));
    }

    @Test
    public void photoMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .photoMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Все фотографии"));
    }

    @Test
    public void recentMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .recentMenuItemClickAfterClickOnAnotherItem()
                .getContentTitle();
        assertThat(contentTitle, is("Последние файлы"));
    }

    @Test
    public void sharedMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .sharedMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Публичные ссылки"));
    }

    @Test
    public void trashMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .trashMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Корзина"));
    }
}
