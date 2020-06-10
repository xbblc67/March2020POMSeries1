package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{

	HomePage homepage;

	@BeforeClass
	public void homeSetup() {
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		homepage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test(priority = 3)
	public void verifyHomePageTitle() {
		String Title = homepage.getHomePageTitle();
		System.out.println("title is " + Title);
		Assert.assertEquals(Title,Constants.HOME_PAGE_TITLE, "title is not matched");
	}

	@Test(priority = 1)
	public void verifyhomePageHeaderText(){
		String header = homepage.getHomePageHeaderText();
		System.out.println("Header is" + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER, "header is not present");
	}

	@Test(priority = 2)
	public void verifyLoggedInUser() {
		String loggedInUser = homepage.getLoggedInUser();
		System.out.println("Loggedin user is" + loggedInUser);
		Assert.assertEquals(loggedInUser,prop.getProperty("accountname"), "loggedinuser is not matched");
	}
}
