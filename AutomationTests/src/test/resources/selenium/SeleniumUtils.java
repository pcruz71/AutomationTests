package selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import tests.BaseTest;
import utils.Log4J;

public class SeleniumUtils extends Log4J {

	WebDriver driver = Driver.getDriver();
	private static final int MAX_WAIT = 60;

	@SuppressWarnings("deprecation")
	public Wait<WebDriver> fluentWait() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(MAX_WAIT, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return wait;
	}

	public boolean waitForElementPresent(By locator) {
		this.waitForPageLoaded();
		try {			
			this.fluentWait().until(ExpectedConditions.presenceOfElementLocated(locator));					
			return true;
		} catch (Exception e) {		
			logger.error(BaseTest.getMethodName() + " - " + locator.toString() + " is not present.\n");			
		} 
		return false;
	}

	public boolean waitForTextPresent(String text) {
		this.waitForPageLoaded();
		try {
			this.fluentWait().until(ExpectedConditions
					.textToBePresentInElement(
							driver.findElement(By.tagName("body")), text));
			return true;
		} catch (Exception e) {
			logger.error(BaseTest.getMethodName() + " - " + text + " is not present.\n");			
		}
		return false;
	}

	public boolean waitForElementVisibility(By locator) {
		this.waitForPageLoaded();
		try {
			this.fluentWait().until(ExpectedConditions
					.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			logger.error(BaseTest.getMethodName() + " - " + locator.toString() + " is not visible.\n");			
		}
		return false;
	}
	
	public boolean waitForElementInvisibility(By locator) {
		this.waitForPageLoaded();
		try {
			this.fluentWait().until(ExpectedConditions
					.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			logger.error(BaseTest.getMethodName() + " - " + locator.toString() + " is visible.\n");			
		}
		return false;
	}

	public void sendKeys(By locator, String txt) {
		this.waitForElementVisibility(locator);
		try {
			driver.findElement(locator).clear();
		} catch (Exception e) {}					
		driver.findElement(locator).sendKeys(txt);		
	}

	public void click(By locator) {
		try {
			this.waitForElementVisibility(locator);
			driver.findElement(locator).click();										
		} catch (Exception e) {
			clickHiddenElement(locator);
		}
	}
	
	public void clickHiddenElement(By locator) {
		this.waitForElementPresent(locator);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].style.visibility='visible';", driver.findElement(locator));
		executor.executeScript("arguments[0].click();", driver.findElement(locator));		
	}

	public String getElementAttribute(By locator, String attribute) {
		waitForElementVisibility(locator);
		String elementAttribute = driver.findElement(locator).getAttribute(attribute);				
		return elementAttribute;
	}

	public boolean waitForPageLoaded() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		return fluentWait().until(expectation);
	}
	
	public void threadSleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTxt(By locator) {
		this.waitForElementVisibility(locator);
		return driver.findElement(locator).getText();
	}

	public boolean isEnabled(By locator) {
		this.waitForElementVisibility(locator);
		return driver.findElement(locator).isEnabled();	
	}
}
