package Selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;

public class T03_WebElement_Browser {
    WebDriver driver;
    String actualSelect;
    int numberOP;
     String userName = "mngr144435";
     String userPassword = "vugahEq";

    @BeforeClass
    public void beforeClass() {
        // System.setProperty("webdriver.gecko.driver", "D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

   // @Test
    public void TC_01_Verifydropdown() throws InterruptedException {
        System.out.println("TC_01_Verifydropdown");
        driver.get("http://daominhdam.890m.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebElement jobRole = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='job1']")));
        Select dropdownJob = new Select(jobRole);
        // Verify if the dropdown list is multiple selection
        Assert.assertFalse(dropdownJob.isMultiple());
        // select selectByVisibleText
        dropdownJob.selectByVisibleText("Automation Tester");
        actualSelect= dropdownJob.getFirstSelectedOption().getText();
        System.out.println("actualSelect"+actualSelect);
        Assert.assertEquals("Automation Tester", actualSelect);
        // select selectByValue
        dropdownJob.selectByValue("manual");
        actualSelect= dropdownJob.getFirstSelectedOption().getText();
        System.out.println("actualSelect"+actualSelect);
        Assert.assertEquals("Manual Tester", actualSelect);
        // select selectIndex
        dropdownJob.selectByIndex(3);
        actualSelect= dropdownJob.getFirstSelectedOption().getText();
        System.out.println("actualSelect"+actualSelect);
        Assert.assertEquals("Mobile Tester", actualSelect);
        numberOP= dropdownJob.getOptions().size();
        Assert.assertEquals(numberOP,5);
    }
    @Test
    public void TC_02_Verifytextbox() throws InterruptedException {
        driver.get("http://demo.guru99.com/v4");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("TC_02_Verifytextbox");
        login(userName,userPassword);
        WebElement newCustomerLk = new WebDriverWait(driver, 15).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")));
        newCustomerLk.click();

        Assert.assertEquals("Guru99 Bank New Customer Entry Page", driver.getTitle());
        WebElement cusName = driver.findElement(By.name("name"));
        cusName.sendKeys("DAM DAO");

        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys("10/10/1889");

        WebElement custAddr = driver.findElement(By.name("addr"));
        custAddr.sendKeys("123 con ga");

        WebElement custCity = driver.findElement(By.name("city"));
        custCity.sendKeys("HCm");

        WebElement cusState = driver.findElement(By.name("state"));
        cusState.sendKeys("CON GA");

        WebElement cusPin = driver.findElement(By.name("pinno"));
        cusPin.sendKeys("867868");

        WebElement cusPhoneNo = driver.findElement(By.name("telephoneno"));
        cusPhoneNo.sendKeys("0977514423");

        WebElement cusEmail = driver.findElement(By.name("emailid"));
        cusEmail.sendKeys("hdgshj@jhkj.com");

        WebElement cusPass = driver.findElement(By.name("password"));
        cusPass.sendKeys("123456789");

        WebElement submitBtn = driver.findElement(By.name("sub"));
        submitBtn.click();

        Thread.sleep(5000);
    }
    public void login (String user ,String pass){
        WebElement userId = driver.findElement(By.name("uid"));
        userId.sendKeys(user);

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(pass);

        WebElement btnLogin = driver.findElement(By.name("btnLogin"));
        btnLogin.click();
    }
}
