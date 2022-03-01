package com.example.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class FoodUtilityDriver {
	//This utility class is utilized to configure the webdriver, and set up and pre-test configureations
	//and after test tear downs
	
	public static WebDriver driver;
	
	@Before
	public void setUp() {
														//if using mac, delete the .exe off the driver
														//also have to in the terminal run the chmod 777 chromedriver
														//to make it executable
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200");
	}
	
	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}
