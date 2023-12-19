package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Integer.parseInt;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {

		super(driver);
	}

	private By firstProductAddToCart = By.cssSelector("[class='features_items'] a[data-product-id='1']:nth-child(4)");
	private By secondProductAddToCart = By.cssSelector("[class='features_items'] a[data-product-id='2']:nth-child(4)");
	private By continueShoppingButton = By.cssSelector(".close-modal");
	private By viewCartButton = By.cssSelector("a[href*='view_cart']");
	private By proceedToCheckoutButton = By.cssSelector(".col-sm-6 .check_out");
	private By singlePrice = By.cssSelector(".cart_price p");
	private By quantity = By.cssSelector(".disabled");
	private By productInCart = By.xpath("//tbody//tr");
	private By totalAmount = By.xpath("//tbody//tr[3]//td[4]");
	private By commentField = By.cssSelector(".form-control");
	private By paymentButton = By.cssSelector("a[href*='payment']");
	private By cardNameField = By.name("name_on_card");
	private By cardNumberField = By.cssSelector(".card-number");
	private By cvcField = By.cssSelector(".card-cvc");
	private By expirationMonthField = By.cssSelector(".card-expiry-month");
	private By expirationYearField = By.cssSelector(".card-expiry-year");
	private By payAndConfirmButton = By.cssSelector(".submit-button");
	private By confirmationMessage = By.cssSelector("#form .container p");

	public void addProductsInCart() {

		clickOnElement(firstProductAddToCart);
		clickOnElement(continueShoppingButton);

		clickOnElement(firstProductAddToCart);
		clickOnElement(continueShoppingButton);

		clickOnElement(secondProductAddToCart);

		clickOnElement(viewCartButton);
		clickOnElement(proceedToCheckoutButton);
	}

	public CartPage addCardDataForPayment() {

		typeInput(cardNameField, faker.name().name());
		typeInput(cardNumberField, faker.finance().creditCard());
		typeInput(cvcField, faker.number().digits(3));
		typeInput(expirationMonthField, "01");
		typeInput(expirationYearField, "3000");
		clickOnElement(payAndConfirmButton);

		return this;
	}

	private Integer totalPricesOfProducts(int i) {

		List<WebElement> prices = getListOfWebElements(singlePrice);
		List<WebElement> quantities = getListOfWebElements(quantity);

		int singleProductTotalPrice = Integer.parseInt(prices.get(i).getText().replace("Rs. ", ""));
		int quantityValue = Integer.parseInt(quantities.get(i).getText());

		return singleProductTotalPrice * quantityValue;
	}

	public boolean isTotalAmountInCartCorrect() {

		int sum = 0;

		List<WebElement> productsInCart = getListOfWebElements(productInCart);

		for (int i = 0; i < productsInCart.size()-1; i++) {
			sum += totalPricesOfProducts(i);
		}
		return parseInt(getElement(totalAmount).getText().replace("Rs. ", "")) == sum;
	}

	public void leaveCommentAndProceedPayment() {

		getElement(commentField).sendKeys("Hey Vladimir, you are awesome!");
		clickOnElement(paymentButton);
	}

	public boolean isPaymentSuccessful() {

		return matchesExpectedText(confirmationMessage, "Congratulations! Your order has been confirmed!");
	}
}
