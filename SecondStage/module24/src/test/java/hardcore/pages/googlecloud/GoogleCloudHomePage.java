package hardcore.pages.googlecloud;

import icanwin.customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;

    @FindBy (xpath = "//input[@class='devsite-search-field devsite-search-query']")
    private WebElement searchField;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 20)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
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
