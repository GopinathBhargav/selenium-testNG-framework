package com.base;

import com.utils.ReusableValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Initialization {

    WebDriver driver;
    public Properties prop;
    FileInputStream fis = null;
    public Initialization(){

        String separator = File.separator;
        String configFilePath = System.getProperty("user.dir") +
                separator+"src"+separator+"main"+separator+"java"+separator+
                "com"+separator+"config"+separator+"config.properties";

         prop = new Properties();

        try {
            fis = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.toString();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.toString();
        }
    }

    public WebDriver initializeBrowser(String browser){

        switch (browser) {
            case "chrome","CHROME" -> driver = new ChromeDriver();
            case "firefox","FIREFOX" -> driver = new FirefoxDriver();
            case "edge","EDGE" -> driver = new EdgeDriver();
            default -> driver = new SafariDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ReusableValues.IMPLICIT_TIME_OUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ReusableValues.PAGE_LOAD_TIME_OUT));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
