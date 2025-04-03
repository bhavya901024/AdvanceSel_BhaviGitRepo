package CampaignTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;
import ObjectRepository.TC3_CampaignsPage;
import ObjectRepository.TC3_CreateCampaignsPage;

public class CreateCampaign {
 public static void main(String[] args) throws InterruptedException, IOException {
			
			//reading data from Generic UTility - PropertiesFileUtility
			PropertiesFileUtility propUtil = new PropertiesFileUtility();
			String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
			String URL = propUtil.readingDataFromPropertiesFile("url");
			String UN = propUtil.readingDataFromPropertiesFile("uname");
			String PWD = propUtil.readingDataFromPropertiesFile("pwd");
			
			//readingData from GenericUTility-Java Utility
			JavaUtility jUtil = new JavaUtility();
			int randomNum = jUtil.getRandomNum(5000);
			
			//readingData from generic Utility (refer ExcelFileUtility.java)
			ExcelFileUtility exUtil = new ExcelFileUtility();
			String Campaign = exUtil.readingDataFromExcel("CreateCampaignData", 1, 2)+randomNum;
			String targetSize = exUtil.readingDataFromExcel("CreateCampaignData", 1, 3);		
			
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
			if(confmsg.contains(Campaign)) {
					System.out.println("Campaign "+Campaign+ " added successfully");
				}else {
					System.out.println("Campaign not added successfully");
				}
			Thread.sleep(3000);
			//Logout of the application
			//used Object Repository for Logout of the application
			dp.logout();				
			//close the browser
			driver.quit();
		}
}
