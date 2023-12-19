package pages;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	Faker faker;
	private static final Logger log = LogManager.getLogger(BasePage.class.getName());
	private long waitTime = Long.parseLong(Utils.dotEnv().get("EXPLICIT_WAIT_TIME"));

	public BasePage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		faker = new Faker();
	}

	protected WebElement getElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected List<WebElement> getListOfWebElements(By locator) {

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	protected void typeInput(By locator, String text) {

		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	protected void hoverAndClick(By locator, long wait) {

		WebElement element = getElement(locator);
		new Actions(driver)
				.moveToElement(element)
				.pause(wait)
				.click()
				.build()
				.perform();
	}

	protected void clickOnElement(By locator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (ElementClickInterceptedException e) {
			js.executeScript("arguments[0].click()", getElement(locator));
		} catch (StaleElementReferenceException s) {
			s.printStackTrace();
			hoverAndClick(locator, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean matchesExpectedText(By locator, String expectedText) {

		WebElement element = getElement(locator);

		if (element.getText().trim().equals(expectedText)) {
			log.info("PASSED - Text found in element: " + element.getText() + " is matching with expected text: " + expectedText);
			return true;
		} else {
			log.info("FAILED - Text found in element: " + element.getText() + " is not matching with expected text: " + expectedText);
		}
		return false;
	}
}
