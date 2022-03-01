package com.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoodItemPage {
	
	@FindBy(xpath="//p[@id='foodname-p']")
	private WebElement foodPara;
	@FindBy(xpath="//button")
	private WebElement deleteFoodButton;
	
	public FoodItemPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getFoodParaText() {
		return this.foodPara.getText();
	}
	
	public void deleteFood() {
		this.deleteFoodButton.click();
	}

}
