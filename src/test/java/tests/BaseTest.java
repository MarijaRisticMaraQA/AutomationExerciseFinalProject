package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

	ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		driver.set(DriverManager.getInstance().setDriver());
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		new Environment(driver.get()).goToEnvironment();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.get().quit();
		driver.remove();
	}
}
