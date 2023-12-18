package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

	RegisterPage registerPage;
	public LoginPage(WebDriver driver) {

		super(driver);
		registerPage = new RegisterPage(driver);
	}

	private By signInOutButton = By.cssSelector(".fa-lock");
	private By emailField = By.cssSelector("[data-qa='login-email']");
	private By passwordField = By.cssSelector("[data-qa='login-password']");
	private By loginButton = By.cssSelector("[data-qa='login-button']");
	private By loggedInUser = By.cssSelector("a :nth-child(2)");
	private By loginForm = By.cssSelector(".login-form");

	public LoginPage goToLoginForm() {

		clickOnElement(signInOutButton);

		return this;
	}

	public LoginPage loginUser(String email, String password) {

		typeInput(emailField, email);
		typeInput(passwordField, password);

		clickOnElement(loginButton);

		return this;
	}

	public void logoutUser() {

		clickOnElement(signInOutButton);
	}

	public String actualLoggedInUser() {

		return getElement(loggedInUser).getText();
	}

	public boolean isUrlUnchanged() {

		return driver.getCurrentUrl().equals("https://automationexercise.com/login");
	}

	public boolean isUserLoggedOut() {

		return wait.until(ExpectedConditions.presenceOfElementLocated(loginForm)).isDisplayed();
	}
}
