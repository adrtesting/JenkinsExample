package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavMenuPage {
	
	public WebDriver driver;
	
	public NavMenuPage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	//PageFactory
	//Vine cu anotari @FindBy() (este o prescurtare pentru driver.findElement())
	@FindBy(xpath="(//a[contains(@class, 'popup_login_link')])[1]") public WebElement loginLink;
	
	//FindBy este acelasi lucru cu: WebElement loginLink = driver.findElement(By.xpath("(//a[contains(@class, 'popup_login_link')])[1]")));
	//scopul Page Models este sa aiba elemente
	@FindBy(linkText = "CONTACTS") public WebElement contactsLink;
	@FindBy(linkText = "BLOG") public WebElement blogLink;
	@FindBy(linkText = "Post Formats") public WebElement postFormatsLink;
	@FindBy(linkText = "BOOKS") public WebElement shopLink;

	public void navigateTo(WebElement element) {
		// TODO Auto-generated method stub
		element.click();
		
	}
	
	public void hoverMenu(WebElement element) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	
}
