package team_work;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Excell_Test {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/List_of_countries_by_GDP_(nominal)");
        driver.manage().window().maximize();
    }

    @Test
    public void wikipedia_Table(){
       // extentTest.pass("Get the country names and GDP, insert into map");

        List<WebElement> countries=driver.findElements(By.xpath("//table[@class='wikitable sortable static-row-numbers plainrowheaders srn-white-background jquery-tablesorter']//tbody//tr//td[1]"));
 //                                driver.findElements(By.xpath("(//tbody)[3]//tr//td[1]"));

        List<WebElement> gdp= driver.findElements(By.xpath("(//tbody)[3]//tr//td[4]")); //Estimate

        Map<String,String> countryGDP= new LinkedHashMap<>(); //koyduğun sıraya göre getirir

        for(int i=0; i<countries.size();i++){
           String countryName= countries.get(i).getText();
           String gdpCountry= gdp.get(i).getText();

           countryGDP.put(countryName,gdpCountry);
           excellwrite(countryName,gdpCountry,i);

        }
        System.out.println(countryGDP);


    }


    public void excellwrite(String a, String b,int c) {
        try {
            String path = "./src/test/java/resources/test.xlsx";

            FileInputStream inputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
           sheet.createRow(0).createCell(0).setCellValue("COUNTRY");
           sheet.getRow(0).createCell(1).setCellValue("GDP");

            sheet.createRow(c+1).createCell(0).setCellValue(a);
            sheet.getRow(c+1).createCell(1).setCellValue(b);

            FileOutputStream fileOutputStream= new FileOutputStream(path);
            workbook.write(fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}