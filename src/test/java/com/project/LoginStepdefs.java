package com.project;


import com.project.helper.AllureAttachment;
import com.project.pages.HomePage;
import com.project.pages.LoginPage;
import com.project.webdriver.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by ASUS on 29.08.2017.
 */

public class LoginStepdefs {

    private LoginPage login;
    private HomePage home;
    private AllureAttachment screen;

    private static final Logger log = Logger.getLogger(LoginStepdefs.class);

    @Before
    public void setUp() throws MalformedURLException {
        login = new LoginPage(Driver.getDriver());
        home = new HomePage(Driver.getDriver());
        screen = new AllureAttachment(Driver.getDriver());
    }

    @Given("^User on Login page$")
    public void userOnLoginPage() throws Throwable {
        login.openUrl("https://github.com/");
    }

    @When("^Enters UserName as ([^\"]*) and Password as ([^\"]*)$")
    public void entersUserNameAsLoginAndPasswordAsPassword(String username, String password) throws Throwable {
        login.loginOnSite(username, password);
        log.info("User logged in");
    }

    @Then("^Message displayed Login Successfully$")
    public void messageDisplayedLoginSuccessfully() throws Throwable {
        Assert.assertEquals(home.getUserName(), "Pavel");
    }

    @After("@1")
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            screen.saveImageAttach(scenario.getId());
            System.out.println("!!!!!"+scenario.getId());
        }
        Driver.closeDriver();
    }
}
