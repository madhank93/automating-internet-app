package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragAndDrop {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://testpages.herokuapp.com/styled/drag-drop-javascript.html");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void dragAndDropYellowBox() {
		WebElement yellowBox = driver.findElement(By.xpath("//div[@id='draggable1']"));
		WebElement redBox = driver.findElement(By.xpath("//div[@id='droppable1']"));
		
		// Mouse drag and drop using Action class
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(yellowBox)
		   .moveToElement(redBox)
		   .release(redBox)
		   .build();

		dragAndDrop.perform();
	}
}
