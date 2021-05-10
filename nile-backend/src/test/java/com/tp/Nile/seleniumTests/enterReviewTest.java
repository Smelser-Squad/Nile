package com.tp.Nile.seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class enterReviewTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",
                "/Users/brendandeyo/Desktop/Nile/nile-backend/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/createReview/1");

        String expectedTitle = "Nile";

        String actualTitle = "";
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Correct URL");
        }
        else {
            System.out.println("Failed Retrieving URL");
        }

        WebElement overallRating = driver.findElement(By.xpath("//*[@id=\"overall-rating\"]/div/div/span[3]"));
        overallRating.click();

        WebElement feature1 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[1]/div/span[1]"));
        feature1.click();
        WebElement feature2 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[2]/div/div/span[4]"));
        feature2.click();
        WebElement feature3 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[3]/div/div/span[5]"));
        feature3.click();
        WebElement feature4 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[4]/div/div/span[1]"));
        feature4.click();
        WebElement feature5 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[5]/div/div/span[5]"));
        feature5.click();
        WebElement feature6 = driver.findElement(By.xpath("//*[@id=\'feature-rating\']/div[6]/div/div/span[4]"));
        feature6.click();

        WebElement addHeadline = driver.findElement(By.xpath("//*[@id=\'text-title\']"));
        addHeadline.sendKeys("This is a test headline!");

        WebElement addReview = driver.findElement(By.xpath("//*[@id=\'text-summary\']"));
        addReview.sendKeys("This is a test review!! Lorem ipsum dolor sit amet, consectetur adipiscing elit" +
                ", sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est " +
                "laborum.");

        WebElement submitReview = driver.findElement(By.xpath("//*[@id=\"submit-review\"]"));
        submitReview.click();

    }

}
