package io.github.madhank93.automating_internet_app;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NestedFrames {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/nested_frames");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void switchFrames() {
		
		String frameText;
		
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-middle");
		
		frameText = driver.findElement(By.id("content")).getText();
		
		Assert.assertEquals(frameText, "MIDDLE");
		
	}



}
