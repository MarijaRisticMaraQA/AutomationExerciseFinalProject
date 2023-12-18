package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactFormPage extends BasePage{

	public ContactFormPage(WebDriver driver) {

		super(driver);
	}

	private By contactUsButton = By.cssSelector("a[href='/contact_us']");
	private By nameInputField = By.cssSelector("[data-qa='name']");
	private By emailInputField = By.cssSelector("[data-qa='email']");
	private By subjectInputField = By.cssSelector("[data-qa='subject']");
	private By messageInputField = By.id("message");
	private By uploadFileButton = By.cssSelector("[name='upload_file']");
	private By submitButton = By.cssSelector("[data-qa='submit-button']");
	private By expectedSentContactFormText = By.cssSelector(".contact-form .alert-success");

	public ContactFormPage fillInContactForm() {

		clickOnElement(contactUsButton);

		typeInput(nameInputField, faker.name().name());
		typeInput(emailInputField, faker.internet().emailAddress());
		typeInput(subjectInputField, "Automation Selenium Java 2023");
		typeInput(messageInputField, "This course was great, thank you a lot!");

		WebElement chooseFile = getElement(uploadFileButton);
		chooseFile.sendKeys(System.getProperty("user.dir") + "/src/test/resources/Java.png");

		clickOnElement(submitButton);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		return this;
	}

	public boolean isContactFormSent() {

		return matchesExpectedText(expectedSentContactFormText, "Success! Your details have been submitted successfully.");
	}
}
