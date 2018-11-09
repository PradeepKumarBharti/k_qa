package kipp_qa.com.test;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSElement;
import kipp_qa.com.common.BaseTest;
import kipp_qa.com.pageOR.HomePageOR;

public class HomepageTest extends BaseTest {
	@BeforeClass
	public void initIOSApp() {
		initIOSDriver();
	}

	@Test(description = "Navigate & verify Native View Screen page")
	public void verifyNativeViewScreen() {
		try {
			PageFactory.initElements(app, HomePageOR.class);
			WebElement user99GuruName = HomePageOR.user99GuruName;
			HomePageOR.user99GuruName.click();
			app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Native View\"]")).click();
			Thread.sleep(200);
			String expectedTest = app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"show native view\"]"))
					.getText();
			Assert.assertEquals("show native view", expectedTest);
			app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Kiip QA 3.0.0\"]")).click();
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(description = "Navigate & verify Table View Screen page")
	public void verifyTableViewScreen() {
		try {
			app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Tableview\"]")).click();
			Thread.sleep(200);
			String expectedTest = app.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Apple\"]")).getText();
			Assert.assertEquals("Apple", expectedTest);
			app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Kiip QA 3.0.0\"]")).click();
			IOSElement findElement = app.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Kiip QA 3.0.0\"]"));
			Thread.sleep(200);
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void test() {
		WebElement findElement = app.findElement(By.xpath("//button[@class='btn btn-xs btn-danger']"));
		new WebDriverWait(app, 500).withMessage("element is not visible").ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class).until(ExpectedConditions.visibilityOf(findElement));
		app.findElement(By.xpath("//button[@class='btn btn-xs btn-danger']")).click();
		
	}
}
