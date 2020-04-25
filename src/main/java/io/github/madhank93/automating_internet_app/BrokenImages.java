package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BrokenImages {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/broken_images");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test
	public void statusCodeOfImage() {
		
		int statuscode;
		String imageURL;

		imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[1]"))
				.getAttribute("src");
		statuscode = RestAssured.get(imageURL).statusCode();
		Assert.assertEquals(statuscode, 404);

		imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[2]"))
				.getAttribute("src");
		statuscode = RestAssured.get(imageURL).statusCode();
		Assert.assertEquals(statuscode, 404);

		imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[3]"))
				.getAttribute("src");
		statuscode = RestAssured.get(imageURL).statusCode();
		Assert.assertEquals(statuscode, 200);
	}

}
