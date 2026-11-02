
package com.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GmailSignInTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyGmailSignIn() {

        // Step 1: Navigate to Gmail website
        driver.get("https://workspace.google.com/intl/en-US/gmail/");

        // Step 2: Click on Sign in button
        WebElement signInButton = driver.findElement(
            By.xpath("//a[@aria-label='Sign into Gmail']")
        );

        signInButton.click();

        // Step 3: Switch to the newly opened tab
        ArrayList<String> tabs = new ArrayList<>(
            driver.getWindowHandles()
        );

        driver.switchTo().window(tabs.get(1));

        // Step 4: Locate Email or phone input field
        WebElement emailField = driver.findElement(
            By.id("identifierId")
        );

        // Step 5: Assert that the email field is visible
        Assert.assertTrue(
            emailField.isDisplayed(),
            "Email or phone field is not visible!"
        );

        System.out.println("PASS: Email or phone field is visible.");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
