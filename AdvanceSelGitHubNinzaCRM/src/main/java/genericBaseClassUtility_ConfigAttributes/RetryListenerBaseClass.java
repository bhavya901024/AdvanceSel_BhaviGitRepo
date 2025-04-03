package genericBaseClassUtility_ConfigAttributes;


	import java.io.IOException;
	import java.sql.SQLException;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;

import GenericListenerUtility.RetryListenerImplementation;
import GenericUtility.DataBaseUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;


	public class RetryListenerBaseClass extends RetryListenerImplementation{

		public WebDriver driver=null;
		public static WebDriver sdriver=null;
		DataBaseUtility dbUtil=new DataBaseUtility();
		PropertiesFileUtility pro=new PropertiesFileUtility();
		@BeforeSuite
		public void beforeSuite() throws SQLException
		{
			System.out.println("Established database connection");
			dbUtil.getDBConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "rmgy@9999");
		}
		@BeforeTest
		public void beforeTest()
		{
			System.out.println("Pre configuration set up");
		}
		@BeforeClass
		public void beforeClass() throws IOException
		{
			System.out.println("Launch the browser");
			String BROWSER = pro.readingDataFromPropertiesFile("browser");
			ChromeOptions Coption=new ChromeOptions();
			FirefoxOptions Foption=new FirefoxOptions();
			EdgeOptions Eoption=new EdgeOptions();
			Coption.addArguments("--headless");
			Foption.addArguments("--headless");
			Eoption.addArguments("--headless");
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(BROWSER.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
			}
			else
			{
				driver=new ChromeDriver();
			}
			sdriver=driver;
			
		}
		@BeforeMethod
		public void beforeMethod() throws IOException
		{
			System.out.println("Login");
			String URL = pro.readingDataFromPropertiesFile("url");
			String UN = pro.readingDataFromPropertiesFile("uname");
			String PWD = pro.readingDataFromPropertiesFile("pwd");
			driver.get(URL);
			TC1_LoginPage lp=new TC1_LoginPage(driver);
			lp.login(UN, PWD);
		}
		
		@AfterMethod
		public void afterMethod()
		{
			System.out.println("Logout");
			TC2_DashboardPage dp=new TC2_DashboardPage(driver);
			dp.logout();
		}
		@AfterClass
		public void afterClass()
		{
			System.out.println("Close the browser");
			driver.quit();
		}
		@AfterTest
		public void afterTest()
		{
			System.out.println("Post configuration set up");
		}
		@AfterSuite
		public void afterSuite()
		{
			System.out.println("close the db connection");
			dbUtil.closeDBConnection();
		}
		
	}
