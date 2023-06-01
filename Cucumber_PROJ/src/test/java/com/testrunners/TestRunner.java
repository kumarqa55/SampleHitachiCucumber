package com.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/features", 
                     glue = {"com.stepdefnitions", "com.hooks"}, 
                   plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
                	tags = "@Hitachi",
               monochrome = true
               )
public class TestRunner {

	
}
                 