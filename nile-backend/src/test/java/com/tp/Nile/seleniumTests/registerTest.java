package com.tp.Nile.seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class registerTest {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver",
                "/Users/brendandeyo/Desktop/Nile/nile-backend/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/signup");

        WebElement fullnameElement = driver.findElement(By.id("fname"));
        WebElement usernameElement = driver.findElement(By.id("username"));
        WebElement emailElement = driver.findElement(By.id("email"));
        WebElement passwordElement = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        fullnameElement.sendKeys("Brendan Deyo");
        usernameElement.sendKeys("Bdeyo28");
        emailElement.sendKeys("bdeyo1@talentpath.com");
        passwordElement.sendKeys("Password123");
        submitButton.click();

//        fullnameElement.submit();
//        usernameElement.submit();
//        emailElement.submit();
//        passwordElement.submit();



    }

}
