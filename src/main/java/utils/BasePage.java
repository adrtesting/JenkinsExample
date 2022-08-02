package utils;

import pages.*;

public class BasePage extends BaseTest {

	public NavMenuPage navMenu = new NavMenuPage(driver);
	public LoginPage loginPage = new LoginPage(driver);

}