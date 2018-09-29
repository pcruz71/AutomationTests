package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.Driver;
import selenium.SeleniumUtils;

public abstract class BasePage extends SeleniumUtils {
	
	//common page objs shared among all the pages
	public WebDriver dvr = Driver.getDriver();
	public By h2 = By.tagName("h2");
	public By login = By.linkText("Login");
	public By home = By.linkText("Home");
	
    abstract public void isPageLoaded();   
}

