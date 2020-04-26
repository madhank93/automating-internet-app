package io.github.madhank93.automating_internet_app;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExitIntent {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/exit_intent");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test
	public void mouseMove() throws AWTException {

		// Mouse movement using Robot class
		Robot robot = new Robot();
		robot.mouseMove(150, 150);
		robot.mouseMove(125, 125);

		driver.findElement(By.xpath("//p[contains(text(),'Close')]")).isDisplayed();

		String modalWindowTitle = driver.findElement(By.xpath("//div[@id='ouibounce-modal']//h3")).getText();
		Assert.assertEquals(modalWindowTitle, "THIS IS A MODAL WINDOW");

		driver.findElement(By.xpath("//p[contains(text(),'Close')]")).click();
	}
}
