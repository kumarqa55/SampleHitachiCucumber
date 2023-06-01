package com.hooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.factory.DriverFactory;
import com.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class HookDefnitions {

	private DriverFactory df;
	private WebDriver driver;
	private ConfigReader reader;

	Properties prop;
	ExtentReports extentReport;
	ExtentTest extentTest;

	/**
	 * @Before will follow Ascending Order
	 */
	@Before(order = 0)
	public void getProperty() {
		reader = new ConfigReader();
		prop = reader.init_Properties();

	}

	@Before(order = 1)
	public void launchBrowser() {
		df = new DriverFactory();
		String browser = prop.getProperty("browser");
		driver = df.init_GetWebDriver(browser);
	}

	/**
	 * @After will follow Descending Order
	 * @param scn
	 */
	@After(order = 1)
	public void tearBrowser(Scenario scn) {
		if (scn.isFailed()) {
			String scrnName = scn.getName().replaceAll(" ", "_");
			byte[] scrnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scn.attach(scrnShot, "image/png", scrnName);
		}
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}
}
