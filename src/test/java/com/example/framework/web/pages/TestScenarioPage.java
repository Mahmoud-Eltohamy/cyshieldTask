package com.example.framework.web.pages;

import com.example.framework.web.actions.PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestScenarioPage {
    private final WebDriver driver;
    private final PageActions element;

    public TestScenarioPage(WebDriver driver) {
        this.driver = driver;
        element = new PageActions(driver);
    }

    public RegistrationPage navigateToRegisterForm() {
        element.click(By.partialLinkText("Register Form"));
        return new RegistrationPage(driver);
    }

    public LoginPage navigateToLoginForm() {
        element.click(By.partialLinkText("Login Form"));
        return new LoginPage(driver);
    }

}
