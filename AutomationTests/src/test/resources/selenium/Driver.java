package selenium;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

	private static ThreadLocal<RemoteWebDriver> threadDvr = new ThreadLocal<RemoteWebDriver>();

	public static WebDriver createDriver(String browser) throws MalformedURLException {

		browser = browser != null ? browser : "firefox";

		if (browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "src/test/resources/selenium/geckodriver");
			threadDvr.set(new FirefoxDriver());

		} else if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "src/test/resources/selenium/chromedriver");
			threadDvr.set(new ChromeDriver());
		}        
		return threadDvr.get();
	}

	public static RemoteWebDriver getDriver() {
		return threadDvr.get();
	}
}
