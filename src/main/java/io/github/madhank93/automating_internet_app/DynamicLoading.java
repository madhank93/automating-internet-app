package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DynamicLoading {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void waitTillElementDispalyed() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.cssSelector("#start button")).click();
				
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish")));
		
		String textValue = driver.findElement(By.xpath("//div[@id='finish']")).getText();	
		Assert.assertEquals(textValue, "Hello World!");

	}

	@Test
	public void waitTillElementRendered() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("#start button")).click();
				
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish")));
		
		boolean element = driver.findElement(By.id("finish")).isDisplayed();
		Assert.assertEquals(element, true);

	}


}
