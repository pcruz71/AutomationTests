package pages;

public class ResetPasswordPage extends BasePage {
		
	public ResetPasswordPage() {
		isPageLoaded();
	}
		
	public void isPageLoaded() {
		waitForTextPresent("Reset Password");
	}
}
