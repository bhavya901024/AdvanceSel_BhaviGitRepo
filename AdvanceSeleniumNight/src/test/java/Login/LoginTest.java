package Login;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;

public class LoginTest {
	@Parameters("browser")
	@Test(groups={"SmokeTest"})
public void loginTest(String browser) throws InterruptedException, IOException {
	//reading data from Generic UTility - PropertiesFileUtility
		PropertiesFileUtility propUtil = new PropertiesFileUtility();
		//String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
		String BROWSER=browser;
		String URL = propUtil.readingDataFromPropertiesFile("url");
		String UN = propUtil.readingDataFromPropertiesFile("uname");
		String PWD = propUtil.readingDataFromPropertiesFile("pwd");

		
		//Cross Browser Testing
		String expectedURL="http://49.249.28.218:8098/dashboard";  //global declaring the url to verify the dashboard
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//launch the Ninza Application and entered UN and PWD and click Login button
		driver.get(URL);
		//calling with Object Repository for un and pwd
		TC1_LoginPage lp=new TC1_LoginPage(driver);
		lp.login(UN, PWD);
		
		Thread.sleep(3000);
		
		//verify the dashboard page
		String actualURL=driver.getCurrentUrl();
		if(actualURL.equals(expectedURL)) {
			Reporter.log("Dashboard page is successfull",true);
		}else {
			Reporter.log("Dashboard page is unsuccessfull",true);
		}
				
		//used Object Repository for Logout of the application
		TC2_DashboardPage dp=new TC2_DashboardPage(driver);
		dp.logout();
		
		//close the browser
		driver.quit();
		
}
}

/* replacing the below code using PropertiesFileUtility and implementing in 
 * CampaignTest and contactTest pakages
 //reading data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER=prop.getProperty("browser");
		String URL=prop.getProperty("url");
		String UN=prop.getProperty("uname");
		String PWD=prop.getProperty("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);
		
		
		
		Actions action=new Actions(driver);
		action.moveToElement(logoutbtn).click().perform();
		*/
