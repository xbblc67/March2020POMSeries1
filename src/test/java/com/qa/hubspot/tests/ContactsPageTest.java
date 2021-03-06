package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest extends BaseTest {
	HomePage homepage;
	ContactsPage contactspage;
	
	@BeforeClass
	public void homeSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		contactspage = homepage.goToContactsPage();
	}
	
	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactspage.getContactsPageTitle();
		System.out.println("Title is" + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACT_SHEET_NAME);
		return data;
	}
	
	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void createContactTest(String email, String firstName, String lastName, String jobTitle){
		contactspage.createContact(email, firstName, lastName, jobTitle);

}
}
