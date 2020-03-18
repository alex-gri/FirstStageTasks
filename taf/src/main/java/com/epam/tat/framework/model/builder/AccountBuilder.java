package com.epam.tat.framework.model.builder;

import com.epam.tat.framework.model.Account;
import com.epam.tat.test.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class AccountBuilder {

    private Account account = new Account();

    public AccountBuilder login(String login) {
        account.setLogin(login);
        return this;
    }

    public AccountBuilder password(String password) {
        account.setPassword(password);
        return this;
    }

    public AccountBuilder invalidLogin() {
        account.setLogin(RandomStringUtils.randomAlphanumeric(
                RandomUtils.nextInt(Constants.MIN_CREDENTIALS_LENGTH, Constants.MAX_CREDENTIALS_LENGTH)));
        return this;
    }

    public AccountBuilder invalidPassword() {
        account.setPassword(RandomStringUtils.randomAlphanumeric(
                RandomUtils.nextInt(Constants.MIN_CREDENTIALS_LENGTH, Constants.MAX_CREDENTIALS_LENGTH)));
        return this;
    }

    public Account build() {
        return account;
    }
}
