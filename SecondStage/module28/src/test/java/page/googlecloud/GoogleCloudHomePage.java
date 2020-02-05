package page.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.CommonAbstractPage;

public class GoogleCloudHomePage extends CommonAbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    @FindBy (xpath = "//input[@class='devsite-search-field devsite-search-query']")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(jQueryAJAXsCompleted());
        return this;
    }

    public GoogleCloudHomePage writeSearchTerm(String searchTerm) {
        searchField.sendKeys(searchTerm);
        return this;
    }

    public GoogleCloudSearchResultsPage search() {
        searchField.submit();
        return new GoogleCloudSearchResultsPage(driver);
    }
}
