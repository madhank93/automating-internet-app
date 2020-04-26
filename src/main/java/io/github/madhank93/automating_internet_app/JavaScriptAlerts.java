package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScriptAlerts {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	
	@Test
	public void handleJsAlert() {
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		driver.switchTo().alert().accept();
		
		String resultMessgae = driver.findElement(By.cssSelector("#result")).getText();
		Assert.assertEquals(resultMessgae, "You successfuly clicked an alert");
	}
	
	@Test
	public void acceptJsConfirm() {
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		driver.switchTo().alert().accept();
		
		String resultMessgae = driver.findElement(By.cssSelector("#result")).getText();
		Assert.assertEquals(resultMessgae, "You clicked: Ok");
	}
	
	@Test
	public void cancelJsConfirm() {
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		driver.switchTo().alert().dismiss();
		
		String resultMessgae = driver.findElement(By.cssSelector("#result")).getText();
		Assert.assertEquals(resultMessgae, "You clicked: Cancel");
	}
	
	@Test
	public void handleJsPrompt() {
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		driver.switchTo().alert().sendKeys("I am Madhan");
		driver.switchTo().alert().accept();
		
		String resultMessgae = driver.findElement(By.cssSelector("#result")).getText();
		Assert.assertEquals(resultMessgae, "You entered: I am Madhan");
	}
}
