package com.tp.Nile.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/jasonwilliams/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gmail.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("identifierId")).sendKeys("hylton.williams@gmail.com");
        driver.findElement(By.className("VfPpkd-RLmnJb")).click();

        if(driver.getTitle()=="gmail"){
            System.out.println("success");
        }
        driver.close();
    }
}
