package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicAuth {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir")+ "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test (priority = 1)
	public void sendValidDetails() {
		// format- username:password@website-address.com
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		String successMessage = driver.findElement(By.xpath("//div[@id='content']//div//p")).getText();
		Assert.assertEquals(successMessage, "Congratulations! You must have the proper credentials.");
	}

}
