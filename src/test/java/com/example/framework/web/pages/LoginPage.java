package com.example.framework.web.pages;

import com.example.framework.web.actions.PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final PageActions element;

    public LoginPage(WebDriver driver) {
        element = new PageActions(driver);
    }

    public LoginPage enterUserName(String userName) {
        element.type(By.name("userid"), userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        element.type(By.name("passid"), password);
        return this;
    }

    public LoginPage enterRepeatPassword(String repeatedPassword) {
        element.type(By.name("repeatpassid"), repeatedPassword);
        return this;
    }

    public LoginPage clickLoginButton() {
        element.click(By.name("submit"));
        return this;
    }

    public boolean isUserLoggedInSuccessfully() {
        boolean value = element.getAlertText().equalsIgnoreCase("Succesful login!");
        element.acceptAlert();
        return value;
    }

}
