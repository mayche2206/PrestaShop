package com.cybertek;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class MockarooDataValidation {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mockaroo.com");
    }
    @Test
    public void titleCheck() {
        String expected = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
        String actual = driver.getTitle();
        assertEquals(actual, expected);
    }
    @Test
    public void mockarooDisplayed() {
        String expected = "mockaroo";
        String actual = driver.findElement(By.xpath("//div[@class='brand']")).getText();
        assertEquals(expected, actual);
    }
    @Test
    public void dataDisplayed() {
        String expected = "realistic data generator";
        String actual = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
        assertEquals(expected, actual);
    }
    @Test
    public void remove() {
        for (int j = 1; j <= 6; j++) {
            driver.findElement(By.xpath("(//a[@class='close remove-field remove_nested_fields'])[" + j + "]")).click();
        }
    }
    @Test
    public void fieldName() {
        String expected = "Field Name";
        String actual = driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText();
        assertEquals(actual, expected);
    }
    @Test
    public void type() {
        String expected = "Type";
        String actual = driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText();
        assertEquals(actual, expected);
    }
    @Test
    public void Options() {
        String expected = "Options";
        String actual = driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText();
        assertEquals(actual, expected);
    }
    @Test
    public void enabled() {
        driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).isEnabled();
    }
    @Test
    public void thousand() {
        String expected = "1000";
        String actual = driver.findElement(By.id("num_rows")).getAttribute("value");
        assertEquals(actual, expected);
    }
    @Test
    public void csv() {
        String expected = "csv";
        String actual = driver.findElement(By.id("schema_file_format")).getAttribute("value");
        assertEquals(actual, expected);
    }
    @Test
    public void unix() {
        String expected = "unix";
        String actual = driver.findElement(By.id("schema_line_ending")).getAttribute("value");
        assertEquals(actual, expected);
    }
    // schema_include_header
    @Test
    public void header() {
        boolean actual = driver.findElement(By.id("schema_include_header")).isSelected();
        assertEquals(actual, true);
    }
    @Test
    public void bom() {
        boolean actual = driver.findElement(By.id("schema_bom")).isSelected();
        assertEquals(actual, false);
    }
    @Test
    public void addField() {
        driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("schema_columns_attributes_1529980565079_name")).clear();
        driver.findElement(By.id("schema_columns_attributes_1529980076410_name")).sendKeys("City");
    }
}


