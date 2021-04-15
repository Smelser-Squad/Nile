package com.tp.Nile.seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class firstTest {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "/Users/brendandeyo/Desktop/Nile/nile-backend/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        WebElement firstResult = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
        driver.findElement(By.className("header_cart")).click();
        driver.manage().window().maximize();


    }
}
