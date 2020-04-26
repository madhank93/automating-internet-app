package io.github.madhank93.automating_internet_app;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GeoLocation {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

	}

	@Test(priority = 1)
	public void allowGeoloction() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.geolocation", 1);
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/geolocation");
		driver.findElement(By.tagName("button")).click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Latitude: ')]")));

		String successContent = driver.findElement(By.xpath("//p[contains(text(),'Latitude: ')]")).getText();
		assertTrue(successContent.contains("See it on Google"));

		driver.quit();
	}

	@Test(priority = 2)
	public void blockGeoloction() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.geolocation", 2);
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/geolocation");
		driver.findElement(By.tagName("button")).click();

		Thread.sleep(10000); // Adding sleep to visual confirm

		driver.quit();
	}
}
