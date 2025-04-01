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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Module7CreateProducts {

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
		
		//using random class for unique value in excel
		Random ran = new Random();
		int randomNum=ran.nextInt(1000);
					
		//reading data from excel file 
		FileInputStream fis1=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		//used single Step and for SHEET,ROW,CELL- i have collaborated
	    String ProductName = wb.getSheet("Products").getRow(1).getCell(2).getStringCellValue()+randomNum;
	    String Quantity = wb.getSheet("Products").getRow(1).getCell(3).getStringCellValue();
	    String PriceperUnit = wb.getSheet("Products").getRow(1).getCell(4).getStringCellValue();
		
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
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);

		//click on Products link and add products 
		driver.findElement(By.partialLinkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		
		//reading textFileds from Excel file data
		driver.findElement(By.name("productName")).sendKeys(ProductName);
		driver.findElement(By.name("quantity")).clear();
		driver.findElement(By.name("quantity")).sendKeys(Quantity);
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(PriceperUnit);
		
		//Handling drop down for Select Category
		WebElement ListBox1 = driver.findElement(By.name("productCategory"));
		Select listdrop1=new Select(ListBox1);
		listdrop1.selectByVisibleText("Electronics");  
		Thread.sleep(2000);
		
		//Handling drop down for Select Vendor
		WebElement ListBox2 = driver.findElement(By.name("vendorId"));
		Select listdrop2=new Select(ListBox2);
		listdrop2.selectByVisibleText("Vendor_49431 - (Electronics)");  
		Thread.sleep(2000);
		
		//click on Add button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//handling confirmation message popup
		String confmsg = driver.findElement(By.xpath("//div[text()='Product "+ProductName+" Successfully Added']")).getText();
			if(confmsg.contains(ProductName)) {
				System.out.println("Product added successfully");
				} 
			else {
				System.out.println("Product not added successfully");
				}
		Thread.sleep(4000);

		//Logout of the application
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutbtn).click().perform();
								
		//close the browser
		driver.quit();  
	}

}
