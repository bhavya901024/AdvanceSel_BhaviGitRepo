package NinzaCRMProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Module1LaunchNinzaTest {

	public static void main(String[] args) throws InterruptedException {
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//launch the Ninza Application and entered UN and PWD and click Login button
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
		//verify the dashboard page
		String actualURL=driver.getCurrentUrl();
		if(actualURL.equals(expectedURL)) {
			System.out.println("Dashboard page is successfull");
		}else {
			System.out.println("Dashboard page is unsuccessfull");
		}
				
		//Logout of the application
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutbtn).click().perform();
		
		//close the browser
		driver.quit();
		

	}

}
