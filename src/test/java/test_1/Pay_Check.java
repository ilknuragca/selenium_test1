

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C03_Test02 {

    public static void main(String[] args) {
    
        //1."https://zero.webapsecurity.com" go
        //2.Signin click
        //3.Login => "username" 
        //4.Password => "password" 
        //5.Signin click
        //6.Pay Bills webpage go
        //7.amount => 500 
        //8.date => "2020-09-10" 
        //9.Pay button click
        //10."The payment was successfully submitted" cheked it

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //1
        driver.get("http://zero.webappsecurity.com");
        //2
        driver.findElement(By.xpath("//button[@id='signin_button']")).click();
        //3
        driver.findElement(By.id("user_login")).sendKeys("username");//By.xpath("//input[@id='user_login']")
        //4
        driver.findElement(By.id("user_password")).sendKeys("password");//By.xpath("//input[@id='user_password']")
        //5
        driver.findElement(By.xpath("//input[@type='submit']")).click();//By.xpath("//input[@name='submit']")
        //6
        driver.get("http://zero.webappsecurity.com");
        driver.findElement(By.xpath("//*[text()='Online Banking']")).click();//By.xpath("//strong[text()='Online Banking']")
        driver.findElement(By.id("pay_bills_link")).click();
        //7
        driver.findElement(By.id("sp_amount")).sendKeys("500");
        //8
        driver.findElement(By.id("sp_date")).sendKeys("2020-09-10");
        //9
        driver.findElement(By.id("pay_saved_payees")).click();
        //10
      WebElement result= driver.findElement(By.id("alert_content"));
      if(result.isDisplayed())
      {System.out.println("Test PASS");
      }else{
          System.out.println("Test FAIL");
      }

      /*  String expectedresult="The payment was successfully submitted.";
        String actualresult=  driver.findElement(By.xpath("//div[@id='alert_content']")).getText(); //By.id("alert_content")
       // System.out.println(actualresult);
       // System.out.println("expected " +expectedresult);
        if(expectedresult.equals(actualresult)){
            System.out.println("test PASS");
        }else{
            System.out.println("Test FAIL");
        }

       */
        driver.close();
    }
}
