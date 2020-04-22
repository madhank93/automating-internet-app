package io.github.madhank93.automating_internet_app;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkboxes {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/checkboxes");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	// Check which checkbox is selected
	@Test
	public void checkSelectedCheckbox() {

		boolean checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected();
		Assert.assertEquals(checkbox1, false);

		boolean checkbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).isSelected();
		Assert.assertEquals(checkbox2, true);
	}

	// Select checkbox1
	@Test
	public void selectCheckbox1() {
		WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
		checkbox1.click();
		boolean elementSelected = checkbox1.isSelected();
		Assert.assertEquals(elementSelected, true);
	}

	// Un-select all checkboxes
	@Test
	public void unselectAllCheckbox() {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id='checkboxes']"));

		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
	}

}
