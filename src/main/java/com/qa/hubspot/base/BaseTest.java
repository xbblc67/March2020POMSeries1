package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.ElementUtil;

public class BaseTest {


	public BasePage basepage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginpage;
	public HomePage homepage;

	@BeforeTest
	public void setup() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);	
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}


