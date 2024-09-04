package com.example.framework.web.pages;

import com.example.framework.web.actions.PageActions;
import com.example.framework.web.utilities.PropertiesFileManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final PropertiesFileManager props;
    private final PageActions element;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        element = new PageActions(driver);
        props = new PropertiesFileManager("global.properties");
    }

    public HomePage open() {
        driver.get(props.getProperty("URL"));
        return this;
    }

    public HomePage navigateTo(String title) {
        element.click(By.partialLinkText(title));
        return this;
    }
}
