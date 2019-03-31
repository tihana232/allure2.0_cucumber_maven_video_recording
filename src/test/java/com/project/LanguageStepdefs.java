package com.project;

import com.project.helper.AllureAttachment;
import com.project.pages.HomePage;
import com.project.pages.LoginPage;
import com.project.webdriver.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Pavel on 1/17/2018.
 */
public class LanguageStepdefs {

    private LoginPage login;
    private HomePage home;
    private AllureAttachment screen;

    private static final Logger log = Logger.getLogger(LoginStepdefs.class);

    @Before
    public void setUp() throws MalformedURLException {
        login=new LoginPage(Driver.getDriver());
        home=new HomePage(Driver.getDriver());
        screen = new AllureAttachment(Driver.getDriver());
    }

    @And("^User select \"([^\"]*)\" language$")
    public void userSelectLanguage(String language){
       System.out.println("Assume method select language :)"+language);
    }

    @Then("^Language \"([^\"]*)\" is visible$")
    public void languageIsVisible(String arg0) {
        System.out.println("Assume method make assertion"+arg0);
    }

    @When("^Enters UserName as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
    public void entersUserNameAsAndPasswordAs(String username, String password) throws Throwable {
   //     login.loginOnSite(username, password);
        log.info("User logged in");
    }

    @After("@2")
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            screen.saveImageAttach(scenario.getName());
        }
        Driver.closeDriver();
    }
}
