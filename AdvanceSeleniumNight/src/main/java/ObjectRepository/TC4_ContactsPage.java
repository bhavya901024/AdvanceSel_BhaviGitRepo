package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC4_ContactsPage {
WebDriver driver;  //this is for pom file //global variable
	
	public TC4_ContactsPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath = "//span[text()='Create Contact']")
	private WebElement createContactBtn;
	
	@FindBy(xpath ="//div[@role='alert']")
	private WebElement confMsg;

	public WebElement getCreateContactBtn() {
		return createContactBtn;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
	
}
