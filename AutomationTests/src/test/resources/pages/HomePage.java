package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
	
	public By homeHeader = By.xpath("//h2[.='Home']");
	public By register = By.linkText("Register");
	public By forgotPassword = By.linkText("Forgot Password");
	
	public HomePage() {
		isPageLoaded();
	}
		
	public void isPageLoaded() {
		this.waitForElementVisibility(homeHeader);		
	}
}
