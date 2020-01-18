package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.driver.DriverSingleton;
import com.epam.tat.framework.model.Account;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;

public class AccountService {

    public PassportYandexAuthorizationPage logIn(Account account) {
        return new YandexDiskHomePage(DriverSingleton.getDriver())
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick()
                .setPassword(account.getPassword())
                .logInButtonClick();
    }

    public PassportYandexAuthorizationPage logInUsingOnlyLogin(Account account) {
        return new YandexDiskHomePage(DriverSingleton.getDriver())
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(account.getLogin())
                .logInButtonClick();
    }
}
