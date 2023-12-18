package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

import static org.testng.Assert.assertTrue;

public class RegisterTest extends BaseTest{

	RegisterPage registerPage;

	@BeforeMethod
	public void setUpRegister() {

		registerPage = new RegisterPage(driver);
	}

	@Test(description = "Register new user")
	public void registerTest() {

		registerPage.registerUser();
		assertTrue(registerPage.isUserRegistered(), "User is not registered!");
	}
}
