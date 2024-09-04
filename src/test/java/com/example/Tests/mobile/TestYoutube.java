package com.example.Tests.mobile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
public class TestYoutube {
    public static AppiumDriver driver;

    @SuppressWarnings("deprecation")
    @BeforeMethod
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setUdid("RK8W2030H0R");
        options.setAppPackage("com.google.android.youtube");
        options.setAppActivity("com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
        options.setDeviceName("Samsung Galaxy A23");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("14");
        options.setAutoGrantPermissions(true);
        options.disableSuppressAccessibilityService();
        options.noReset();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "Test to play quraan on Youtube")
    public void testPlayMyFavoriteMusicOnYouTube() throws InterruptedException {
        // Click on search icon
        Thread.sleep(10000);
        WebElement searchIcon = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Search\"]"));
        searchIcon.click();

        // Send keys to search bar
        Thread.sleep(1000);
        WebElement searchBar = driver.findElement(AppiumBy.id("com.google.android.youtube:id/search_edit_text"));
        searchBar.sendKeys("Al jazeera live");

        // Click on search suggestion
        Thread.sleep(1000);
        String newXpath = "//android.widget.TextView[@text='al jazeera live']";
        WebElement searchSuggestion = driver.findElement(AppiumBy.xpath(newXpath));
        searchSuggestion.click();

        // Click on search result
        Thread.sleep(2000);
        String titleXpath = "//android.view.ViewGroup[contains(@content-desc, 'Al Jazeera Arabic Live')]";
        WebElement searchResult = driver.findElement(AppiumBy.xpath(titleXpath));
        searchResult.click();

        // Assertion - assert that the live chat text is visible
        String uploaderNameXpath = "//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/title\"]";
        WebElement uploaderName = driver.findElement(AppiumBy.xpath(uploaderNameXpath));
        Boolean uploaderNameResult = uploaderName.isDisplayed();

        Assert.assertTrue(uploaderNameResult, "Verify if the live chat text is visible");

    }
}
