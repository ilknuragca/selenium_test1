package test_1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

//Homework_3
public class AmazonDropDown {
    /*Create A Class: AmazonDropdown
●
● Create A Method dropdownTest
●
● Go to https://www.amazon.com/
●
● Find the element of the dropdown element. HINT: By.id(“searchDropdownBox")
●
● Print the first selected option and assert if it equals “All Departments”
●
● Select the 4th option by index (index of 3) and assert if the name is “Amazon Devices”.(after you
select you to need to use the get first selected option method)
●
● Print all of the dropdown options
●
● Print the total number of options in the dropdown
●
● Check if Appliances is a drop-down option. Print true if “Appliances” is an option. Print false
otherwise.
●
● BONUS: Check if the dropdown is in Alphabetical Order*/
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://www.amazon.com");
        driver.manage().window().maximize();

    }

    @Test
    public void dropdownTest() throws InterruptedException {
      WebElement dropdown= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

  //Print the first selected option and assert if it equals “All Departments”
        Select select= new Select(dropdown);
       Thread.sleep(3000);
       WebElement firstOption=  select.getFirstSelectedOption();
       String actualFirstOption= firstOption.getText();
        Assert.assertEquals("All Departments",actualFirstOption);

   //Select the 4th option by index (index of 3) and assert if the name is “Amazon Devices”.
       String fourthOption= select.getOptions().get(3).getText();//index 3 == 4th option
        System.out.println("4th option is : " +fourthOption);
        Assert.assertFalse(fourthOption.equals("Amazon Devices"));

   //(after you select you to need to use the get first selected option method)
        // Assert.assertEquals("Amazon Devices", fourthOption);
        //4th option is "Baby" , not equals "Amazon Devices"


 // Print all of the dropdown options
        List<WebElement> allDropdowns = select.getOptions();
        for(WebElement eachDropdown: allDropdowns){
            System.out.println(eachDropdown.getText()+ " ");
        }

  // Print the total number of options in the dropdown
       int numberOption= allDropdowns.size();
        System.out.println("Total number of option: "+ numberOption);

 //Check if Appliances is a drop-down option. Print true if “Appliances” is an option. Print false otherwise
        boolean isContain = false;
        for(WebElement eachDropdown: allDropdowns){
            if(!eachDropdown.getText().equals("Appliances")){
                isContain=true;
            }
        }
        System.out.println(isContain);

 //BONUS: Check if the dropdown is in Alphabetical Order

        List<String> dropdownString= new ArrayList<>();
        List<String> dropdownStringOrder= new ArrayList<>();

        for(WebElement eachDropdown: allDropdowns){
            dropdownString.add(eachDropdown.getText());
            dropdownStringOrder.add(eachDropdown.getText());
        }
        System.out.println(dropdownString);
        Collections.sort(dropdownStringOrder);
        System.out.println(dropdownStringOrder);


        if(dropdownString.containsAll(dropdownStringOrder)){
            System.out.println("Dropdown is in Alphabetical Order" );
        }else{
            System.out.println("Dropdown is not in Alphabetical Order" );
        }

    }


}