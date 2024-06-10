package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductsPage {
    private WebDriver driver;

    public AllProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[text()='Add to cart'])[1]")
    private WebElement firstProductAddToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public void addFirstProductToCart() {
        firstProductAddToCartButton.click();
    }

    public void goToCart() {
        cartIcon.click();
    }
}
