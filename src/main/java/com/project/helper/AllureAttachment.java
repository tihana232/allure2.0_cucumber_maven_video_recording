package com.project.helper;

import com.project.webdriver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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
        targetStream.close();

   }

    private static String selenoidUrl = "http://127.0.0.1:4444"; //127.0.0.1
    //@Step
    public static void attachAllureVideo(String sessionId) {
        try {
            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        int exit = 1;
        for (int i = 0; i < 20; i++) {
            try {
                int size = Integer.parseInt(url.openConnection().getHeaderField("Content-Length"));
                System.out.println("Content-Length: " + size);
                System.out.println("i: " + i);
                if (size > lastSize) {
                    lastSize = size;
                    Thread.sleep(1000);
                } else if (size == lastSize) {
                    System.out.println("Content-Length: " + size);
                    System.out.println("exit: " + exit);
                    exit--;
                    Thread.sleep(500);
                }
                if (exit < 0) {
                    System.out.println("video ok!");
                    return url.openStream();
                }
            } catch (Exception e) {
                System.out.println("getSelenoidVideo: " + e.getMessage());
                //e.printStackTrace();
            }
        }

        return null;
    }

    public static void deleteSelenoidVideo(URL url) {
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setDoOutput(true);
            deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            System.out.println("deleteSelenoidVideo");
            System.out.println(deleteConn.getResponseCode());
            System.out.println(deleteConn.getResponseMessage());
            deleteConn.disconnect();
        } catch (IOException e) {
            System.out.println("deleteSelenoidVideo");
            e.printStackTrace();
        }
    }
}