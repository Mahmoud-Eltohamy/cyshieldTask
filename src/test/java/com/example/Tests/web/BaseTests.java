package com.example.Tests.web;

import com.example.framework.web.drivers.DriverFactory;
import org.testng.annotations.*;
import com.example.framework.web.pages.HomePage;
import com.example.framework.web.pages.LoginPage;
import com.example.framework.web.pages.RegistrationPage;
import com.example.framework.web.pages.TestScenarioPage;

public class BaseTests {

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected TestScenarioPage testScenarioPage;
    protected static DriverFactory driverFactory = new DriverFactory();


    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driverFactory.setDriver(DriverFactory.BrowserType.CHROME);
                break;
            case "firefox":
                driverFactory.setDriver(DriverFactory.BrowserType.FIREFOX);
                break;
            default:
                driverFactory.setDriver(DriverFactory.BrowserType.CHROME);
        }
        driverFactory.getDriver().manage().window().maximize();
        homePage = new HomePage(driverFactory.getDriver());
        registrationPage = new RegistrationPage(driverFactory.getDriver());
        testScenarioPage = new TestScenarioPage(driverFactory.getDriver());
        loginPage = new LoginPage(driverFactory.getDriver());
    }

    @AfterTest
    public void tearDown() {
        driverFactory.getDriver().quit();

    }

}
