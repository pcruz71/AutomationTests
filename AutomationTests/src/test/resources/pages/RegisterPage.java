package pages;

public class RegisterPage extends BasePage {
	
	public RegisterPage() {
		isPageLoaded();
	}
		
	public void isPageLoaded() {
		waitForTextPresent("Already have an account?");
	}
}
