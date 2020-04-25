package io.github.madhank93.automating_internet_app;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleWindows {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/windows");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	@Test
	public void handlingWindows() {
		driver.findElement(By.linkText("Click Here")).click();		
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
		
		driver.switchTo().window(windows.get(1));
		Assert.assertEquals(driver.getTitle(), "New Window");
				
		driver.switchTo().window(windows.get(0));
		Assert.assertEquals(driver.getTitle(), "The Internet");
		
		driver.switchTo().window(windows.get(1));
		driver.close();
		
	}

}
