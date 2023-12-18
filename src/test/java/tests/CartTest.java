package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.RegisterPage;

import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest{

	CartPage cartPage;
	RegisterPage registerPage;

	@BeforeMethod
	public void setUpCartPage() {

		cartPage = new CartPage(driver);
		registerPage = new RegisterPage(driver);
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
