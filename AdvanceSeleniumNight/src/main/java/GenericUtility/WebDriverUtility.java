package GenericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility //Selenium and commonly used Script
{ //used in createContact
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	//used in createContact
	public void waitForElementToBeClickable(WebDriver driver,WebElement element,long sec) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	//used in createContact
	public void switchToWindow(WebDriver driver, String partialURL) {
		Set<String> allWindowIds=driver.getWindowHandles();
		for(String Window:allWindowIds)
		{
			driver.switchTo().window(Window);
			String actURL = driver.getCurrentUrl();
			if(actURL.contains(partialURL))
			{
				break;
			}
		}
	}
	//Overloaded methods for switching the frames 
	public void SwitchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void SwitchToFrame(WebDriver driver,String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void SwitchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//Overloaded methods for handling the drop downs
	public void select(WebElement element,String value) {
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void select(String visibleText,WebElement element) {
		Select sel=new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	//Assignment
	//create  methods for double click,context click and move To Elememt using Actions class
	public void actionDoubleClick(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}
	public void actionRightClick(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	public void actionMoveToElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void actionMoveToElementAndClick(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	//take Screen Shot Method for WebPage
	public void takingScreenShotofWebPage(WebDriver driver,String fileName) throws IOException
	{
	TakesScreenshot ts = (TakesScreenshot)driver;
	File temp = ts.getScreenshotAs(OutputType.FILE);
	File dest = new File("./screenshot/"+fileName+".png");
	FileHandler.copy(temp, dest);	
	}
	//take Screen Shot Method for WebElement
	public void takingScreenShotofWebElement(WebElement element,String fileName) throws IOException
	{
	File temp = element.getScreenshotAs(OutputType.FILE);
	File dest = new File("./screenshot/"+fileName+".png");
	FileHandler.copy(temp, dest);	
	}
}

