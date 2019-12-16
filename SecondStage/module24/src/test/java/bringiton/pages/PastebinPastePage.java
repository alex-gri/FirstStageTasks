package bringiton.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PastebinPastePage extends AbstractPage {

    @FindBy (xpath = "//div[@class='paste_box_info']//h1")
    private WebElement pasteName;

    @FindBy (id = "paste_code")
    private WebElement pasteCodeTextBox;

    @FindBy (xpath = "//div[@id='code_buttons']//a[text()='Bash']")
    private WebElement syntaxHighlightingMark;

    public PastebinPastePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleContainsPasteName() {
        return driver.getTitle().contains(pasteName.getText());
    }

    public boolean syntaxHighlightingIsBash() {
        return syntaxHighlightingMark.getText().equals("Bash");
    }

    public boolean pasteCodeEqualsToSource() {
        return pasteCodeTextBox.getText().equals(getPasteCodeFromProperties());
    }

    protected String getPasteCodeFromProperties() {
        FileInputStream fileInputStream;
        Properties property = new Properties();
        StringBuilder propertyFilePathBuilder = new StringBuilder();
        propertyFilePathBuilder.append("resources").append(File.separator).append("BringItOn_Code.properties");

        try {
            fileInputStream = new FileInputStream(propertyFilePathBuilder.toString());
            property.load(fileInputStream);
        } catch (IOException e) {
            logger.warn("Property file is not found!");
        }
        return String.format(property.getProperty("paste.code"));
    }
}
