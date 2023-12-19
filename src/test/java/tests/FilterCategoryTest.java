package tests;

import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FilterCategoryPage;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class FilterCategoryTest extends BaseTest{

	FilterCategoryPage filterCategoryPage;
	@BeforeMethod(alwaysRun = true)
	public void setUpFilter() {

		filterCategoryPage = new FilterCategoryPage(driver.get());
	}

	@Test(description = "Filter Women category products")
	public void filterCategoryTest() {

		assertTrue(filterCategoryPage.isListOfProductsFiltered(), "Products are not filtered!");
	}
}
