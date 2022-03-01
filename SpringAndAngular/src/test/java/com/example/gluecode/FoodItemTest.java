package com.example.gluecode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.page.FoodItemPage;
import com.example.page.FoodTablePage;
import com.example.page.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FoodItemTest {
	
	private LoginPage lp;
	private FoodTablePage ftp;
	private FoodItemPage fip;
	private String foodName;
	private String calories;
	
	@When("The user inputs {string} into the food name field")
	public void the_user_inputs_into_the_food_name_field(String string) {
		this.foodName=string;
	}

	@When("The user inputs {string} into the calorie input field")
	public void the_user_inputs_into_the_calorie_input_field(String string) {
		this.calories=string;
	}
	@When("the user clicks submit new food")
	public void the_user_clicks_submit_new_food() {
		this.ftp = new FoodTablePage(FoodUtilityDriver.driver);
		this.ftp.createFood(this.foodName, this.calories);
	}
	@When("the new food appears on the table the user can click the {string} link")
	public void the_new_food_appears_on_the_table_the_user_can_click_the_link(String string) {
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+this.foodName+"')]")));
		this.ftp.clickLink(this.foodName);
	}
	@When("the user is on the Test food item page")
	public void the_user_is_on_the_test_food_item_page() {
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
		this.fip = new FoodItemPage(FoodUtilityDriver.driver);
		System.out.println(FoodUtilityDriver.driver.getCurrentUrl());
		assertEquals(this.fip.getFoodParaText(), this.foodName);
	}
	
	@When("the user clicks the delete food button")
	public void the_user_clicks_the_delete_food_button() {
		this.fip.deleteFood();
	}
	
	@Then("the user will taken to the Food table page with out the Test food")
	public void the_user_will_taken_to_the_food_table_page_with_out_the_test_food() {
		WebDriverWait wait = new WebDriverWait(FoodUtilityDriver.driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName("a")));
		try {
			WebElement e = FoodUtilityDriver.driver.findElement(By.xpath("//a[contains(text(),'"+this.foodName+"')]"));
			assertTrue(false);
		} catch(org.openqa.selenium.NoSuchElementException e){
			assertTrue(true);
		}
	}

}
