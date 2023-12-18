package tests;

import dataproviders.LoginDataProvider;
import model.LoginUserModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest{

	LoginPage loginPage;
	RegisterPage registerPage;
	@BeforeMethod
	public void setLoginPage() {

		loginPage = new LoginPage(driver);
		registerPage = new RegisterPage(driver);
	}

	@Test(description = "Login user and then logout - positive test")
	public void loginUserTest() {

		registerPage.registerUser();
		registerPage.continueAndLogoutUser();
		loginPage.loginUser(registerPage.getEmail(), registerPage.getPassword());

		String expectedLoggedInUser = registerPage.name;
		assertEquals(loginPage.actualLoggedInUser(), expectedLoggedInUser, "User name is not matching");

		loginPage.logoutUser();
		assertTrue(loginPage.isUserLoggedOut(), "User is not logged out!");
	}

	@Test(description = "Login with invalid credentials from JSON")
	public void loginTestFromJsonNegative() {

		List<LoginUserModel> list = Utils.getDataFromJson();
		for (int i = 0; i < list.size(); i++) {
			loginPage.goToLoginForm()
					.loginUser(list.get(i).getEmail(), list.get(i).getPassword());
		}
		assertTrue(loginPage.isUrlUnchanged(), "Url is changed!");
	}

	@Test(dataProvider = "loginProvider",
			dataProviderClass = LoginDataProvider.class,
			description = "Login with invalid credentials from data provider")
	public void loginTestDataProviderNegative(String email, String password) {

		loginPage.goToLoginForm()
				.loginUser(email, password);

		assertTrue(loginPage.isUrlUnchanged(), "Url is changed!");
	}
}
