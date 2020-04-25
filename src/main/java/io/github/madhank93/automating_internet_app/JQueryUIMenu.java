package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JQueryUIMenu {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	@Test (priority = 1)
	public void sendStringValue() {
		WebElement enabled = driver.findElement(By.linkText("Enabled"));
		new Actions(driver).moveToElement(enabled).perform();

		WebElement downloads = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.linkText("Downloads")));
		new Actions(driver).moveToElement(downloads).perform();

		WebElement PDF = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.linkText("PDF")));
		PDF.click();
	}

}
