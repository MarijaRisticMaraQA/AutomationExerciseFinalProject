package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactFormPage;

@Listeners(TestListener.class)
public class ContactFormTest extends BaseTest{

	ContactFormPage contactFormPage;
	@BeforeMethod(alwaysRun = true)
	public void setUpContactForm() {

		contactFormPage = new ContactFormPage(driver.get());
	}

	@Test(description = "Fill in and send Contact form")
	public void sendContactFormTest() {

		contactFormPage.fillInContactForm();
		Assert.assertTrue(contactFormPage.isContactFormSent(), "Contact form is not sent!");
	}
}
