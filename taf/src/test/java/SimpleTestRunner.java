import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.yandexdisk.YandexDiskHomePage;

public class SimpleTestRunner {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private final String INVALID_LOGIN = "";
    private final String INVALID_PASSWORD = "";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void validCredentialsLogInShouldSucceedTest() {
        boolean isLogInSuccessful = new YandexDiskHomePage(driver)
                .openPage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .isLogInSuccessful();
        Assert.assertTrue(isLogInSuccessful);
    }

    @Test
    public void invalidLoginLogInShouldFailTest() {
        boolean isLogInFailed = new YandexDiskHomePage(driver)
                .openPage()
                .logInButtonClick()
                .setLogin(INVALID_LOGIN)
                .logInButtonClick()
                .isLogInFailed();
        Assert.assertTrue(isLogInFailed);
    }

    @Test
    public void invalidPasswordLogInShouldFailTest() {
        boolean isLogInFailed = new YandexDiskHomePage(driver)
                .openPage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(INVALID_PASSWORD)
                .logInButtonClick()
                .isLogInFailed();
        Assert.assertTrue(isLogInFailed);
    }

    @AfterMethod
    public void browserClose() {
        //driver.quit();
    }
}
