package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FilterCategoryPage;

import static org.testng.Assert.assertTrue;

public class FilterCategoryTest extends BaseTest{

	FilterCategoryPage filterCategoryPage;
	@BeforeMethod
	public void setUpFilterPage() {

		filterCategoryPage = new FilterCategoryPage(driver);
	}

	@Test(description = "Filter Women category products")
	public void filterCategoryTest() {

		assertTrue(filterCategoryPage.isListOfProductsFiltered(), "Products are not filtered!");
	}
}
