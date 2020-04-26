package io.github.madhank93.automating_internet_app;

import java.util.Timer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InfiniteScroll {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	// Since it's a infinite scroll closing the browser after 10 seconds
	@Test
	public void infiniteScroll() {
		boolean footerNote = driver.findElement(By.xpath("//div[contains(text(),'Powered by')]")).isDisplayed();

		try {
			Timer t = new java.util.Timer();
			t.schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					driver.quit(); // closing the browser after 10 secs
				}
			}, 10000);

			// Scrolling down
			while (footerNote) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			}
		} catch (NoSuchSessionException e) {
			System.out.println("Closed browser after 10 sec");
		}
	}
}
