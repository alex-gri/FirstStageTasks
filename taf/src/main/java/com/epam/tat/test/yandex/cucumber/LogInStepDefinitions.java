package com.epam.tat.test.yandex.cucumber;

import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.test.Constants;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LogInStepDefinitions {

    private Account testAccount;

    @Given("user is on home page")
    public void userIsOnHomePage() {
        new YandexDiskHomePage().openYandexDiskHomePage();
    }

    @And("user has valid credentials")
    public void userHasValidCredentials() {
        testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
    }

    @When("user clicks log in")
    public void userClicksLogIn() {
        new YandexDiskHomePage().logInButtonClick();
    }

    @When("user clicks submit")
    public void userClicksSubmit() {
        new PassportYandexAuthorizationPage().logInButtonClick();
    }

    @And("user enters login")
    public void userEntersLogin() {
        new PassportYandexAuthorizationPage().setLogin(testAccount.getLogin());
    }

    @And("user enters password")
    public void userEntersPassword() {
        new PassportYandexAuthorizationPage().setPassword(testAccount.getPassword());
    }

    @And("user clicks on his profile avatar")
    public void userClicksOnHisProfileAvatar() {
        new PassportYandexAuthorizationPage().profileAvatarButtonClick();
    }

    @Then("user sees his login")
    public void userSeesHisLogin() {
        String loggedInAccountLogin = new PassportYandexAuthorizationPage().getLoggedInAccountLogin();
        assertThat("Logged in account's login doesn't match to the given test-account's login",
                loggedInAccountLogin, is(equalTo(testAccount.getLogin())));
    }
}
