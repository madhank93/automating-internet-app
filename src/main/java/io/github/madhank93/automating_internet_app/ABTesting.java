package io.github.madhank93.automating_internet_app;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ABTesting {
	
	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir")+ "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/abtest");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test
    public void WithCookieAfterVisitingPage() {
		
        String headingText = driver.findElement(By.tagName("h3")).getText();
        assertTrue(headingText.contains("A/B Test"));
        
        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.navigate().refresh();
        headingText = driver.findElement(By.cssSelector("h3")).getText();
        assertTrue(headingText.contains("No A/B Test"));
    }
	
	@Test
    public void WithOptOutUrl() {
        driver.get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
        driver.switchTo().alert().dismiss();
        assertTrue(driver.findElement(By.cssSelector("h3")).getText().contains("No A/B Test"));
    }


}
