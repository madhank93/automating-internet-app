package io.github.madhank93.automating_internet_app;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenshotOnError {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot screenShot = (TakesScreenshot) driver;
				FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),
						new File(System.getProperty("user.dir") + "/screenshot" + timestamp + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test(priority = 1)
	public void fullPageScreenshot() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			TakesScreenshot screenShot = (TakesScreenshot) driver;
			FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + "/screenshot" + timestamp + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void generateException() {
		driver.findElement(By.xpath("element-not-in-the-page")).click();
	}

}
