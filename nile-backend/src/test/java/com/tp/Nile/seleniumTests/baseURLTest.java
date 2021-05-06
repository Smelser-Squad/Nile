package com.tp.Nile.seleniumTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class baseURLTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "/Users/brendandeyo/Desktop/Nile/nile-backend/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        String baseURL = "http://localhost:3000";
        String expectedTitle = "Nile";

        String actualTitle = "";
        driver.get(baseURL);
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed");
        }
        else {
            System.out.println("Test Failed");
        }

        driver.close();

    }

}

