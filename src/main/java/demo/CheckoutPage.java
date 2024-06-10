package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    //@FindBy(className = "summary_value_label")
    //private WebElement summaryValueLabel;

    //
    @FindBy(className = "summary_subtotal_label")
    private WebElement summarySubTotalLabel;
    //
    @FindBy(className = "summary_tax_label")
    private WebElement summaryTaxLabel;
    //
    @FindBy(className = "summary_total_label")
    private WebElement summaryTotalLabel;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }

    public boolean isCheckoutInfoCorrect() {
        if (summarySubTotalLabel.isDisplayed() && summaryTaxLabel.isDisplayed() && summaryTotalLabel.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean isPurchaseComplete() {
        return completeHeader.isDisplayed();
    }
}
