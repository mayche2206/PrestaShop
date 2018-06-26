package com.cybertek;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

	WebDriver driver;
	Faker f;
	Random rApt;
	Random states;
	Random postCode;
	Random rStateClk;
	Random rMonthBirth;
	Random rDayBirth;
	Random rYearBirth;

	

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@BeforeMethod
	public void goToCurrentPage() {
		driver.get("http://automationpractice.com");
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() throws InterruptedException {
		f = new Faker();
		rApt = new Random(3);
		rStateClk = new Random();
		rMonthBirth = new Random();
		rDayBirth = new Random();
		rYearBirth = new Random();
	

		String randomEmail = f.name().username() + "@yahoo.com";
		String firstName = f.name().firstName();
		String lastName = f.name().lastName();
		String companyName = firstName + "_$_" + lastName + "LLC";
		String homeAddress = f.address().streetAddress();
		String randomCity = f.address().city();
		String zipCode = f.address().zipCode().substring(0, 5);
		String addInformation = f.chuckNorris().fact();
		String pWord = f.internet().password(5, 10);
		String firstLastName = firstName + " " + lastName;
		int stateIndex = rStateClk.nextInt(53) + 1;
		int monthIndex = rMonthBirth.nextInt(12)+1;
		int dayIndex = rDayBirth.nextInt(31)+1;
		int yearIndex = rYearBirth.nextInt(119)+1;
		
		// Sign in and go to next page
		driver.findElement(By.xpath("//a[@class='login']")).click();

		// add email and click create and account
		driver.findElement(By.id("email_create")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//span/i[@class='icon-user left']")).click();

		// Click < Mr > --> radioButton
		driver.findElement(By.id("id_gender1")).click();

		/*
		 * Fill up sign up form 1) First name 2) Last name 3) Add random password
		 */
		WebElement customerFirstName = driver.findElement(By.id("customer_firstname"));
		customerFirstName.sendKeys(firstName);

		WebElement customerLastName = driver.findElement(By.id("customer_lastname"));
		customerLastName.sendKeys(lastName);

		WebElement password = driver.findElement(By.id("passwd"));
		password.sendKeys(pWord);

		// Click on day/month/year
		Thread.sleep(1000);
		WebElement daySelect = driver.findElement(By.xpath("//select[@id='days']/option[" + dayIndex + "]"));
		daySelect.click();
		Thread.sleep(000);
		WebElement monthSelect = driver.findElement(By.xpath("//select[@id='months']/option[" + monthIndex + "]"));
		monthSelect.click();
		Thread.sleep(1000);
		WebElement yearSelect = driver.findElement(By.xpath("//select[@id='years']/option[" + yearIndex + "]"));
		yearSelect.click();

//		d.findElement(By.xpath("//select[@id='days']/option[28]")).click();
//		d.findElement(By.xpath("//select[@id='months']/option[9]")).click();
//		d.findElement(By.xpath("//select[@id='years']/option[30]")).click();
		
		// Click on both checkboxes
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();

		// Add random company name
		WebElement company = driver.findElement(By.id("company"));
		company.sendKeys(companyName);

		// Add random street
		WebElement address = driver.findElement(By.id("address1"));
		address.sendKeys(homeAddress + " Street");

		// Add random apartment number
		WebElement aptNum = driver.findElement(By.id("address2"));
		aptNum.sendKeys(f.address().buildingNumber());

		// Add random city name
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("" + randomCity);

		// Select state
		driver.findElement(By.xpath("//select[@id='id_state']")).click();
		Thread.sleep(1000);
		WebElement stateSelect = driver
				.findElement(By.xpath("//select[@id='id_state']/option[@value='" + stateIndex + "']"));
		stateSelect.click();

		// Add Zip code
		WebElement zip = driver.findElement(By.id("postcode"));
		zip.sendKeys(zipCode);


		// Add country as Default

		// Add additional information
		WebElement addInfo = driver.findElement(By.cssSelector("#other"));
		addInfo.sendKeys(addInformation);

		// Add home phone and Cell
		WebElement housePhone = driver.findElement(By.cssSelector("#phone"));
		housePhone.sendKeys(f.phoneNumber().cellPhone());

		WebElement cellPhone = driver.findElement(By.cssSelector("#phone_mobile"));
		cellPhone.sendKeys(f.phoneNumber().cellPhone());

		// Add alias address
		WebElement aliasAddress = driver.findElement(By.cssSelector("#alias"));
		aliasAddress.sendKeys(homeAddress);

		// Click register
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();

		// Log out
		driver.findElement(By.xpath("//div[@class='header_user_info'][2]/a")).click();

		// Log in again with already created credentials
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("[id='email']")).sendKeys(randomEmail);
		driver.findElement(By.cssSelector("[id='passwd']")).sendKeys(pWord);
		driver.findElement(By.cssSelector("[id='SubmitLogin']")).click();

		// Verify that correct first name and last name is displayed on the top right
		Thread.sleep(1000);

		WebElement curName = driver.findElement(By.xpath("//div[@class='header_user_info']/a/span"));
		String currentName = curName.getText();
		Assert.assertEquals(firstLastName, currentName);

	}

	@AfterClass
	public void quit() {
		driver.quit();
	}
}