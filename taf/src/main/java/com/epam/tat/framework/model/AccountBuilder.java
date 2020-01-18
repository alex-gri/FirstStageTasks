package com.epam.tat.framework.model;

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

    public Account build() {
        return account;
    }
}
