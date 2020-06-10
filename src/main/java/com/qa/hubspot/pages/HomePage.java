package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;
    //private ElementUtil elementUtil;

//	By header = By.cssSelector("h1.private-header__heading private-header__heading--solo");
	By header = By.cssSelector("//i18n-string[text()='Dashboard Library']");
	By accountName = By.cssSelector("span.account-name ");
	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE,10);
	}

	public String getHomePageHeaderText()  {
		//	if((driver.findElement(header)).isDisplayed()) {
//	    Thread.sleep(2000);
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;  
	//	elementUtil.waitForElementToBeVisible(header, 10);
	//	return elementUtil.doGetText(header);
	}

	public String getLoggedInUser() {
		//	if ((driver.findElement(accountName)).isDisplayed()) {
		//		return driver.findElement(accountName).getText();
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}

	public ContactsPage goToContactsPage() {
		clickOnContacts(); // doing encapusulation not giving exposore to external users.
		return new ContactsPage(driver);
	}

	private void clickOnContacts() {
		elementUtil.waitForElementPresent(primaryContactLink, 10);
		elementUtil.doClick(primaryContactLink);
		elementUtil.waitForElementPresent(secondaryContactLink, 10);
		elementUtil.doClick(secondaryContactLink);
	}


}
