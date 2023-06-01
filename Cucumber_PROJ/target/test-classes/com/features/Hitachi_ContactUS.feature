@Hitachi
Feature: Hitachi Portal Page

  Background: 
    Given User Launches the Hitachi Application

  @Contact_US
  Scenario Outline: Hitachi Home Page
    When User Click on Contact_US button
    Then User should able to navigate to the Contact_US page and Verify "<Title>"
    Then User Verifies "<Input_Text1>" on the Contact_US page
    Then User Verifies "<Input_Text2>" on the Contact_US page

    Examples: 
      | Input_Text1 | Input_Text2   | Title                          |
      | Irvine     | United States | Contact Us – Hitachi Solutions |
