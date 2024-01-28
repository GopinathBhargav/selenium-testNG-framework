package com.practice.ninja;

import com.base.Initialization;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Initialization {

    WebDriver driver;

    public Search(){
        super();
        System.out.println("search clas constructor executed");
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowser(prop.getProperty("browser"));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }


    @Test
    public void searchItems(){
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("iphone");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        String actualText = driver.findElement(By.xpath("//div[@class='product-thumb'] //h4/a[text()='iPhone']")).getText();
        Assert.assertEquals("iPhone",actualText);
    }

    @Test
    public void searchInvalidItems(){
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("iphonee");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        String actualText = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        Assert.assertEquals("There is no product that matches the search criteria.",actualText);
    }
}
