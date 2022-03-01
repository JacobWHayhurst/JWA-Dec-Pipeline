package com.example.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoodTablePage {

	//xpath("//table[@id='myTable']/tr")
	@FindBy(xpath="//tr")
	private List<WebElement> foodTableRows;
	
	@FindBy(xpath="//input[@id='foodname']")
	private WebElement foodName;
	
	@FindBy(xpath="//input[@id='food-cal']")
	private WebElement foodCal;
	
	@FindBy(xpath="//button[@id='foodSubmit']")
	private WebElement foodSubmit;
	
	@FindBy(xpath="//a")
	private List<WebElement> foodAnchorTags;
	
	public FoodTablePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public int sizeOfTable() {
		return this.foodTableRows.size();
	}
	
	public void createFood(String foodname, String cal) {
		this.foodName.clear();
		this.foodCal.clear();
		this.foodName.sendKeys(foodname);
		this.foodCal.sendKeys(cal);
		this.foodSubmit.click();
	}
	
	public void clickLink(String foodname) {
		for(WebElement link: this.foodAnchorTags) {
			if(link.getText().equals(foodname)) {
				link.click();
			}
		}
	}

	public List<WebElement> getFoodAnchorTags() {
		return foodAnchorTags;
	}
	
	
	
}
