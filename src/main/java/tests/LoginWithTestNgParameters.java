package tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import utils.BaseTest;


public class LoginWithTestNgParameters extends BaseTest {

	@Parameters({"user", "pass"})
	@Test
	public void loginWithParameters(String user, String pass) {
		page.navMenu.navigateTo(page.navMenu.loginLink);
		page.loginPage.loginInApp(user, pass);
		page.loginPage.logoutFromApp();
		
	}
	
	
}