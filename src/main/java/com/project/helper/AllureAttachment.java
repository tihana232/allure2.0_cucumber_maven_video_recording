package com.project.helper;

import com.project.webdriver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;

import java.io.*;

import static org.apache.commons.io.FileUtils.*;


public class AllureAttachment {

    private WebDriver driver;

    public AllureAttachment(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment()
    public void saveImageAttach(String name) throws IOException {
        driver = Driver.getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        copyFile(scrFile, new File(".\\target\\allure-results\\" + name + ".png"));
        File initialFile = new File(".\\target\\allure-results\\" + name + ".png");
        InputStream targetStream = new FileInputStream(initialFile);
        Allure.addAttachment("My attachment", targetStream);
    }
}