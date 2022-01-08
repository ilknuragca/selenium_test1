package test_1;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Homework_1 {
        WebDriver driver;

        @Before
        public void setUp(){
            System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("http://automationpractice.com/index.php");
            driver.manage().window().maximize();

        }
        @Test
        public void signIn() throws InterruptedException {
            WebElement signInBtn= driver.findElement(By.xpath("//a[@class='login']"));
            signInBtn.click();

            WebElement emailtext = driver.findElement(By.xpath("//input[@id='email_create']"));
            emailtext.sendKeys("aydinn.ilknuur@gmail.com");

            WebElement createAccount= driver.findElement(By.xpath("//i[@class='icon-user left']"));
            createAccount.click();

            driver.findElement(By.xpath("//*[text()='Create an account']")).isDisplayed();
            driver.findElement(By.xpath("//*[text()='Your personal information']")).isDisplayed();
            driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
            driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("İlknur");
            driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Aydin");
            driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456789");


//        Selecting birth day
            WebElement day = driver.findElement(By.id("days"));
            Select daySelect = new Select(day);
            daySelect.selectByValue("1");


            WebElement months=driver.findElement(By.id("months"));
            Select monthSelect = new Select(months);
            monthSelect.selectByValue("1");



//        Selecting birth year
            WebElement year = driver.findElement(By.id("years"));
            Select yearSelect = new Select(year);
            yearSelect.selectByValue("1990");


            driver.findElement(By.xpath("//div[@id='uniform-optin']")).click();
            driver.findElement(By.xpath("//input[@id='company']")).sendKeys("TechProed");
            driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("OneWay Street No: 25, 123456 , TechProed");
            driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Chicago");
            Select selectstate= new Select( driver.findElement(By.xpath("//select[@id='id_state']")));
            selectstate.selectByValue("5");
            driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("00000");
            driver.findElement(By.cssSelector("#other")).sendKeys("I want to be a member");
            driver.findElement(By.cssSelector("#phone")).sendKeys("+1-652-7895");
            driver.findElement(By.cssSelector("#phone_mobile")).sendKeys("+1-305-65-895");
            driver.findElement(By.cssSelector("#alias")).sendKeys("My address");
            driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
            // driver.findElement(By.xpath("//h1[@class='page-heading']")).isDisplayed();
            WebElement locateMyAccount = driver.findElement(By.xpath("//h1[@class='page-heading']"));
            Assert.assertTrue(locateMyAccount.isDisplayed());

            Thread.sleep(5000);
        }
        @Test
        public void fakertest() throws InterruptedException {
            Faker faker=new Faker();
            WebElement signInBtn= driver.findElement(By.xpath("//a[@class='login']"));
            signInBtn.click();

            driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(faker.internet().emailAddress());

            WebElement createAccount= driver.findElement(By.xpath("//i[@class='icon-user left']"));
            createAccount.click();

            driver.findElement(By.xpath("//*[text()='Create an account']")).isDisplayed();
            driver.findElement(By.xpath("//*[text()='Your personal information']")).isDisplayed();
            driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
            driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(faker.name().firstName());
            driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(faker.name().lastName());
            driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(faker.internet().password());

            WebElement day = driver.findElement(By.id("days"));
            Select daySelect = new Select(day);
            daySelect.selectByValue("1");


            WebElement months=driver.findElement(By.id("months"));
            Select monthSelect = new Select(months);
            monthSelect.selectByValue("1");



//        Selecting birth year
            WebElement year = driver.findElement(By.id("years"));
            Select yearSelect = new Select(year);
            yearSelect.selectByValue("1990");


            driver.findElement(By.xpath("//div[@id='uniform-optin']")).click();
            driver.findElement(By.xpath("//input[@id='company']")).sendKeys(faker.company().name());
            driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(faker.address().fullAddress());
            driver.findElement(By.xpath("//input[@id='city']")).sendKeys(faker.address().city());
            Select selectstate= new Select( driver.findElement(By.xpath("//select[@id='id_state']")));
            selectstate.selectByValue("5");
            driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(faker.address().zipCode());
            driver.findElement(By.cssSelector("#other")).sendKeys("I want to be a member");
            driver.findElement(By.cssSelector("#phone")).sendKeys(faker.phoneNumber().cellPhone());
            driver.findElement(By.cssSelector("#phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());
            driver.findElement(By.cssSelector("#alias")).sendKeys("My address");
            driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
            // driver.findElement(By.xpath("//h1[@class='page-heading']")).isDisplayed();
            WebElement locateMyAccount = driver.findElement(By.xpath("//h1[@class='page-heading']"));
            Assert.assertTrue(locateMyAccount.isDisplayed());
            Thread.sleep(3000);

        }



        @After
        public void tearDown(){
            driver.quit();
        }

    }





