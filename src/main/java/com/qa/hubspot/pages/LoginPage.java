package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage {

	private WebDriver driver; // this driver is only for this loginpage and no can access it.
	//private ElementUtil elementUtil;
	// 1. By Locators
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");


	// 2. Create constructors of page class.
	public LoginPage(WebDriver driver) {
		this.driver = driver;	
		elementUtil = new ElementUtil(this.driver);
	}

	//3. page actions:
	public String getLoginPageTitle() {
		return	elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
		//return driver.getTitle();
	}

	public boolean verifySignUpLink() {
		elementUtil.waitForElementPresent(signUpLink, 5);
		return elementUtil.doIsDisplayed(signUpLink);
	}

	public HomePage doLogin(String username, String password) {  // use this keyword bcz variable name is same	
		//	driver.findElement(this.username).sendKeys(username);
		//	driver.findElement(this.password).sendKeys(password);
		//	driver.findElement(this.loginButton).click(); // after click it will give next landing page i.e. home page
		elementUtil.waitForElementPresent(this.username, 30);
		elementUtil.doSendKeys(this.username, username);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doClick(this.loginButton);
		return new HomePage(driver);
	}
}
