package NinzaCRMProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Module6DateFormat {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		
				//expected close Date
				Date dateObj = new Date();
				SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");  //MM should be in caps
				String todayDate=sim.format(dateObj);
				System.out.println(todayDate);
				
				Calendar cal=sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH, 30);
				String closeDate=sim.format(cal.getTime());
				System.out.println(closeDate);
				Thread.sleep(3000);
				
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
				driver.findElement(By.name("campaignName")).sendKeys("Vivaan7");
				driver.findElement(By.name("targetSize")).clear();
				driver.findElement(By.name("targetSize")).sendKeys("5");
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				Thread.sleep(3000);

				//handling confirmation message popup
				String confmsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
				if(confmsg.contains("Vivaan7")) {
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
