package ProductTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;
import ObjectRepository.TC5_CreateProductsPage;
import ObjectRepository.TC5_ProductsPage;

public class CreateProductTest {

	public static void main(String[] args) throws IOException, InterruptedException {
			//reading data from Generic UTility - PropertiesFileUtility
			PropertiesFileUtility propUtil = new PropertiesFileUtility();
			String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
			String URL = propUtil.readingDataFromPropertiesFile("url");
			String UN = propUtil.readingDataFromPropertiesFile("uname");
			String PWD = propUtil.readingDataFromPropertiesFile("pwd");
			
			//readingData from GenericUTility-Java Utility
			JavaUtility jUtil = new JavaUtility();
			int randomNum = jUtil.getRandomNum(2000);
						
			//readingData from generic Utility (refer ExcelFileUtility.java)
			ExcelFileUtility exUtil = new ExcelFileUtility();
			String ProductName = exUtil.readingDataFromExcel("Products", 1, 2)+randomNum;
			String Quantity = exUtil.readingDataFromExcel("Products", 1, 3);
			String PriceperUnit = exUtil.readingDataFromExcel("Products", 1, 4);
							
			//Cross Browser Testing
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
			TC1_LoginPage lp=new TC1_LoginPage(driver);
			lp.login(UN, PWD);
			Thread.sleep(3000);

			//Object Repository
			TC2_DashboardPage dp=new TC2_DashboardPage(driver);
			Thread.sleep(3000);
			WebElement productLink = dp.getProductslink();
						
			WebDriverUtility wUtil=new WebDriverUtility();
			wUtil.waitForElementToBeClickable(driver, productLink, 30);
			productLink.click();	 
			Thread.sleep(2000);
			
			TC5_ProductsPage pp=new TC5_ProductsPage(driver);
			pp.getAddProductBtn().click();
			Thread.sleep(3000);
			
			TC5_CreateProductsPage cpp=new TC5_CreateProductsPage(driver);
			cpp.createProducts(ProductName, Quantity, PriceperUnit);
			Thread.sleep(3000);
			
			//handling confirmation message popup
			String confmsg = cpp.getConfMsg().getText();
				if(confmsg.contains(ProductName)) {
					System.out.println("Product "+ProductName+ " added successfully");
					} 
				else {
					System.out.println("Product not added successfully");
					}
			Thread.sleep(4000);

			//Logout of the application
			//used Object Repository for Logout of the application
			dp.logout();								
			//close the browser
			driver.quit();  
		}

	}
