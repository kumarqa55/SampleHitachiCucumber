package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Hitachi_ContactUS_PO extends BasePage {
	
	Common_Function_PO cf = new Common_Function_PO();
	
	//Locators
	@FindBy(xpath="//a[@class='btn btn-primary' and text()='Contact us']")
	protected WebElement contactUS;
	
	String irvine_LinkText = "//a[contains(text(),'Irvine')]";
	
	
	
	
	public void launchHitachiApplication() {
		driver.get("https://global.hitachi-solutions.com/");
	}
	
	public void clickOnContactUS() {
		cf.clickFunctionButton(contactUS);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String verifyText() {
		return cf.getWebelementText(irvine_LinkText);
		
	}



}
