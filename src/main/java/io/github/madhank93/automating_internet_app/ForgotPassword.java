package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgotPassword {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/forgot_password");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void forgotPassword() {
		driver.findElement(By.id("email")).sendKeys("test@mail.com");
		driver.findElement(By.id("form_submit")).click();
		
		String message = driver.findElement(By.id("content")).getText();
		
		Assert.assertEquals(message, "Your e-mail's been sent!");
	}

	
}
