package com.practice.ninja;

import com.base.Initialization;
import com.utils.ReusableUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration extends Initialization {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowser("chrome");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void registerAccountDetails(){
        String email = "ABC09"+ ReusableUtilities.generateTimeStamp()+"@gmail.com";
        String registeredEmail = email.replace(":","_");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("ABC09");
        driver.findElement(By.id("input-lastname")).sendKeys("ABC09");
        driver.findElement(By.id("input-email")).sendKeys(registeredEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("12312312");
        driver.findElement(By.id("input-password")).sendKeys("1234567890");
        driver.findElement(By.id("input-confirm")).sendKeys("1234567890");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals("Your Account Has Been Created!",actualText);
    }

    @Test
    public void registerAccountDetailsWithExistingEmail(){
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("ABC09");
        driver.findElement(By.id("input-lastname")).sendKeys("ABC09");
        driver.findElement(By.id("input-email")).sendKeys("ABC092024-01-2512_34_14.332612@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("12312312");
        driver.findElement(By.id("input-password")).sendKeys("1234567890");
        driver.findElement(By.id("input-confirm")).sendKeys("1234567890");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
        Assert.assertEquals("Warning: E-Mail Address is already registered!",actualText);
    }
}
