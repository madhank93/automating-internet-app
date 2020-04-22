package io.github.madhank93.automating_internet_app;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddRemoveElements {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir")+ "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	// Check the availability of "Add Element" button
	@Test(priority = 1)
	public void checkAddButtonAvailability() {
		boolean addButtonDisplayed = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"))
				.isDisplayed();
		boolean addButtonEnabled = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).isEnabled();
		Assert.assertEquals((addButtonDisplayed && addButtonEnabled), true);
	}

	// Click on the "Add Element" button
	@Test(priority = 2)
	public void clickAddButton() {
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
	}

	// Click "Add element" button using mouse action
	@Test(priority = 3)
	public void clickAddButtonUsingMouseAction() {
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
		
		Actions builder = new Actions(driver);
		Action actions = builder.moveToElement(addButton).click().build();

		actions.perform();
	}
	
	// Click "Add element" button using keyboard keys
	@Test(priority = 4)
	public void clickAddButtonUsingKeyboardAction() {
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
		
		addButton.sendKeys(Keys.TAB); // moving focus to the "Add element" button
		addButton.sendKeys(Keys.ENTER);
	}
	
	// To check availability of "Delete" button
	@Test(priority = 5)
	public void isDeleteButtonDisplayed() {
		boolean deleteButton = driver.findElement(By.xpath("//button[@class='added-manually']")).isDisplayed();
		Assert.assertEquals(deleteButton, true);
	}

	// To count the number of "Delete" button available
	@Test(priority = 6)
	public void countNumberOfDeleteButton() {
		clickAddButton(); // Calling addElement method, so that delete button count will be increased by 1. Total will be 2.
						
		List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));
		Assert.assertEquals(listOfDeleteButton.size(), 4);
	}

	// Method to delete the button "Delete"
	@Test(priority = 7)
	public void deleteElement() {
		List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));

		// Iterating through the list of buttons and deleting it one-by-one
		for (WebElement button : listOfDeleteButton) {
			button.click();
		}
	}
}
