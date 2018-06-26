package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPrestaShop {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@Test
	public void loginWithWrongCredentionals() {

		driver.get(" http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.name("email")).sendKeys("mayche2206@yahoo.com");
		driver.findElement(By.name("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();

		String MyFail = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(MyFail.contains("Authentication failed."));

	}

	@Test
	public void loginWithWrongEmailType() {

		driver.get(" http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.name("email")).sendKeys("mayche2206yahoo.com");
		driver.findElement(By.name("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();

		String MyFail2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(MyFail2.contains("Invalid email address."));
	}
	
	@Test
	public void loginWithBlankEmailField() {

		driver.get(" http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.name("email")).sendKeys("");
		driver.findElement(By.name("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();

		String MyFail3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(MyFail3.contains("An email address required."));
	}
	
	@Test
	public void loginWithEmtyPassword() {

		driver.get(" http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
	

			driver.findElement(By.id("email")).sendKeys("mayche2206@gmail.com");

			driver.findElement(By.id("passwd")).sendKeys("");

			driver.findElement(By.id("SubmitLogin")).click();
			
			String mytext1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
			
			Assert.assertTrue(mytext1.contains("Password is required."));
			

		}
	
	

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}