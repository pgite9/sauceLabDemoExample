package demo.tests;

import demo.AllProductsPage;
import demo.LoginPage;
import demo.utils.BaseTest;
//import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

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
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadTestData() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/testData/loginData.json");
            //JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            Object obj = parser.parse(reader);
            testData = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
