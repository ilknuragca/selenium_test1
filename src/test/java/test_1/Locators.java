package test_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Homework_2
public class Locators {
    WebDriver driver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","./drivers./chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();

    }

 @Test
    public void test(){
  //When a user logs in the https://www.saucedemo.com/ as a standard_user id
        driver.get( "https://www.saucedemo.com/ ");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

//And verify the Products is displayed on the page
        Assert.assertEquals("PRODUCTS",driver.findElement(By.xpath("//span[@class='title']")).getText());

//And gets all the prices on a list
        List<WebElement> product= new ArrayList<>();
        List<String> stringPrice= new ArrayList<>();
        product= driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
            for(WebElement w:product){
                stringPrice.add(w.getText());

            }
     System.out.println("All prices => " +stringPrice);

//The verifies the minimum price is greater than $5
     List<Double>  doublePrice= new ArrayList<>();
     for(String str: stringPrice){
        str= str.substring(1);
         Double a=Double.parseDouble(str);
         doublePrice.add(a);
     }
 //    System.out.println("Price =>" +doublePrice);
     Collections.sort(doublePrice);
    // System.out.println("Order Price =>" +doublePrice);

     Assert.assertTrue("The minimum price is not greater than $5 " ,doublePrice.get(0)>5);

     //The verifies the maximum price is less than $50
     Assert.assertTrue("The verifies the maximum price is not less than $5",doublePrice.get(doublePrice.size()-1)<50);

 }


    @After
    public void tearDown(){
        driver.quit();
    }


}
