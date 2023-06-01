package com.stepdefnitions;

import org.junit.Assert;

import com.pageobjects.Hitachi_ContactUS_PO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Hitachi_ContactUS_SD {

	Hitachi_ContactUS_PO hitachi = new Hitachi_ContactUS_PO();

	@Given("User Launches the Hitachi Application")
	public void user_launches_the_hitachi_application() {
		hitachi.launchHitachiApplication();
	}

	@When("User Click on Contact_US button")
	public void user_click_on_contact_us_button() {
		hitachi.clickOnContactUS();
	}

	@Then("User should able to navigate to the Contact_US page and Verify {string}")
	public void user_should_able_to_navigate_to_the_contact_us_page_and_verify(String expTitle) {
		String actTitle = hitachi.getTitle();
		Assert.assertEquals(expTitle, actTitle);
		System.out.println("The Title is : " + actTitle);
	}

	@Then("User Verifies {string} on the Contact_US page")
	public void user_verifies_on_the_contact_us_page(String expValue) {
		String actValue = hitachi.verifyText();
		Assert.assertTrue(actValue.contains(expValue));
		System.out.println("The Link Text Contains : " + expValue);
	}
}
