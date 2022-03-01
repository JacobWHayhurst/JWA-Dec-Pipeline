package com.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	/*
	 * xpath stands for XML path languange
	 * 	it is an expression format to find a path to identify nodes in an XML document
	 * 
	 *	/ -> stands for the root node, for an HTML doc <html></html>
	 *	// -> it try to find a node from the root node that matches the selector thar proceeds it
	 *	//p -> this will try to find a paragraph tag throughout our html document.
	 *		@ -> select based of an attirbute of an html tag
	 *		//@class='myClass'
	 *	//input[@id='password']  -> find an input tag with an id attribute of 'password'
	 *	//*[@name='myInput'] -> * is a wildcard for any tag
	 */
	
	@FindBy(xpath="//input[@id='username']")
	private WebElement username;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement password;
	
	@FindBy(xpath="//button[@id='loginButton']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToFoodPage(String username, String password) {
		this.username.clear();
		this.password.clear();
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
	}
	
	

}
