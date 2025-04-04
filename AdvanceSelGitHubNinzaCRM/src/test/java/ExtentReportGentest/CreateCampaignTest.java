package ExtentReportGentest;

import java.io.FileInputStream;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericListenerUtility.ExtentReportImple;
import GenericListenerUtility.ListenerImple;
import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;
import ObjectRepository.TC3_CampaignsPage;
import ObjectRepository.TC3_CreateCampaignsPage;
import genericBaseClassUtility_ConfigAttributes.BaseClass;

@Listeners(ExtentReportImple.class)	
public class CreateCampaignTest extends BaseClass{
	
	@Test
	public void  createCampaignTest() throws InterruptedException, IOException {
		//Using TestNG created a methods and removed the main() and also copy-pasted the createCamapginwithcloseDate code and made it as method too
		
		//reading data from Generic UTility - PropertiesFileUtility
			//copied and pasted in Base Class(genericBaseClassUtility)
		
		//readingData from GenericUTility-Java Utility
		JavaUtility jUtil = new JavaUtility();
		int randomNum = jUtil.getRandomNum(5000);
		
		//readingData from generic Utility (refer ExcelFileUtility.java)
		ExcelFileUtility exUtil = new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("CreateCampaignData", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("CreateCampaignData", 1, 3);		
		
		//Cross Browser Testing
		//copied and pasted in Base Class(genericBaseClassUtility)
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//launch the Ninza Application and entered UN and PWD and click Login button
		//copied and pasted in Base Class(genericBaseClassUtility)
		Thread.sleep(3000);
		
		//create campaign 
		TC2_DashboardPage dp=new TC2_DashboardPage(driver);
		Thread.sleep(3000);
		dp.getCampaignsLink().click();
		TC3_CampaignsPage cp=new TC3_CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		TC3_CreateCampaignsPage ccp= new TC3_CreateCampaignsPage(driver);
		ccp.createcampaignWithmandatoryFields(Campaign, targetSize);
		Thread.sleep(3000);

		//handling confirmation message popup and used Object repository
		String confmsg = cp.getConfMsg().getText();	
		//Assertion concept
		boolean status = confmsg.contains(Campaign);
		Assert.assertEquals(status, true, "campaign not added");
		//Assert.assertTrue(status, "Camapign not added");
		Reporter.log("Camapign "+Campaign+" added successfully",true);
		Thread.sleep(3000);
		
		//Logout of the application
		//used Object Repository for Logout of the application
		//copied and pasted in Base Class(genericBaseClassUtility)
	}
//@Parameters("browser")
@Test
public void createCampaignwithcloseDateTest() throws InterruptedException, IOException {
	//reading data from Generic UTility - PropertiesFileUtility
		//copied and pasted in Base Class(genericBaseClassUtility)
	
	//readingData from GenericUTility-Java Utility
	JavaUtility jUtil = new JavaUtility();
	int randomNum = jUtil.getRandomNum(5000);
			
	//readingData from generic Utility (refer ExcelFileUtility.java)
	ExcelFileUtility exUtil = new ExcelFileUtility();
	String Campaign = exUtil.readingDataFromExcel("CreateCampaignData", 1, 2)+randomNum;
	String targetSize = exUtil.readingDataFromExcel("CreateCampaignData", 1, 3);
	
	//JavaUtility
	String closeDate=jUtil.generateReqDate(30);
	
	//Cross Browser Testing
	//copied and pasted in Base Class(genericBaseClassUtility)
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//launch the Ninza Application and entered UN and PWD and click Login button by using object repository
	//copied and pasted in Base Class(genericBaseClassUtility)
	Thread.sleep(3000);
	
	//create campaign using object Repository
	TC2_DashboardPage dp=new TC2_DashboardPage(driver);
	Thread.sleep(3000);
	dp.getCampaignsLink().click();
	TC3_CampaignsPage cp=new TC3_CampaignsPage(driver);
	cp.getCreateCampaignBtn().click();
	TC3_CreateCampaignsPage ccp= new TC3_CreateCampaignsPage(driver);
	ccp.createCampaignWithCloseDate(Campaign, targetSize, closeDate);
	Thread.sleep(3000);

	//handling confirmation message popup and used Object repository
	String confmsg = cp.getConfMsg().getText();
	//Assertion concept
	boolean status = confmsg.contains(Campaign);
	Assert.assertEquals(status, true, "campaign not added");
	//Assert.assertTrue(status, "Camapign not added");
	Reporter.log("Camapign "+Campaign+" added successfully",true);
	Thread.sleep(3000);

	//Logout of the application
	//used Object Repository for Logout of the application
	//copied and pasted in Base Class(genericBaseClassUtility)

}

}










/* replacing the below code with ExcelFileUtility

//reading data from excel file 
		FileInputStream fis1=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		//used single Step and for SHEET,ROW,CELL- i have collaborated
		String Campaign = wb.getSheet("CreateCampaignData").getRow(1).getCell(2).getStringCellValue()+randomNum;
		String targetSize=wb.getSheet("CreateCampaignData").getRow(1).getCell(3).getStringCellValue();
		System.out.println(Campaign);
		System.out.println(targetSize);
		
//using random class for unique value in excel
		Random ran = new Random();
		int randomNum=ran.nextInt(1000);
		
		//logout 
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		//below 2 lines used from Generic Utility-WebDriverUtility
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.actionMoveToElement(driver, logoutbtn);	

//create campaign
 * driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).clear();
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);
 */
