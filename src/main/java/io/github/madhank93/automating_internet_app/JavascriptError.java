package io.github.madhank93.automating_internet_app;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavascriptError {

	WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

		LoggingPreferences pref = new LoggingPreferences();
		pref.enable(LogType.BROWSER, Level.ALL);
		
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.LOGGING_PREFS, pref);
		
		driver = new ChromeDriver(options);

		driver.get("https://the-internet.herokuapp.com/javascript_error");
	}

	@AfterTest
	public void quit() {
		// driver.quit();
	}

	@Test
	public void captureAndPrintJSError() {
		
		LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
		  for (LogEntry error : jserrors) {
		   System.out.println(error.getMessage());
		  }

	}

}
