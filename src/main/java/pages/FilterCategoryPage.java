package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FilterCategoryPage extends BasePage{

	public FilterCategoryPage(WebDriver driver) {

		super(driver);
	}

	private By productsOnPage = By.cssSelector(".features_items .col-sm-4");
	private By openWomenCategory = By.cssSelector("a[href='#Women']");
	private By dressCategory = By.cssSelector("a[href='/category_products/1']");

	private void filterCategory() {

		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		clickOnElement(openWomenCategory);
		clickOnElement(dressCategory);
	}

	public boolean isListOfProductsFiltered() {

		List<WebElement> allProductsOnPage = getListOfWebElements(productsOnPage);
		int numberOfAllProducts = allProductsOnPage.size();

		filterCategory();

		List<WebElement> filteredProductsOnPage = getListOfWebElements(productsOnPage);
		int numberOfFilteredProducts = filteredProductsOnPage.size();

		return numberOfFilteredProducts < numberOfAllProducts;
	}
}
