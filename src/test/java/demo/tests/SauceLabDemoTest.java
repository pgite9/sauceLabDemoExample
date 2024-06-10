package demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demo.*;
import demo.utils.BaseTest;
//import jdk.nashorn.internal.parser.JSONParser;
import demo.utils.CommonConfigs;
import demo.utils.ExtentManager;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.*;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SauceLabDemoTest {
    private WebDriver driver;
    private JSONObject testData;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        extent = ExtentManager.getReporter();
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
    }

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
        test = extent.createTest("loginAndAddProductToCartTest", "Test to login and add a product to the cart");

        LoginPage loginPage = new LoginPage(driver, CommonConfigs.BASE_URL);
        loginPage.enterUsername((String) testData.get("username"));
        loginPage.enterPassword((String) testData.get("password"));
        loginPage.clickLogin();
        test.log(Status.INFO,"Clicked on Login.");

        // Assertion to verify that the login was successful
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
        test.log(Status.PASS,"Successfully logged into application.");


        AllProductsPage allProductsPage = new AllProductsPage(driver);
        allProductsPage.addFirstProductToCart();
        test.log(Status.INFO,"Clicked on Add Product.");
        allProductsPage.goToCart();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Assertion to verify that the product was added to the cart
        Assert.assertTrue(allProductsPage.isProductInCart(), "Product was not added to the cart!");
        test.log(Status.PASS,"Product successfully got added to cart.");

    }

    @Test
    public void completePurchaseTest() {
        test = extent.createTest("completePurchaseTest", "Test to complete a purchase");

        LoginPage loginPage = new LoginPage(driver,CommonConfigs.BASE_URL);
        loginPage.enterUsername((String) testData.get("username"));
        loginPage.enterPassword((String) testData.get("password"));
        loginPage.clickLogin();
        test.log(Status.INFO,"Login Successful.");
        // Assertion to verify that the login was successful
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");


        AllProductsPage allProductsPage = new AllProductsPage(driver);
        allProductsPage.addFirstProductToCart();
        test.log(Status.INFO,"Product Added to cart");
        allProductsPage.goToCart();
        test.log(Status.INFO,"Click on go to cart.");

        // Assertion to verify that the product was added to the cart
        Assert.assertTrue(allProductsPage.isProductInCart(), "Product was not added to the cart!");
        test.log(Status.PASS,"Successfully product added to cart.");

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName((String) testData.get("firstName"));
        checkoutPage.enterLastName((String) testData.get("lastName"));
        checkoutPage.enterPostalCode((String) testData.get("postalCode"));
        checkoutPage.clickContinue();
        test.log(Status.INFO,"Entered User data and clicked on Continue...");
        // Assertion to verify that the checkout information was entered correctly
        Assert.assertTrue(checkoutPage.isCheckoutInfoCorrect(), "Checkout information is incorrect!");
        test.log(Status.PASS,"Successfully order is confirmed.");
        checkoutPage.clickFinish();
        FinishPage finishPage = new FinishPage(driver);
        // Assertion to verify that the purchase was completed successfully
        Assert.assertTrue(finishPage.isPonyExpressMessageVisible(), "Purchase was not completed successfully!");
        test.log(Status.PASS,"Successfully Finish page is displayed.");

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
