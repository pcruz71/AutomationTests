package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import selenium.Driver;
import selenium.ScreenshotOnFailure;
import testng.Retry;
import utils.Log4J;

public class BaseTest {

	protected final static Logger logger = Logger.getLogger(Log4J.class);
	private static ThreadLocal<String> methodName = new ThreadLocal<String>();

	int failCount = 0;

	public static String getMethodName() {
		return methodName.get();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {		
		File dir = new File("test-output");
		try {
			FileUtils.deleteDirectory(dir);
		} catch (IOException e) {
			logger.error("error deleting test-output dir");
		}
		new File("test-output/log4j-application.log");
		org.apache.log4j.PropertyConfigurator.configure("src/test/resources/log4j.properties");
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })			
	public void setup(String browser, Method method) throws IOException {
		
		methodName.set(method.getName());
		Driver.createDriver(browser);
		new LoginPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestContext context, ITestResult result, Method method) throws IOException {

		if (Retry.retryCount == Retry.MAXRETRYCOUNT && result.getStatus() == ITestResult.FAILURE) {

			ScreenshotOnFailure ss = new ScreenshotOnFailure();
			@SuppressWarnings("unused")
			String error = null;
			error = ss.takeScreenShotOnFailure(result, Driver.getDriver(), method);			
			failCount++;			
		}		
		
		if (Driver.getDriver() != null) {
			Driver.getDriver().quit();
		}		
	}
}
