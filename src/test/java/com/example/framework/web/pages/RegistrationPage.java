package com.example.framework.web.pages;

import com.example.framework.web.actions.PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class RegistrationPage {
    private final PageActions element;

    public RegistrationPage(WebDriver driver) {
        element = new PageActions(driver);
    }

    public RegistrationPage enterUserId(String userId) {
        element.type(By.name("userid"), userId);
        return this;

    }

    public RegistrationPage enterUserPassword(String password) {
        element.type(By.name("passid"), password);
        return this;
    }

    public RegistrationPage enterUserName(String username) {
        element.type(By.name("username"), username);
        return this;
    }

    public RegistrationPage enterUserAddress(String address) {
        element.type(By.name("address"), address);
        return this;
    }

    public RegistrationPage selectUserCountry(String country) {
        element.selectOptionWithVisibleText(By.name("country"), country);
        return this;
    }

    public RegistrationPage enterZipCode(String zipCode) {
        element.type(By.name("zip"), zipCode);
        return this;
    }

    public RegistrationPage enterUserEmail(String email) {
        element.type(By.name("email"), email);
        return this;
    }

    public RegistrationPage selectEnglishLanguage() {
        if (!element.isSelected(By.cssSelector("input[name='languageQuestion']"))) {
            element.click(By.cssSelector("input[name='languageQuestion']"));
        }
        return this;
    }

    public RegistrationPage deSelectEnglishLanguage() {
        if (element.getElementAttribute(By.cssSelector("input[name='languageQuestion']"), "checked").equalsIgnoreCase("true")) {
            element.click(By.cssSelector("input[name='languageQuestion']"));
        }
        return this;
    }

    public RegistrationPage fillAboutSection(String text) {
        element.type(By.id("desc"), text);
        return this;
    }

    public RegistrationPage selectSex(Gender gender) {
        switch (gender) {
            case MALE:
                element.click(By.cssSelector("input[value='Male']"));
                break;
            case FEMALE:
                element.click(By.cssSelector("input[value='Female']"));
                break;
        }
        return this;
    }

    public void clickSubmit() {
        element.click(By.name("submit"));
    }

    public boolean isRegistrationFormDisplayed() {
        return element.isDisplayed(By.name("registration"));
    }

    public boolean isUserAbleToProceed() {
        List<String> errorMessages = List.of("User Id should not be empty / length be between 5 to 12", "Password should not be empty / length be between 7 to 12", "Username must have alphabet characters only", "User address must have alphanumeric characters only", "Select your country from the list", "ZIP code must have numeric characters only", "You have entered an invalid email address!");


        boolean value = !errorMessages.contains(element.getAlertText());
        element.acceptAlert();
        return value;
    }

    public enum Gender {
        MALE, FEMALE
    }
}
