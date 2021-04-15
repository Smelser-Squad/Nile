package com.tp.Nile.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/jasonwilliams/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/");

        WebElement firstResult = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));

        assertEquals(driver.findElement(By.className("header_optionTwo")).getText(), "Sign In");
        driver.findElement(By.className("MuiSvgIcon-root")).click();
        assertEquals(driver.findElement(By.className("checkout_title")),"Shopping Cart");
        driver.close();

        driver.manage().window().maximize();


    }
}
