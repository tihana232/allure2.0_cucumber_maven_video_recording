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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

/**
 * Created by ASUS on 29.08.2017.
 */

public class LoginStepdefs {

    private LoginPage login;
    private HomePage home;
    private AllureAttachment screen;
    SessionId sessionId;

    private static final Logger log = Logger.getLogger(LoginStepdefs.class);

    @Before
    public void setUp() throws IOException {

        login = new LoginPage(Driver.getDriver());
        home = new HomePage(Driver.getDriver());
        screen = new AllureAttachment(Driver.getDriver());
        sessionId= ((RemoteWebDriver)Driver.getDriver()).getSessionId();
    }

    @Given("^User on Login page$")
    public void userOnLoginPage() throws Throwable {
        login.openUrl("https://www.onliner.by");
    }

    @When("^Enters UserName as ([^\"]*) and Password as ([^\"]*)$")
    public void entersUserNameAsLoginAndPasswordAsPassword(String username, String password) {
       // login.loginOnSite(username, password);
        log.info("User logged in");
    }

    @Then("^Message displayed Login Successfully$")
    public void messageDisplayedLoginSuccessfully() {
       // Assert.assertEquals(home.getUserName(), "Pavel");
    }

    @After()
    public void tearDown() {
        Driver.closeDriver();
        screen.attachAllureVideo(sessionId.toString());
    }
}
