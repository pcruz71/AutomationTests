package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
	
	public By username = By.id("username");
	public By password = By.id("password");
	public By submit = By.id("submit");
	public By reset = By.linkText("Reset");
	public By register = By.linkText("Register");
	public By pre = By.tagName("pre");
	
	public LoginPage() {
		dvr.get("http://localhost:3000/login");
		isPageLoaded();
	}
		
	public void isPageLoaded() {
		waitForElementPresent(username);
	}
}
