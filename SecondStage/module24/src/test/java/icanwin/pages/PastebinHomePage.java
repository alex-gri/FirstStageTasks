package icanwin.pages;

import icanwin.CustomConditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(id = "paste_code")
    private WebElement pasteCodeTextBox;

    @FindBy(name = "paste_expire_date")
    private WebElement pasteExpirationSelectBox;

    @FindBy(name = "paste_name")
    private WebElement pasteNameTextBox;

    @FindBy(id = "submit")
    private WebElement createNewPastePseudoButton; // Pseudo - because it's actually of input type.

    @FindBy(id = "success")
    private WebElement successNote;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 20)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public PastebinHomePage addPasteCode(String pasteCode) {
        pasteCodeTextBox.sendKeys(pasteCode);
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

    public boolean createPaste() {
        createNewPastePseudoButton.submit();
        return successNote.getText().contains("Your guest paste has been posted") ? true : false;
    }
}
