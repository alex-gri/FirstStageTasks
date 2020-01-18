package com.epam.tat.yandex;

import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.AccountBuilder;
import com.epam.tat.testbase.LoginTestBase;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test login into yandex disk using credentials (positive and negative checks).
 */

public class YandexDiskLogIn extends LoginTestBase {

    @Test
    public void validCredentialsLogInTest() {
        Account testAccount = new AccountBuilder().login(LOGIN).password(PASSWORD).build();
        new AccountService().logIn(testAccount);
        Assert.assertTrue(new PassportYandexAuthorizationPage(driver).isLogInSuccessful());
    }

    @Test
    public void invalidLoginLogInTest() {
        Account testAccount = new AccountBuilder().login(INCORRECT_VALUE).build();
        new AccountService().logInUsingOnlyLogin(testAccount);
        Assert.assertTrue(new PassportYandexAuthorizationPage(driver).isLogInFailed());
    }

    @Test
    public void invalidPasswordLogInTest() {
        Account testAccount = new AccountBuilder().login(LOGIN).password(INCORRECT_VALUE).build();
        new AccountService().logIn(testAccount);
        Assert.assertTrue(new PassportYandexAuthorizationPage(driver).isLogInFailed());
    }
}
