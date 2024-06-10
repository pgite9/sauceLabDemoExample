package demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // For Java 14, use appropriate WebDriver version
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
