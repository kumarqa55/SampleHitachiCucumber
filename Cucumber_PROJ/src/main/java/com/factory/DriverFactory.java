package com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobjects.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This Method is used to Initialize the ThreadLocal Driver Based on the given
	 * browser
	 * @param browser
	 * @return
	 */
	
	
	public WebDriver init_GetWebDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver(options));
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
		} else {
			System.out.println("Please send the correct Browser Name :" + browser);
		}

		getDeviceDriver().manage().deleteAllCookies();
		getDeviceDriver().manage().window().maximize();
		
		BasePage.smallWait.set(new WebDriverWait(getDeviceDriver(), BasePage.SMALL_WAIT_TIME));
		BasePage.medWait.set(new WebDriverWait(getDeviceDriver(), BasePage.MEDIUM_WAIT_TIME));
		BasePage.longWait.set(new WebDriverWait(getDeviceDriver(), BasePage.LONG_WAIT_TIME));
		
		return getDeviceDriver();
	}

	/**
	 * This is used to get the Driver with Thread Local
	 * 
	 * @return
	 * @throws ExceptionInInitializerError
	 */
	public static synchronized WebDriver getDeviceDriver() throws ExceptionInInitializerError {
		if (tlDriver.get() != null)
			return tlDriver.get();
		throw new ExceptionInInitializerError("Browser Driver Not Initialized");
	}

	public static void setDriver(WebDriver inputDriver) {
		tlDriver.set(inputDriver);
	}

}
