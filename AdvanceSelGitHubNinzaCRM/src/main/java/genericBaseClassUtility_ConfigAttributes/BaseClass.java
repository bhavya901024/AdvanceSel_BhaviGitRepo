package genericBaseClassUtility_ConfigAttributes;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import GenericUtility.DataBaseUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;

public class BaseClass {
	
	//below 3 lines declaring globally
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	DataBaseUtility dbUtil=new DataBaseUtility();
	PropertiesFileUtility propUtil = new PropertiesFileUtility();
	
	@BeforeSuite
	public void beforesuite() {
		System.out.println("Establish database connection");
		dbUtil.getDBConnection("jdbc:mysql://localhost:3306/NinzaProject", "root", "rmgy@9999");
	}
	@BeforeTest
	public void beforetest() {
		System.out.println("Pre-configuration setup");
	}
	@BeforeClass
	public void beforeclass() throws IOException {
		System.out.println("Launch the browser");
		String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
		//String expectedURL="http://49.249.28.218:8098/dashboard";  //global declaring the url to verify the dashboard
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		sdriver=driver;
	}
	@BeforeMethod
	public void beforemethod() throws IOException {
		System.out.println("Login");
		String URL = propUtil.readingDataFromPropertiesFile("url");
		String UN = propUtil.readingDataFromPropertiesFile("uname");
		String PWD = propUtil.readingDataFromPropertiesFile("pwd");
		driver.get(URL);
		//calling with Object Repository for un and pwd
		TC1_LoginPage lp=new TC1_LoginPage(driver);
		lp.login(UN, PWD);
	}
	@AfterMethod
	public void aftermethod() {
		System.out.println("Logout");
		TC2_DashboardPage dp=new TC2_DashboardPage(driver);
		dp.logout();
	}
	@AfterClass
	public void afterclass() {
		System.out.println("Close the browser");
		driver.quit();
	}
	@AfterTest
	public void aftertest() {
		System.out.println("Post-configuration setup");
	}
	@AfterSuite
	public void aftersuite() {
		System.out.println("close database connection");
		dbUtil.closeDBConnection();
	}
}
