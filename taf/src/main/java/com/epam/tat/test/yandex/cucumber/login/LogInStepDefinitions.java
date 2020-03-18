package com.epam.tat.test.yandex.cucumber.login;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.test.Constants;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.DataTableType;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LogInStepDefinitions {

    private Account testAccount;
    private PassportYandexAuthorizationPage authorizationPage;

    @DataTableType
    public Account accountEntry(Map<String, String> entry) {
        return new Account(
                entry.get("login"),
                entry.get("password"));
    }

    @Given("user is on home page")
    public void userIsOnHomePage() {
        new YandexDiskHomePage().openYandexDiskHomePage();
    }


    @And("user has valid credentials")
    public void userHasValidCredentials(List<Account> accounts) {
        testAccount = accounts.get(0);
    }

    @When("user clicks log in")
    public void userClicksLogIn() {
        new YandexDiskHomePage().logInButtonClick();
        authorizationPage = new PassportYandexAuthorizationPage();
    }

    @When("user clicks submit")
    public void userClicksSubmit() {
        authorizationPage.logInButtonClick();
    }

    @And("user enters login")
    public void userEntersLogin() {

        // Removing cookies from authorization page and refreshing it (login can be saved from previous tests).
        Browser.getInstance().getWrappedDriver().manage().deleteAllCookies();
        Browser.getInstance().getWrappedDriver().navigate().refresh();
        Log.info("Cookies of current domain have been removed");

        authorizationPage.setLogin(testAccount.getLogin());
    }

    @And("user enters password")
    public void userEntersPassword() {
        authorizationPage.setPassword(testAccount.getPassword());
    }

    @And("user clicks on his profile avatar")
    public void userClicksOnHisProfileAvatar() {
        authorizationPage.profileAvatarButtonClick();
    }

    @Then("user sees his login")
    public void userSeesHisLogin() {
        String loggedInAccountLogin = authorizationPage.getLoggedInAccountLogin();
        assertThat("Logged in account's login doesn't match to the given test-account's login",
                loggedInAccountLogin, is(equalTo(testAccount.getLogin())));
    }

    @And("user has invalid login")
    public void userHasInvalidLogin() {
        testAccount = new AccountBuilder().invalidLogin().build();
    }

    @Then("user sees error message")
    public void userSeesErrorMessage() {
        boolean isLogInFailed = authorizationPage.isLogInErrorMessagePresent();
        Assert.assertTrue(isLogInFailed, "Log in state is not 'failed'");
    }

    /*
     * Removing cookies to destroy current yandex disk session so we can return to the homepage.
     */
    @After
    public void cleanCookies() {
        Browser.getInstance().getWrappedDriver().manage().deleteAllCookies();
        Log.info("Cookies of current domain have been removed");
    }

    @And("user has valid login and invalid password")
    public void userHasValidLoginAndInvalidPassword() {
        testAccount = new AccountBuilder().login(Constants.LOGIN).invalidPassword().build();
    }
}
