package com.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.factory.DriverFactory;

public class BasePage {

	/**
	 * Timeout for each Webdriver object in seconds
	 */
	protected WebDriver driver;

	public static final int SMALL_WAIT_TIME = 10;
	public static final int MEDIUM_WAIT_TIME = 30;
	public static final int LONG_WAIT_TIME = 50;

	public static ThreadLocal<WebDriverWait> smallWait = new ThreadLocal<WebDriverWait>();
	public static ThreadLocal<WebDriverWait> medWait = new ThreadLocal<WebDriverWait>();
	public static ThreadLocal<WebDriverWait> longWait = new ThreadLocal<WebDriverWait>();

	public BasePage() {
		try {

			driver = DriverFactory.getDeviceDriver();
			// PageFactory.initElements(driver, this);

			AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
			PageFactory.initElements(factory, this);

		} catch (ExceptionInInitializerError e) {
			System.out.println("Exception Occured while loading BasePage.Driver [" + e.getMessage() + "]");
		}
	}


	/**
	 * KeyBoard Enter Functionality
	 * 
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void keyBoardEnter() throws InterruptedException, AWTException {
		Thread.sleep(3000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * Navigate To Page Functions
	 * 
	 * @param url
	 */
	public void openApplication(String url) {
		driver.get(url);
		waitForPageLoad(driver);
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void clickBrowserBackButton() {
		driver.navigate().back();
	}

	/**
	 * Scroll Down Functionality
	 * 
	 * @param ele
	 */

	public void scrollElementIntoView(WebElement ele) {
		String scrollView = "window.scrollBy(0,arguments[0].getBoundingClientRect().top-(window.innerHeight/2));";
		((JavascriptExecutor) driver).executeScript(scrollView, ele);
	}

	public void scrollElementIntoView1(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", ele);
	}

	public void safeJavaScriptClick(WebElement element) {
		explicitWait(element);
		try {
			scrollElementIntoView(element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click;", element);
		} catch (Exception e) {
			System.out.println("Not Able to Click on : " + element);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Wait Functionality
	 * 
	 * @param element
	 */

	public void waitForPageLoad1(WebDriver driver) {
		ExpectedCondition<Boolean> pgLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (UnsupportedCommandException un) {
					return false;
				}
			}
		};
		WebDriverWait webWait = new WebDriverWait(driver, 90);
		webWait.until(pgLoad);
	}

	public void waitForPageLoad(WebDriver driver) {
		Date d1 = new Date();
		boolean flag = true;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			while (flag) {
				if (js.executeScript("return.document.readyState").toString().equals("complete")) {
					break;
				}
				Date d2 = new Date();
				long diff = d2.getTime() - d1.getTime();
				long diffSec = diff / 1000 % 60;
				if (diffSec == 40) {
					System.out.println("Exceeded the Page Loading Time : " + diffSec);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void explicitWait(WebElement element) {
		// waitForPageLoad(driver);

		WebDriverWait wd_Wait = new WebDriverWait(driver, 40);
		wd_Wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void explicitWaitXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	public String getRefClassVar(String classNM, String fieldNM) throws Exception {
		Class<?> className = Class.forName("com.pageobjects." + classNM);
//		Method method = className.getMethod(fieldNM);
//		String value = (String)method.invoke(method);
		Field field = className.getDeclaredField(fieldNM);
		String value = (String) field.get(className);
		return value;
	}

}
