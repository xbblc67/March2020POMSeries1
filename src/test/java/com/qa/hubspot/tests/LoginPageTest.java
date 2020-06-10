package com.qa.hubspot.tests;



import org.testng.Assert;

import org.testng.annotations.Test;


import com.qa.hubspot.base.BaseTest;

import com.qa.hubspot.utils.Constants;

public class LoginPageTest extends BaseTest {



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


}
