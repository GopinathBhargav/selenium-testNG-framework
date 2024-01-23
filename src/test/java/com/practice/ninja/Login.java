package com.practice.ninja;

import com.base.Initialization;
import com.utils.ReusableUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Login extends Initialization {

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

    @Test(priority = 1)
    public void verifyWithValidCredentials(){

        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("zabc@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.xpath("//a[text()='Edit your account information']")).getText();
        Assert.assertEquals(actualText, "Edit your account information");

    }


    @Test(priority = 2)
    public void verifyWithInValidCredentials(){

        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("zab"+ReusableUtilities.generateTimeStamp()+"@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.cssSelector("div[class='alert alert-danger alert-dismissible']")).getText();
        Assert.assertEquals(actualText, "Warning: No match for E-Mail Address and/or Password.");

    }




}
