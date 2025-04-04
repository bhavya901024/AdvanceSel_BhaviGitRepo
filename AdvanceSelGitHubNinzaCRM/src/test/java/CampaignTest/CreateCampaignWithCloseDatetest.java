package CampaignTest;


	import java.io.FileInputStream;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.Properties;
	import java.util.Random;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;


import org.openqa.selenium.By;
//org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;

	import GenericUtility.ExcelFileUtility;
	import GenericUtility.JavaUtility;
	import GenericUtility.PropertiesFileUtility;

	public class CreateCampaignWithCloseDatetest {

		public static void main(String[] args) throws IOException, InterruptedException {
			PropertiesFileUtility propUtil=new PropertiesFileUtility();
			String BROWSER = propUtil.readingDataFromPropertiesFile("browser");
			String URL = propUtil.readingDataFromPropertiesFile("url");
			String UN = propUtil.readingDataFromPropertiesFile("uname");
			String PWD = propUtil.readingDataFromPropertiesFile("pwd");
			
			JavaUtility jUtil=new JavaUtility();
			int randomNum = jUtil.getRandomNum(2000);
			
			ExcelFileUtility exUtil=new ExcelFileUtility();
			String Campaign = exUtil.readingDataFromExcel("DDT", 1, 2)+randomNum;
			String targetSize = exUtil.readingDataFromExcel("DDT", 1, 3);
			
			String closeDate = jUtil.generateReqDate(30);
			
			String expectedURL="http://49.249.28.218:8098/dashboard";
			//Launching the browser
			WebDriver driver=null;
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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			//navigating to ninza CRM
			driver.get(URL);
			//enter the username and password
			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PWD);
			//click on sign in button
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Campaigns")).click();
			driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			driver.findElement(By.name("campaignName")).sendKeys(Campaign);
			driver.findElement(By.name("targetSize")).clear();
			driver.findElement(By.name("targetSize")).sendKeys(targetSize);
			driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			Thread.sleep(2000);
			String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
			if(ConfMsg.contains(Campaign))
			{
				System.out.println("campaign added successfully");
			}
			else
			{
				System.out.println("campaign not added");
			}
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[@class='user-icon']")).click();
	        WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
	        Actions action=new Actions(driver);
	        action.moveToElement(logoutBtn).click().perform();
	        //close the browser
	        driver.quit();
		}

	}
