package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DynamicContent {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/dynamic_content");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test (priority = 1)
	public void verifyImageChangesOnRefresh() {
		String imgPathBeforeRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"))
				.getAttribute("src");
		driver.navigate().refresh();

		// Referring the element after refresh otherwise results in Stale element
		// reference exception
		String imgPathAfterRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"))
				.getAttribute("src");
		Assert.assertNotNull(imgPathBeforeRefresh, imgPathAfterRefresh);
	}

	// Expecting StaleElementReferenceException exception to occur
	@Test (expectedExceptions = { StaleElementReferenceException.class })
	public void exceptionTest() {
		WebElement imgPathBeforeRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"));
		imgPathBeforeRefresh.getAttribute("src");
		driver.navigate().refresh();
		imgPathBeforeRefresh.getAttribute("src");

	}

}
