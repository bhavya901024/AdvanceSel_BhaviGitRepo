package ExtentReportGen;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import ObjectRepository.TC4_ContactsPage;
import ObjectRepository.TC4_CreateContactsPage;
import genericBaseClassUtility_ConfigAttributes.BaseClass;

@Listeners(ExtentReportImple.class)	
public class CreateContactwithCampaignTest extends BaseClass{

	//@Parameters("browser")
	@Test
	public void createContactwithCampaignTest() throws InterruptedException, IOException {
		//reading data from Generic UTility - PropertiesFileUtility
			//copied and pasted in Base Class(genericBaseClassUtility)
		
		//readingData from GenericUTility-Java Utility
		JavaUtility jUtil = new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
				
		/*//reading data from excel file 
		FileInputStream fis1=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1); */
				
		//readingData from generic Utility (refer ExcelFileUtility.java)
		ExcelFileUtility exUtil = new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("CreateCampaignData", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("CreateCampaignData", 1, 3);
		//also used contact excel sheet for reading other data's too
		String organization = exUtil.readingDataFromExcel("Contact", 1, 2)+randomNum;
		String title = exUtil.readingDataFromExcel("Contact", 1, 3);
		String contactName = exUtil.readingDataFromExcel("Contact", 1, 4)+randomNum;
		String mobile = exUtil.readingDataFromExcel("Contact", 1, 5);
		
		//cross browser testing
		//copied and pasted in Base Class(genericBaseClassUtility)
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//launch the Ninza Application and entered UN and PWD and click Login button
			//copied and pasted in Base Class(genericBaseClassUtility)
		Thread.sleep(3000);
		
		//Object Repository
		TC2_DashboardPage dp=new TC2_DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
		
		TC3_CampaignsPage cp=new TC3_CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		TC3_CreateCampaignsPage ccp=new TC3_CreateCampaignsPage(driver);
		ccp.createcampaignWithmandatoryFields(Campaign, targetSize);
		Thread.sleep(3000);
		
		WebElement contactLink=dp.getContactlink();
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.waitForElementToBeClickable(driver, contactLink, 20);
		contactLink.click();
		Thread.sleep(5000);

		TC4_ContactsPage ccp1=new TC4_ContactsPage(driver);
		WebElement createContactBtn=ccp1.getCreateContactBtn();
		Wutil.waitForElementToBeClickable(driver, createContactBtn, 20);
		createContactBtn.click();
		
		TC4_CreateContactsPage cct=new TC4_CreateContactsPage(driver);
		cct.createContactWithCampaign(organization, title, contactName, mobile, "selectCampaign", "create-contact", Campaign);
		//where selectCampaign is a partial text from the URL = child window(click on + and copy partial text)
		//create-contact is a partial text from the URL = parent window
		
	   Thread.sleep(3000);
	   String ConfirmationMsg = ccp1.getConfMsg().getText();
	   //Assertion concept
	   boolean status = ConfirmationMsg.contains(contactName);
	   Assert.assertEquals(status, true, "campaign not added");
	   //Assert.assertTrue(status, "Campaign not added");
	   Reporter.log("Camapign "+contactName+" added successfully",true);
	   Thread.sleep(5000);
	   
	 //used Object Repository for Logout of the application
	   //copied and pasted in Base Class(genericBaseClassUtility)

	}

}

/*uesd GenericUtility- WebDriver Utility
WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
wait.until(ExpectedConditions.elementToBeClickable(contactLink));

Set<String> allWindowIds = driver.getWindowHandles();
		for(String Window:allWindowIds)
		{
			driver.switchTo().window(Window);
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("selectCampaign"))
			{
				break;
			}
		}
 for(String Window:allWindowIds)
			{
				driver.switchTo().window(Window);
				String actUrl = driver.getCurrentUrl();
				if(actUrl.contains("create-contact"))
				{
					break;
				}
			}
			
	Select select1=new Select(selectTypeDD);
	     select1.selectByValue("campaignName");
	     
	//replaced below with object repository
	 * //create campaign 
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).clear();
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		Thread.sleep(3000);
		
		WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		//below 2 lines used WebUtility Methods
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.waitForElementToBeClickable(driver, contactLink, 20);
		contactLink.click();
		Thread.sleep(5000);
		WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
		//below 1 line used WebUtility Methods
		Wutil.waitForElementToBeClickable(driver, createContactBtn, 20);
		createContactBtn.click();
		driver.findElement(By.name("organizationName")).sendKeys(organization);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactName);
		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
		
		//below 1 line used WebUtility Methods
		Wutil.switchToWindow(driver, "selectCampaign");
		
		WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
		//below 1 line used WebUtility Methods
		Wutil.select(selectTypeDD,"campaignName");
		
	     driver.findElement(By.id("search-input")).sendKeys(Campaign);
	     driver.findElement(By.xpath("//button[@class='select-btn']")).click();

	   //below 1 line used WebUtility Methods
		Wutil.switchToWindow(driver, "create-contact");
	     
	    driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
*/
