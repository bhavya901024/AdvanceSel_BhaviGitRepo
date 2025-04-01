package ProductTest;


		import java.io.FileInputStream;
		import java.io.FileNotFoundException;
		import java.io.IOException;
		import java.time.Duration;
		import java.util.Properties;
		import java.util.Random;

		import org.apache.poi.ss.usermodel.Workbook;
		import org.apache.poi.ss.usermodel.WorkbookFactory;
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.edge.EdgeDriver;
		import org.openqa.selenium.firefox.FirefoxDriver;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.Select;
		import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;
import ObjectRepository.TC3_CampaignsPage;
import ObjectRepository.TC3_CreateCampaignsPage;
import ObjectRepository.TC4_ContactsPage;
import ObjectRepository.TC5_CreateProductsPage;
import ObjectRepository.TC5_ProductsPage;
		
		public class CreateProduct {
		
			//@Parameters("browser")
			@Test(groups={"Integretion"})
			public void createProductTest() throws IOException, InterruptedException {
				//reading data from Generic UTility - PropertiesFileUtility
				PropertiesFileUtility propUtil = new PropertiesFileUtility();
				String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
				//String BROWSER=browser;
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
				//Assertion concept
				boolean status = confmsg.contains(ProductName);
				Assert.assertEquals(status, true, "campaign not added");
				//Assert.assertTrue(status, "Product not added");
				Reporter.log("Camapign "+ProductName+" added successfully",true);
				Thread.sleep(4000);

				//Logout of the application
				//used Object Repository for Logout of the application
				dp.logout();								
				//close the browser
				driver.quit();  
			}

		}

/*
 * //click on Products link and add products 
				driver.findElement(By.partialLinkText("Products")).click();
				//below 2 lines used WebUtility Methods
				WebDriverUtility Wutil=new WebDriverUtility();
				Wutil.waitForElementToBeClickable(driver, Products, 20);
				//driver.findElement(By.xpath("//span[text()='Add Product']")).click();
				
				//reading textFileds from Excel file data
				driver.findElement(By.name("productName")).sendKeys(ProductName);
				driver.findElement(By.name("quantity")).clear();
				driver.findElement(By.name("quantity")).sendKeys(Quantity);
				driver.findElement(By.name("price")).clear();
				driver.findElement(By.name("price")).sendKeys(PriceperUnit);
 * 
 * 91: 
 * //Handling drop down for Select Category
				WebElement ListBox1 = driver.findElement(By.name("productCategory"));
				Select listdrop1=new Select(ListBox1);
				listdrop1.selectByVisibleText("Electronics"); 
 * 
 * //Handling drop down for Select Vendor
				WebElement ListBox2 = driver.findElement(By.name("vendorId"));
				Select listdrop2=new Select(ListBox2);
				listdrop2.selectByVisibleText("Vendor_49431 - (Electronics)");  
				Thread.sleep(2000);
				
				//click on Add button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
		*/
		