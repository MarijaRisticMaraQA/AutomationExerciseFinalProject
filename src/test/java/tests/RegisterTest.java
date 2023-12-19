package tests;

import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegisterPage;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest{

	RegisterPage registerPage;

	@BeforeMethod(alwaysRun = true)
	public void setUpRegister() {

		registerPage = new RegisterPage(driver.get());
	}

	@Test(description = "Register new user")
	public void registerTest() {

		registerPage.registerUser();
		assertTrue(registerPage.isUserRegistered(), "User is not registered!");
	}
}
