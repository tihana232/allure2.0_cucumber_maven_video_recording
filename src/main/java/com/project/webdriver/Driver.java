package com.project.webdriver;

import org.omg.CORBA.SystemException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("chrome");
            browser.setVersion("65.0");
            browser.setCapability("enableVNC", true);
            browser.setCapability("enableVideo", true);
            browser.setCapability("screenResolution", "1920x1080x24");

            try {

                driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), browser);
                driver.manage().window().setSize(new Dimension(1920, 1080));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}