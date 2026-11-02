package com.qa.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple Selenium WebDriver test on a public practice site.
 * Requires Google Chrome installed (Selenium Manager downloads the driver automatically).
 * Run headless with:  mvn test -Dheadless=true
 */
public class LoginUiTest {

    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,900");
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(LOGIN_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    private String flashMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash"))).getText();
    }

    @Test
    public void validLogin() {
        login("tomsmith", "SuperSecretPassword!");

        wait.until(ExpectedConditions.urlContains("/secure"));
        Assert.assertTrue(flashMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void invalidLogin() {
        login("tomsmith", "wrongPassword");

        Assert.assertTrue(flashMessage().contains("Your password is invalid!"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
