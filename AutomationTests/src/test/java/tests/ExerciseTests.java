package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.ResetPasswordPage;

public class ExerciseTests extends BaseTest {
	
	@Test(groups = { "Navigation" }, enabled = true)
	public void verifyHomeLinks() {
		LoginPage lp = new LoginPage();
		lp.click(lp.home);

		HomePage hp = new HomePage();		
		Assert.assertTrue(hp.getTxt(hp.h2).equals("Home"));
		
		hp.click(hp.home);
		Assert.assertTrue(hp.getTxt(hp.h2).equals("Home"));
		
		hp.click(hp.login);
		Assert.assertTrue(lp.waitForElementVisibility(lp.username));
		lp.navigateBack();
		
		hp.click(hp.register);
		RegisterPage rp = new RegisterPage();
		Assert.assertTrue(rp.getTxt(rp.h2).equals("Register"));
		lp.navigateBack();
		
		hp.click(hp.forgotPassword);
		ResetPasswordPage pp = new ResetPasswordPage();
		Assert.assertTrue(pp.getTxt(pp.h2).equals("Reset Password"));		
	}
	
	@Test(groups = { "Navigation" }, enabled = true)
	public void verifyLoginPageLinks() {
		LoginPage lp = new LoginPage();
		Assert.assertTrue(lp.getTxt(lp.h2).equals("Login"));
		Assert.assertTrue(lp.waitForElementVisibility(lp.username));
		Assert.assertTrue(lp.waitForElementVisibility(lp.password));
		Assert.assertTrue(lp.waitForElementVisibility(lp.submit));
		Assert.assertFalse(lp.isEnabled(lp.submit));
		Assert.assertTrue(lp.waitForTextPresent("Until the user provides a non-empty User Name and Password, the Login button is disabled."));
		Assert.assertTrue(lp.waitForTextPresent("When the Login button is clicked, a <pre> element containing the submitted User Name and Password is rendered."));
		Assert.assertTrue(lp.waitForTextPresent("Whenever the user modifies the User Name or the Password, the <pre> element is removed from the page."));
		
		lp.click(lp.reset);
		ResetPasswordPage rp = new ResetPasswordPage();
		Assert.assertTrue(rp.getTxt(rp.h2).equals("Reset Password"));
		lp.navigateBack();

		lp.click(lp.register);
		RegisterPage rg = new RegisterPage();
		Assert.assertTrue(rg.getTxt(rg.h2).equals("Register"));
		lp.navigateBack();
		
		lp.click(lp.home);
		HomePage hp = new HomePage();
		Assert.assertTrue(hp.getTxt(hp.h2).equals("Home"));
	}
	
	@Test(groups = { "Navigation" }, enabled = true)
	public void verifyRegisterPageLinks() {
		LoginPage lp = new LoginPage();
		lp.click(lp.register);

		RegisterPage rp = new RegisterPage();
		Assert.assertTrue(rp.getTxt(rp.h2).equals("Register"));
		Assert.assertTrue(rp.waitForTextPresent("Already have an account?"));
		
		rp.click(rp.login);
		Assert.assertTrue(lp.getTxt(lp.h2).equals("Login"));
		lp.navigateBack();
		
		rp.click(rp.home);
		HomePage hp = new HomePage();
		Assert.assertTrue(hp.getTxt(hp.h2).equals("Home"));
	}
	
	@Test(groups = { "Navigation" }, enabled = true)
	public void verifyResetPageLinks() {
		LoginPage lp = new LoginPage();
		lp.click(lp.reset);

		ResetPasswordPage rp = new ResetPasswordPage();
		Assert.assertTrue(rp.getTxt(rp.h2).equals("Reset Password"));
		Assert.assertTrue(rp.waitForTextPresent("Remembered your password?"));
		
		rp.click(rp.login);
		Assert.assertTrue(lp.getTxt(lp.h2).equals("Login"));
		lp.navigateBack();
		
		rp.click(rp.home);
		HomePage hp = new HomePage();
		Assert.assertTrue(hp.getTxt(hp.h2).equals("Home"));
	}
	
	@Test(groups = { "Form" }, enabled = true)
	public void verifyLoginFormSubmitBtnDisabled() {
		LoginPage lp = new LoginPage();
		Assert.assertTrue(lp.getElementAttribute(lp.username, "value").equals(""));
		Assert.assertTrue(lp.getElementAttribute(lp.password, "value").equals(""));
		Assert.assertFalse(lp.isEnabled(lp.submit));
	}

	@Test(groups = { "Form" }, enabled = true)
	public void verifyWhenLoginBtnClickedLoginInfoRendered() {
		LoginPage lp = new LoginPage();
		lp.login("username", "password");
		Assert.assertTrue(lp.getTxt(lp.pre).replace("\n", " ").equals("Username: username Password: password"));
	}

	@Test(groups = { "Form" }, enabled = true)
	public void verifyPreElementRemovedWhenLoginInfoModified() {
		LoginPage lp = new LoginPage();
		lp.login("username", "password");
		lp.sendKeys(lp.username, "username1");
		Assert.assertTrue(lp.waitForElementInvisibility(lp.pre));
		lp.sendKeys(lp.username, "username");
		lp.sendKeys(lp.password, "password1");
		Assert.assertTrue(lp.waitForElementInvisibility(lp.pre));
	}
}
