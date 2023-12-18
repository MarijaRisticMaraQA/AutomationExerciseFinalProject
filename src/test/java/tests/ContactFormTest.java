package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactFormPage;

public class ContactFormTest extends BaseTest{

	ContactFormPage contactFormPage;
	@BeforeMethod
	public void setUpContactFormPage() {

		contactFormPage = new ContactFormPage(driver);
	}

	@Test(description = "Fill in and send Contact form")
	public void sendContactFormTest() {

		contactFormPage.fillInContactForm();
		Assert.assertTrue(contactFormPage.isContactFormSent(), "Contact form is not sent!");
	}
}
