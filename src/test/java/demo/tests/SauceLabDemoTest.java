package demo.tests;

import demo.AllProductsPage;
import demo.LoginPage;
import demo.utils.BaseTest;
import jdk.nashorn.internal.parser.JSONParser;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.FileReader;

public class SauceLabDemoTest {
    private WebDriver driver;
    private JSONObject testData;

    @BeforeMethod
    public void setUp() {
        driver = BaseTest.getDriver();
        loadTestData();
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.quitDriver();
    }

    @Test
    public void loginAndAddProductToCartTest() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername((String) testData.get("username"));
        loginPage.enterPassword((String) testData.get("password"));
        loginPage.clickLogin();

        AllProductsPage allProductsPage = new AllProductsPage(driver);
        allProductsPage.addFirstProductToCart();
        allProductsPage.goToCart();
    }

    private void loadTestData() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/test/resources/testdata.json");
            Object obj = parser.parse(reader);
            testData = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
