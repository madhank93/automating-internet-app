package io.github.madhank93.automating_internet_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Hovers {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/hovers");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void mouseHover() {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//div[@class='example']//div[3]//img[1]"));
		actions.moveToElement(element).build().perform();
        
        String hoverMessage = driver.findElement(By.xpath("//h5[contains(text(),'name: user3')]")).getText();
        Assert.assertEquals(hoverMessage, "name: user3");
	}
}
