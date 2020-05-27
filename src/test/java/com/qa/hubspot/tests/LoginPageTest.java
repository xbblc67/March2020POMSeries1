package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class LoginPageTest {

	BasePage basepage;
	WebDriver driver;
	Properties prop;

	LoginPage loginpage;
	@BeforeTest
	public void setup() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);	
	}

	@Test(priority = 2)
	public void verifyLoginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title is not matched");
	}

	@Test(priority = 1)
	public void veifySignUpLinkTest() {
		Assert.assertTrue(loginpage.verifySignUpLink(), "signup list is not displayed...");
	}

	@Test(priority =3)
	public void loginTest() {
		loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
