package NinzaCRMProject;

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

public class Module5RandomclassandreaduniqueValuefromexcel {
//refer notes(STEPS) too which i have written
	//Imp Note: in excel change the targetSize to '100 and press enter key - this ll convert num to String
	public static void main(String[] args) throws InterruptedException, IOException {
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
		
		//using random class for unique value in excel
		Random ran = new Random();
		int randomNum=ran.nextInt(1000);
		
		//reading data from excel file 
		FileInputStream fis1=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		//used single Step and for SHEET,ROW,CELL- i have collaborated
		String Campaign = wb.getSheet("CreateCampaignData").getRow(1).getCell(2).getStringCellValue()+randomNum;  //concatinated
		String targetSize=wb.getSheet("CreateCampaignData").getRow(1).getCell(3).getStringCellValue();   //converted double to String and getNumricValue to getStringValue
		System.out.println(Campaign);
		System.out.println(targetSize);
		
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
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
		//create campaign 
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);   //changed Vivaan to campaign variable
		driver.findElement(By.name("targetSize")).clear();
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);   //changed number 100 to targetSize variable
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);

		//handling confirmation message popup
		String confmsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		if(confmsg.contains(Campaign)) {  ////changed Vivaan to campaign variable
				System.out.println("Campaign added successfully");
			}else {
				System.out.println("Campaign not added successfully");
			}
		Thread.sleep(3000);

		//Logout of the application
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutbtn).click().perform();
						
		//close the browser
		driver.quit();

	}

}
