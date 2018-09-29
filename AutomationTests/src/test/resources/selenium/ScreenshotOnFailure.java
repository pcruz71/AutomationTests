package selenium;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import exceptionHandling.CustomException;
import tests.BaseTest;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ScreenshotOnFailure extends CustomException {
	
	StringWriter errors = new StringWriter();
	
	public String takeScreenShotOnFailure(ITestResult testResult, WebDriver driver, Method method) throws IOException {			
					
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String error = "";
		try {
			error = getStackTrace(testResult, method);
			logger.error(BaseTest.getMethodName() + " - " + error + "\n");				
		} catch (Exception e) {
			logger.error("EXCEPTION: getStackTrace(testResult, method);");
			e.printStackTrace(new PrintWriter(errors));				
			logger.error(errors);
		}
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = "failed." + method.getName() + "." + error + "." + timeStamp + ".png";
			if (fileName.length() > 256) {
				fileName = "failed." + method.getName() + "." + error + ".png";
			}
			FileUtils.copyFile(scrFile, new File("test-output/screenshots/" + fileName));
		} catch (Exception e) {
			logger.error("EXCEPTION: FileUtils.copyFile(scrFile, new File(\"test-output/screenshots/\" + fileName));");
		}			
		return error;
	}
}

