package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DisappearingElements {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/disappearing_elements");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	// Check gallery link is displayed
	@Test
	public void isGalleryLinkDisplayed() {
		boolean galleryLink = false;

		try {
			galleryLink = driver.findElement(By.xpath("//a[contains(text(),'Gallery')]")).isDisplayed();
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
			galleryLink = driver.findElement(By.xpath("//a[contains(text(),'Gallery')]")).isDisplayed();
		}
		Assert.assertEquals(galleryLink, true);
	}

}
