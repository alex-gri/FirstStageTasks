package hurtmeplenty.pages.googlecloud;

import icanwin.customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;

    @FindBy (xpath = "//input[@class='devsite-search-field devsite-search-query']")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 20)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public HomePage writeSearchTerm(String searchTerm) {
        searchField.sendKeys(searchTerm);
        return this;
    }

    public SearchResultsPage search() {
        searchField.submit();
        return new SearchResultsPage(driver);
    }
}
