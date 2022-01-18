package test_1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.Locale;

public class StageMovement_Hw5 {
    WebDriver driver;
@Before
public void setUp(){
    System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://automationpractice.com/index.php");
    driver.manage().window().maximize();
}
@Test
    public  void stageMovements() throws InterruptedException {
    driver.findElement(By.xpath("//a[@title='Women']")).click();
    Actions actions= new Actions(driver);
    actions.sendKeys(Keys.PAGE_DOWN).perform();


    //Click on Add To Cart
    WebElement tshrt=driver.findElement(By.xpath("(//div[@class='product-image-container'])[1]"));
    actions.moveToElement(tshrt).perform();
    driver.findElement(By.xpath("(//*[@title='Add to cart'])[1]")).click();

   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2[1])")));
    //WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(.,'Product successfully')]")));

    //And verifies the product is added to the cart
    String message= driver.findElement(By.xpath("(//h2[1])")).getText();
    Assert.assertEquals("Product successfully added to your shopping cart",message);


    //And verifies the color, size, quantity, price, shipping, Total Price
    WebElement colorandsize= driver.findElement(By.xpath("//*[@id='layer_cart_product_attributes']"));
    //System.out.println(colorandsize.getText());
    Assert.assertEquals("Orange, S",colorandsize.getText());

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//strong[@class='dark'])[1]")));
    WebElement quantity= driver.findElement(By.xpath("(//strong[@class='dark'])[1]"));
    String quantityText = quantity.getText();
   // Assert.assertEquals("Quantity",quantityText);

    WebElement quantity_one= driver.findElement(By.xpath("//span[@id='layer_cart_product_quantity']"));
    String quantity_oneText =  quantity_one.getText();
   // Assert.assertEquals("1",quantity_oneText);

    String quantity1= quantityText+" " + quantity_oneText;
    Assert.assertEquals("Quantity 1",quantity1);

    WebElement total= driver.findElement(By.xpath("(//strong[@class='dark'])[2]"));
    String totalText=total.getText();
    WebElement total_price= driver.findElement(By.xpath("//span[@id='layer_cart_product_price']"));
    String total_priceText= total_price.getText();

    String totalPrice= totalText+" "+total_priceText;
    Assert.assertEquals("Total $16.51",totalPrice);

    WebElement shipping=driver.findElement(By.xpath("//span[@class='ajax_cart_shipping_cost']"));
    String shippingText= shipping.getText();
    Assert.assertEquals("$2.00",shippingText);

   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ajax_block_cart_total']")));
    WebElement totalshipping=driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']"));
    String totalshippingText= totalshipping.getText();
    Assert.assertEquals("$18.51",totalshippingText);

    //And clicks on Proceed to checkout
    driver.findElement(By.partialLinkText("Proceed")).click();

    //Then verify the Summary card is displayed(SELECT A CORE ELEMENT ON THAT PAGE FOR
    WebElement summaryCartTitle = driver.findElement(By.id("cart_title"));
    Assert.assertTrue(summaryCartTitle.isDisplayed());

    //Then verify the product is In stock
    WebElement stock= driver.findElement(By.xpath("//span[@class='label label-success']"));
   Assert.assertTrue(stock.isDisplayed());

//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_price_1_1_633106")));

   //Then verify the Unit price matches the price that is on the Add To Card page
    WebElement unitPrice = driver.findElement(By.id("product_price_1_1_0"));
    String unitPriceText = unitPrice.getText();
    Assert.assertEquals(unitPriceText, total_priceText);

    driver.findElement(By.xpath("//i[@class='icon-plus']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='total_product_price_1_1_0']")));
/*
   WebElement newtotal= driver.findElement(By.xpath("//span[@id='total_product_price_1_1_0']"));
   String newTotalText= newtotal.getText();
   Assert.assertEquals("$33.02",newTotalText);
*/
    //And click on Proceed to checkout
    driver.findElement(By.partialLinkText("Proceed")).click();

    //Then verify sign-in page displayed
     String title= driver.getTitle();
     Assert.assertTrue(title.contains("Login"));

//Then the user enters a username password and sign in
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aydinn.ilknuur@gmail.com");
    driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456789");
    driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

//Then Verify user is on the Address stage
     driver.findElement(By.xpath("//h1[@class='page-heading']")).isEnabled();

//And click on Proceed to checkout
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

 //Then verify user moves to the Shipping stage(SHIPPING
    WebElement ship= driver.findElement(By.className("page-heading"));
    Assert.assertTrue(ship.isDisplayed());

  //And click on Proceed to checkout
    driver.findElement(By.xpath("//button[@name='processCarrier']")).click();


//Then verify the error message: You must agree to the terms of service before continuing.
     WebElement error= driver.findElement(By.xpath("//p[@class='fancybox-error']"));
     Assert.assertTrue(error.isDisplayed());
Thread.sleep(3000);
 //Then click on x to exit out
    driver.findElement(By.xpath("//*[@title='Close']")).click();
    //driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();

//And click on the Terms of service checkbox
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();

//And click on Proceed to checkout
    driver.findElement(By.xpath("//button[@name='processCarrier']")).click();

 //Then verify a user is on the Payment stage(PLEASE CHOOSE YOUR PAYMENT
     WebElement payment= driver.findElement(By.xpath("//h1[@class='page-heading']"));
     String paymentText= payment.getText().toUpperCase();
     Assert.assertTrue(paymentText.equals("PLEASE CHOOSE YOUR PAYMENT METHOD"));
}

}
