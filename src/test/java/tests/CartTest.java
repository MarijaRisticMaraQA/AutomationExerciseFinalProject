package tests;

import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.RegisterPage;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class CartTest extends BaseTest{

	CartPage cartPage;
	RegisterPage registerPage;

	@BeforeMethod(alwaysRun = true)
	public void setUpCart() {

		cartPage = new CartPage(driver.get());
		registerPage = new RegisterPage(driver.get());
	}

	@Test(description = "Add products to cart and finish the payment")
	public void cartTest() {

		registerPage.registerUser()
				.continueShopping();
		cartPage.addProductsInCart();
		assertTrue(cartPage.isTotalAmountInCartCorrect(), "Total amount is not correct!");
		cartPage.leaveCommentAndProceedPayment();
		cartPage.addCardDataForPayment();
		assertTrue(cartPage.isPaymentSuccessful(), "Payment is not successful!");
	}
}
