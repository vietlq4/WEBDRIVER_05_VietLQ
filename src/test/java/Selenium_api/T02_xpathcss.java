package Selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class T02_xpathcss {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // System.setProperty("webdriver.gecko.driver", "D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\Serenity\\auto\\WEBDRIVER_05_VietLQ\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://live.guru99.com");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_VerifyURLandtitle() {
        System.out.println("Check homepage title");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_Loginempty() {
        System.out.println("Check homepage title");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
        Assert.assertEquals("This is a required field.", EmailErrorMsg);
        String PassErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
        Assert.assertEquals("This is a required field.", PassErrorMsg);

    }
    @Test
    public void TC_03_LoginwithEmailinvalid() {
        System.out.println("Check homepage title");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("123434234@12312.123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
        System.out.println(EmailErrorMsg);
        Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", EmailErrorMsg);

    }
    @Test
    public void TC_04_LoginwithPasswordincorrect() {
        System.out.println("Check homepage title");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
        System.out.println(EmailErrorMsg);
        Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", EmailErrorMsg);

    }

    @Test
    public void TC_05_Createanaccount() throws InterruptedException {
        System.out.println("Check homepage title");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys("viet");
        driver.findElement(By.id("lastname")).sendKeys("Le");
        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys("automation"+ randomNumber() + "@gmail.com");

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456asd");
        driver.findElement(By.id("confirmation")).clear();
        driver.findElement(By.id("confirmation")).sendKeys("123456asd");
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        String signUpSuccessMessage =     driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        Assert.assertEquals(signUpSuccessMessage, "Thank you for registering with Main Website Store.");

        driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/logoutSuccess/");

    }
    private int randomNumber() {
        	Random random =  new Random();
        	int number = random.nextInt(9999);
        	return number;
        }
}
