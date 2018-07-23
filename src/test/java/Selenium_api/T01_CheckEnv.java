package Selenium_api;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.firefox.FirefoxDriver;

public class T01_CheckEnv {
   // System.set("webdriver.gecko.driver", "/Users/username/Downloads/geckodriver");
    WebDriver driver ;

    @Test
    public void TC_01_CheckUrlAndTitle() {
        System.out.println("Check homepage title");
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "Guru99 Bank Home Page");

        System.out.println("Check homepage urc");
        String homePageUrl = driver.getCurrentUrl();
        Assert.assertEquals(homePageUrl, "http://demo.guru99.com/v4/");
    }

    @BeforeClass
    public void beforeClass() {
       // System.setProperty("webdriver.gecko.driver", "D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver","D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/v4/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
