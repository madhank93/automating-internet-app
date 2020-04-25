package io.github.madhank93.automating_internet_app;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileDownloader {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		
		Map<String, Object> preferences = new Hashtable<String, Object>();
		preferences.put("download.default_directory", System.getProperty("user.dir"));
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preferences);
		
		driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/download");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test (priority =  1)
	public void downlodFile() {
		driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).click();
	}
	
	@Test (priority =  2)
	public void verifyFileExists() throws InterruptedException {
		String filename = driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).getText();
		
		File file = new File(System.getProperty("user.dir") + "/" + filename);
		
		Thread.sleep(5000);
		
		Assert.assertEquals(file.exists(), true);
	}
	
	
	
	
}
