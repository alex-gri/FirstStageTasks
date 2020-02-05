package bringiton.pages;

import icanwin.customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PastebinHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy (id = "paste_code")
    private WebElement pasteCodeTextBox;

    @FindBy (name = "paste_format")
    private WebElement pasteSyntaxHighlightingSelectBox;

    @FindBy (name = "paste_expire_date")
    private WebElement pasteExpirationSelectBox;

    @FindBy (name = "paste_name")
    private WebElement pasteNameTextBox;

    @FindBy (id = "submit")
    private WebElement createNewPastePseudoButton; // Pseudo - because it's actually of input type.

    public PastebinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public PastebinHomePage addPasteCode() {
        pasteCodeTextBox.sendKeys(getPasteCodeFromProperties());
        return this;
    }

    // Reading test-data from property-file.
    protected String getPasteCodeFromProperties() {
        FileInputStream fileInputStream;
        Properties property = new Properties();
        StringBuilder propertyFilePathBuilder = new StringBuilder();
        propertyFilePathBuilder.append("src").append(File.separator)
                               .append("test").append(File.separator)
                               .append("resources").append(File.separator).append("BringItOn_Code.properties");

        try {
            fileInputStream = new FileInputStream(propertyFilePathBuilder.toString());
            property.load(fileInputStream);
        } catch (IOException e) {
            logger.warn("Property file is not found!");
        }
        return String.format(property.getProperty("paste.code"));
    }

    public PastebinHomePage selectSyntaxHighlighting(String pasteSyntaxHighlighting) {
        Select selectBox = new Select(pasteSyntaxHighlightingSelectBox);
        selectBox.selectByVisibleText(pasteSyntaxHighlighting);
        return this;
    }

    public PastebinHomePage selectPasteExpiration(String expirationValue) {
        Select selectBox = new Select(pasteExpirationSelectBox);
        selectBox.selectByVisibleText(expirationValue);
        return this;
    }

    public PastebinHomePage setPasteName(String pasteName) {
        pasteNameTextBox.sendKeys(pasteName);
        return this;
    }

    public PastebinPastePage createPaste() {
        createNewPastePseudoButton.submit();
        return new PastebinPastePage(driver);
    }
}
