package tests;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = DriverManager.setDriver("chrome");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		driver.get("https://automationexercise.com/");
	}
}
