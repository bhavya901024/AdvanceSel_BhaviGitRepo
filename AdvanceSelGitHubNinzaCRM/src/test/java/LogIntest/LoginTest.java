package LogIntest;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.TC1_LoginPage;
import ObjectRepository.TC2_DashboardPage;
import genericBaseClassUtility_ConfigAttributes.BaseClass;

@Listeners(ExtentReportImple.class)																																																																			
public class LoginTest extends BaseClass{
	@Test
public void loginTest() throws InterruptedException, IOException {
	//reading data from Generic UTility - PropertiesFileUtility
		//copied and pasted in Base Class(configurationAttributes)
		
	//Cross Browser Testing
		String expectedURL="http://49.249.28.218:8098/dashboard";  //failing the Line for Screenshot in ExtentReports
		//String expectedURL="http://49.249.28.218:8098/dashboard";
		//copied and pasted in Base Class(configurationAttributes)
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//launch the Ninza Application and entered UN and PWD and click Login button
		//copied and pasted in Base Class(configurationAttributes)
	//calling with Object Repository for un and pwd
		//copied and pasted in Base Class(configurationAttributes)
		
		Thread.sleep(3000);
		
		//verify the dashboard page
		String actualURL=driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL, "Validation Failed");
		Reporter.log("Validation is passed",true);	
				
	//used Object Repository for Logout of the application
		//copied and pasted in Base Class(configurationAttributes)
		
	//close the browser
		//copied and pasted in Base Class(configurationAttributes)
		
}
}

