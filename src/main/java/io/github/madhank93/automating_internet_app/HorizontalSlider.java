package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HorizontalSlider {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/horizontal_slider");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test (priority = 1)
	public void clickOnTheSlider() {
		driver.findElement(By.tagName("input")).click();;
		String range = driver.findElement(By.id("range")).getText();
		Assert.assertEquals(range, "2.5");
	}
	
	@Test (priority = 2)
	public void moveSliderUsingKeyboard() {
		WebElement slider = driver.findElement(By.tagName("input"));
		slider.sendKeys(Keys.ARROW_RIGHT);
		String range = driver.findElement(By.id("range")).getText();
		Assert.assertEquals(range, "3");
	}
	
	@Test (priority = 3)
	public void moveSliderUsingMouse() {
		WebElement slider = driver.findElement(By.tagName("input"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 60, 0).build().perform();
		String range = driver.findElement(By.id("range")).getText();
		Assert.assertEquals(range, "5");
	}
}
