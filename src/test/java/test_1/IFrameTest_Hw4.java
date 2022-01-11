package test_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class IFrameTest_Hw4 {

    WebDriver driver;

    @Before
    public void setUp(){
      //  System.setProperty("webdriver.chrome.driver","./drivers./chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
     //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }
      @Test
    public void iframeTest() throws InterruptedException {
          driver.get("https://html.com/tags/iframe/");

          Actions actions=new Actions(driver);
          Thread.sleep(3000);
          actions.sendKeys(Keys.PAGE_DOWN).perform();

          Thread.sleep(3000);
          actions.sendKeys(Keys.PAGE_DOWN).perform();


          WebElement framex= driver.findElement(By.xpath("//iframe[@class='lazy-loaded']"));
          driver.switchTo().frame(framex);
          //driver.switchTo().frame(0); //with index

          driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();

          Thread.sleep(10000);
       driver.findElement(By.xpath("//button[@class='ytp-play-button ytp-button']")).click();

       Thread.sleep(5000);
      }

@After
    public void tearDown(){
        driver.quit();
}
}
