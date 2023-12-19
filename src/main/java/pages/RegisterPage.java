package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

@Getter
public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {

		super(driver);
	}

	public String email;
	public String password;
	public String name;

	private By signInOutButton = By.cssSelector(".fa-lock");
	private By nameField = By.name("name");
	private By emailField = By.cssSelector("input[data-qa='signup-email']");
	private By signUpButton = By.cssSelector("button[data-qa='signup-button']");
	private By genderRadioButton = By.id("id_gender2");
	private By passwordField = By.id("password");
	private By dayOfBirth = By.id("days");
	private By monthOfBirth = By.id("months");
	private By yearOfBirthe = By.id("years");
	private By firstName = By.id("first_name");
	private By lastName = By.id("last_name");
	private By address = By.id("address1");
	private By country = By.id("country");
	private By state = By.id("state");
	private By city = By.id("city");
	private By zipcode = By.id("zipcode");
	private By mobileNumber = By.id("mobile_number");
	private By createAccButton = By.cssSelector("[data-qa='create-account']");
	private By accountCreatedTitle = By.cssSelector("[data-qa='account-created']");
	private By continueButton = By.cssSelector("[data-qa='continue-button']");

	public RegisterPage registerUser() {

		email = faker.internet().emailAddress();
		password = faker.internet().password();
		name = faker.name().name();

		clickOnElement(signInOutButton);

		typeInput(nameField, name);
		typeInput(emailField, email);

		clickOnElement(signUpButton);

		clickOnElement(genderRadioButton);
		typeInput(passwordField, password);
		selectDateOfBirth();
		typeInput(firstName, faker.name().firstName());
		typeInput(lastName, faker.name().lastName());
		typeInput(address, faker.address().streetAddress());
		selectCountry();
		typeInput(state, faker.address().state());
		typeInput(city, faker.address().city());
		typeInput(zipcode, faker.address().zipCode());
		typeInput(mobileNumber, faker.phoneNumber().cellPhone());

		clickOnElement(createAccButton);

		return this;
	}

	private void selectDateOfBirth() {

		Select daySelect = new Select(getElement(dayOfBirth));
		daySelect.selectByVisibleText("1");

		Select monthSelect = new Select(getElement(monthOfBirth));
		monthSelect.selectByVisibleText("January");

		Select yearSelect = new Select(getElement(yearOfBirthe));
		yearSelect.selectByVisibleText("2000");
	}

	private void selectCountry() {

		Select countrySelect = new Select(getElement(country));
		countrySelect.selectByVisibleText("Canada");
	}

	public void continueShopping() {

		clickOnElement(continueButton);
	}

	public void continueAndLogoutUser() {

		clickOnElement(continueButton);
		clickOnElement(signInOutButton);
	}

	public boolean isUserRegistered() {

		return matchesExpectedText(accountCreatedTitle, "ACCOUNT CREATED!");
	}
}
