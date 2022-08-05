package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseTest {
	
	//public static WebDriver driver;
	
	public BasePage page;
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browser) {
		
//		FirefoxOptions options = new FirefoxOptions();
//		options = webdriver.FirefoxOptions();
//		options.binary_location = r "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Mozilla Firefox";
//		driver = webdriver.Firefox(executable_path=r'C:\WebDrivers\geckodriver.exe', options=options);
		
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
		
		ChromeOptions option = new ChromeOptions();
		//option.addArguments("---headless");
		//option.addArguments("start-maximized");
		option.setExperimentalOption("useAutomationExtension", true);
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		//firefoxBinary.addCommandLineOptions("---headless");
		FirefoxOptions foption = new FirefoxOptions();
		foption.setBinary(firefoxBinary);
		
		
		//ChromeOptions options = new ChromeOptions();
		
		//System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
		//driver = new ChromeDriver(options);
		
		if(browser !="" && browser != null) {
			if(browser.equalsIgnoreCase("chrome")) {
				
				driver.set(new ChromeDriver(option));
				
			} else if(browser.equalsIgnoreCase("firefox")) {
				
				driver.set(new FirefoxDriver(foption));
			}
		}
		
		
		
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get().get("https://keybooks.ro");
		//driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		page = new BasePage();
	
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.get().quit();
	}
	
	@AfterMethod
	public void recordFailure(ITestResult method) throws IOException {

		if(ITestResult.FAILURE == method.getStatus()) {
			TakesScreenshot poza =  (TakesScreenshot)driver;
			File picture = poza.getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			Files.copy(picture, new File("screenshots/"+method.getName() + "-"+ timeStamp + ".png"));

		}

	}
	
	

}