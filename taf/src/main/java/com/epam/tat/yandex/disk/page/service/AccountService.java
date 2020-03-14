package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Account;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;

public class AccountService {

    private AccountService() {}

    public static PassportYandexAuthorizationPage logIn(Account account) {
        Log.report("[Authorization] Logging in Yandex Disk...");
        return new YandexDiskHomePage()
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick()
                .setPassword(account.getPassword())
                .logInButtonClick()
                .verifyOverlay();
    }

    public static PassportYandexAuthorizationPage logInUsingOnlyLogin(Account account) {
        Log.report("[Authorization] Logging in Yandex Disk using INCORRECT login...");
        return new YandexDiskHomePage()
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick();
    }
}
