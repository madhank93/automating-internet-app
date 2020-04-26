package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KeyPresses {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/key_presses");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void keyboardPressEvent() {
		WebElement inputBox = driver.findElement(By.id("target"));
		String result;
		
		inputBox.sendKeys(Keys.ARROW_DOWN);
		result = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(result, "You entered: DOWN");
		
		inputBox.sendKeys(Keys.ESCAPE);
		result = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(result, "You entered: ESCAPE");
		
		inputBox.sendKeys(Keys.BACK_SPACE);
		result = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(result, "You entered: BACK_SPACE");	
	}
}
