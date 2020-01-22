package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.model.Account;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;

public class AccountService {

    public static PassportYandexAuthorizationPage logIn(Account account) {
        return new YandexDiskHomePage()
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick()
                .setPassword(account.getPassword())
                .logInButtonClick();
    }

    public static PassportYandexAuthorizationPage logInUsingOnlyLogin(Account account) {
        return new YandexDiskHomePage()
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick();
    }
}
