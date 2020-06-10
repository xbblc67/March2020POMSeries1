package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author pankaj
 *
 */

public class BasePage {
	WebDriver driver;
	public Properties prop; // properties is a class present in java.util
	public ElementUtil elementUtil;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the webdriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */
//tlDriver is for thread local i.e. for pdf and extend report.
	public WebDriver init_driver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//	driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			//	driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		/*
		 * // driver.manage().deleteAllCookies(); driver.manage().window().maximize();
		 * // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // Its
		 * define at global level driver.get(prop.getProperty("url")); return driver;
		 */
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();

		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public Properties init_prop() {

		/**
		 * this method is used to initialize the property from config.property file on the basis of environment variable
		 * returns prop
		 */
		prop = new Properties();
		String path = null;
		String env = null;
		try { // if the file is not found it will throw file not found exception error.
			env = System.getProperty("env");
			if (env == null) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
			}
			else {
				switch (env) {
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
					break;
				case "dev":
					path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
					break;
				case "stage":
					path = "./src/main/java/com/qa/hubspot/config/config.properties";
					break;
					default:
						System.out.println("please pass the correct environment");
						break;
				}
			}
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/" + System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;


	}
}
