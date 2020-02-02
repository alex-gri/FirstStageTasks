package com.epam.tat.test.yandex;

import com.epam.tat.test.Constants ;
import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.test.testbase.LoginTestBase;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test login into yandex disk using credentials (positive and negative checks).
 */

public class YandexDiskLogIn extends LoginTestBase {

    @Test
    public void validCredentialsLogInTest() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
        String loggedInAccountLogin = AccountService.logIn(testAccount)
                .getLoggedInAccountLogin();
        assertThat(loggedInAccountLogin, is(equalTo(testAccount.getLogin())));
    }

    @Test
    public void invalidLoginLogInTest() {
        Account testAccount = new AccountBuilder().login(Constants.INCORRECT_VALUE).build();
        boolean isLogInFailed = AccountService.logInUsingOnlyLogin(testAccount)
                .isLogInErrorMessagePresent();
        Assert.assertTrue(isLogInFailed);
    }

    @Test
    public void invalidPasswordLogInTest() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.INCORRECT_VALUE).build();
        boolean isLogInFailed = AccountService.logIn(testAccount)
                .isLogInErrorMessagePresent();
        Assert.assertTrue(isLogInFailed);
    }
}
