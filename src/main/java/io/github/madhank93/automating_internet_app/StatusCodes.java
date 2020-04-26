package io.github.madhank93.automating_internet_app;

import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StatusCodes {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/status_codes");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	// REST Assured is used to check status code
	@Test
	public void statusCode() {
		ArrayList<String> URL = new ArrayList<String>();
		int statuscode;

		// Get all the links on the page
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// Looping through all the element, finding URL link and adding it to the ArrayList
		for (WebElement link : links) {
			String href = link.getAttribute("href");
			URL.add(href);
		}

		statuscode = RestAssured.get(URL.get(2)).statusCode();
		Assert.assertEquals(statuscode, 200);
		
		statuscode = RestAssured.given().redirects().follow(false).when().get(URL.get(3)).statusCode();
		Assert.assertEquals(statuscode, 301);

		statuscode = RestAssured.get(URL.get(4)).statusCode();
		Assert.assertEquals(statuscode, 404);

		statuscode = RestAssured.get(URL.get(5)).statusCode();
		Assert.assertEquals(statuscode, 500);		
	}
}
