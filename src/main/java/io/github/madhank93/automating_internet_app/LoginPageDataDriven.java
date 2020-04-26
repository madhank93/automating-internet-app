package io.github.madhank93.automating_internet_app;

import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginPageDataDriven {

	static WebDriver driver;
	static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";
	static XSSFRow row;
	static XSSFCell cell;
	static int columnIndex;
	static DataFormatter formatedData;
	static Select conDropdown;
	static String arrOfDOB[];
	static Random randomNum;
	static WebDriverWait wait;
	HashMap<Integer, String> result = new HashMap<Integer, String>();

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/login");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

	// method to wait until the element is clickable
	public static void elementToClickable(By expression) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(expression));
	}

	// Reading from and xlsx, passing it to login page and storing the result
	@SuppressWarnings("resource")
	@Test(priority = 1)
	public void login() throws IOException {
		// creating a input stream to the xlsx file

		InputStream excelFileToRead = new FileInputStream(System.getProperty("user.dir") + "//" + "test-data.xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(excelFileToRead); // accessing workbook
		XSSFSheet sheet = workBook.getSheetAt(0); // getting first sheet

		Iterator<Row> rows = sheet.iterator();

		// loop through all the rows in the xlsx file
		while (rows.hasNext()) {

			row = (XSSFRow) rows.next();
			Iterator<Cell> cells = row.iterator();

			if (row.getRowNum() == 0) {
				continue;// skip first row, as it contains column names
			}

			// loop through all the cell in the row
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				formatedData = new DataFormatter();
				columnIndex = cell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					// Username column
					elementToClickable(By.id("username"));
					driver.findElement(By.id("username")).sendKeys(formatedData.formatCellValue(cell));
					break;

				case 1:
					// Password column
					elementToClickable(By.id("password"));
					driver.findElement(By.id("password")).sendKeys(formatedData.formatCellValue(cell));
					break;
				}
			}
			// Clicking on login button
			elementToClickable(By.xpath("//form[@id='login']//button"));
			driver.findElement(By.xpath("//form[@id='login']//button")).click();

			String flashMessage = driver.findElement(By.id("flash")).getText();

			if (flashMessage.contains("secure")) {
				result.put(row.getRowNum(), "Passed");
			} else if (flashMessage.contains("invalid")) {
				result.put(row.getRowNum(), "Failed");
			}
		}
		// close the xlsx files
		excelFileToRead.close();
		workBook.close();
	}

	// Writing the results into the xlsx
	@SuppressWarnings("resource")
	@Test(priority = 2)
	public void writeLoginResult() throws IOException {

		File exfile = new File(System.getProperty("user.dir") + "//" + "test-data.xlsx");
		FileInputStream file = new FileInputStream(exfile);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		for (Map.Entry<Integer, String> entry : result.entrySet()) {
			Row row = sheet.getRow(entry.getKey());
			Cell cell = row.getCell(2);

			if (cell == null) {
				cell = row.createCell(2);
			}
			cell.setCellValue(entry.getValue());
		}
		file.close();
		FileOutputStream outFile = new FileOutputStream(exfile);
		workbook.write(outFile);
		outFile.close();
	}
}
