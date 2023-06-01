package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Common_Function_PO extends BasePage {

	/**
	 * Enter Text in Text box
	 * 
	 * @param element
	 * @param str
	 */
	public void sendKeys(WebElement element, String str) {
		try {
			explicitWait(element);
			longWait.get().until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(str);
		} catch (StaleElementReferenceException ser) {
			longWait.get().until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(str);
		}
	}

	public void sendKeysXpath(String xpath, String text) {
		try {
			explicitWaitXpath(xpath);
			longWait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			longWait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(text);
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + xpath + " | Error - " + nse);
		} catch (StaleElementReferenceException ser) {
			sendKeysXpath(xpath, text);
		}
	}

	/**
	 * Click Functionality
	 */
	public void clickFunctionButton(WebElement element) {

		try {
			explicitWait(element);
			longWait.get().until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (StaleElementReferenceException ser) {
			longWait.get().until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + element + " | Error- " + nse);
		}
	}

	public void clickFunctionXpath(String xpath) {
		try {
			explicitWaitXpath(xpath);
			longWait.get().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + xpath + " | Error - " + nse);
		} catch (StaleElementReferenceException sre) {
			clickFunctionXpath(xpath);
		}
	}

	/**
	 * To get WebElement Text <label id="message"> Customer ID </label> getText will
	 * give text = Customer ID
	 * 
	 * @param xpath
	 * @return
	 */
	public String getWebelementText(String xpath) {
		String text = null;
		try {
			explicitWaitXpath(xpath);
			text = longWait.get().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + xpath + " | Error - " + nse);
		} catch (StaleElementReferenceException sre) {
			getWebelementText(xpath);
		}
		return text;

	}

	/**
	 * To get Attribute Text <label id="message"> Customer ID </label> getText will
	 * give Attribute text of id = message
	 * 
	 * @param xpath
	 * @return
	 */
	public String getAttributeText(String xpath, String attribute) {
		String text = null;
		try {
			explicitWaitXpath(xpath);
			text = longWait.get().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)))
					.getAttribute(attribute);
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + xpath + " | Error - " + nse);
		} catch (StaleElementReferenceException sre) {
			getWebelementText(xpath);
		}
		return text;
	}

}
