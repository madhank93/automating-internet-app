package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FloatingMenu {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/floating_menu");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test
	public void clickFloatingMenu() {
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
		
		String url = driver.getCurrentUrl();
		
		Assert.assertEquals(url, "https://the-internet.herokuapp.com/floating_menu#about");

	}

}
