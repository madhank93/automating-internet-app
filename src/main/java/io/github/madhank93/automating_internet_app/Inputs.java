package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Inputs {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/inputs");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	@Test (priority = 1)
	public void sendStringValue() {
		WebElement inputBox = driver.findElement(By.tagName("input"));
		inputBox.sendKeys("I am Madhan");
		String value = inputBox.getAttribute("value");
				
		Assert.assertEquals(value, "");
	}
	
	@Test (priority = 2)
	public void sendNumericalValue() {
		WebElement inputBox = driver.findElement(By.tagName("input"));
		inputBox.sendKeys("1234");
		String value = inputBox.getAttribute("value");
				
		Assert.assertEquals(value, "1234");
	}
	

}
