package com.tp.Nile.seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class cssSelectorTests {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver",
                "/Users/brendandeyo/Desktop/Nile/nile-backend/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/");

        String expectedTitle = "Nile";
        String actualTitle = "";
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed");
        }
        else {
            System.out.println("Test Failed");
        }

        driver.findElement(By.cssSelector(".hearder_searchInput")).sendKeys("Shirts");
        driver.findElement(By.cssSelector("#product_image")).click();
        driver.findElement(By.cssSelector(".search")).sendKeys("No questions here, sir;");
        driver.findElement(By.cssSelector(".toggleQuestion")).click();
        driver.findElement(By.cssSelector(".post")).sendKeys("This is the best question ever omg. " +
                "Here are some filler words because I don't know what else to write");
        driver.findElement(By.cssSelector("#root > div > div.SingleProductListing > div.section > div > div > div > form > button")).click();

    }

}
