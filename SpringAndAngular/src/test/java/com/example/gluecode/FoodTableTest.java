package com.example.gluecode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.page.FoodItemPage;
import com.example.page.FoodTablePage;
import com.example.page.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FoodTableTest {
	
	private LoginPage lp;
	private FoodTablePage ftp;
	private FoodItemPage fItemP;
	private String username;
	private String password;
	private String foodname;
	private String calories;
	
	
	@Given("The user is on the login page")
	public void the_user_is_on_the_login_page() {
		this.lp = new LoginPage(FoodUtilityDriver.driver);
		assertEquals("http://localhost:4200/login", FoodUtilityDriver.driver.getCurrentUrl());
	}

	@When("the user inputs {string} into the username field")
	public void the_user_inputs_into_the_username_field(String string) {
		this.username=string;
	}
	
	@When("the user inputs {string} in to password field")
	public void the_user_inputs_in_to_password_field(String string) {
		this.password=string;
	}
	@When("the user clicks login")
	public void the_user_clicks_login() {
		this.lp.loginToFoodPage(this.username, this.password);
		//Selenium waits
		/*
		 * Is a way to pause our application until an event occurs - ASYNCHRONOUS FUNCTIONS
		 * there are 3 types of waits:
		 * 		implicit wait - waits of a specified period of time
		 * 		explicit wait - wait for and condition to be true on our page
		 * 		flunent waits - it a conbination of the previous two, but we can also controll the rate in which we
		 * 						check our page for that condition to be true(polling rate)
		 */
		//this is an explicit wait
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5); //we will wait 5 seconds before a timeout
		//then it will throw an ElementNotFound Exceptions
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName("a")));//we are waiting for the a tags in our
		//table to be clickable, thus means our table has populated
		//if the table populated then we can create an instance of the the FoodTablePage POM
		this.ftp = new FoodTablePage(FoodUtilityDriver.driver);
	}
	
	@Then("the user is directed the food page")
	public void the_user_is_directed_the_food_page() {
		assertEquals("http://localhost:4200/food", FoodUtilityDriver.driver.getCurrentUrl());
	}
	
	@When("Page food Page Loads")
	public void page_food_page_loads() {
		//This is a redundant step implementation and probably could be removed
		assertEquals("http://localhost:4200/food", FoodUtilityDriver.driver.getCurrentUrl());
	}
	
	@Then("the user should see the All the food in the table.")
	public void the_user_should_see_the_all_the_food_in_the_table() {
		assertTrue(this.ftp.sizeOfTable()>1);
	}

	@When("The User inputs {string} into the foodName Field")
	public void the_user_inputs_into_the_food_name_field(String string) {
		this.foodname=string;
	}
	@When("The User inputs {string} into calories field")
	public void the_user_inputs_into_calories_field(String string) {
		this.calories=string;
	}
	@When("the User clicks submit food")
	public void the_user_clicks_submit_food() {
		this.ftp.createFood(this.foodname, this.calories);
	}
	@Then("the table should show the new food")
	public void the_table_should_show_the_new_food() {
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5); //wait for the table to be populated
		//this the new food
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '"+this.foodname+ "')]")));
		WebElement e = FoodUtilityDriver.driver.findElement(By.xpath("//a[contains(text(), '"+this.foodname+ "')]"));
		assertTrue(e!=null);
		
		//logic to delete the test data inserted by selenium
		e.click();
		wait = new WebDriverWait(FoodUtilityDriver.driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
		this.fItemP =  new FoodItemPage(FoodUtilityDriver.driver);
		this.fItemP.deleteFood();//this will clean up our test data, and delete our newly created food
	}
	
	@Then("the table should show food already exists")
	public void the_table_should_show_food_already_exists() {
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("foodExists")));
		assertEquals("FOOD ALREADY EXISTS", FoodUtilityDriver.driver.findElement(By.id("foodExists")).getText());
		
	}

}
