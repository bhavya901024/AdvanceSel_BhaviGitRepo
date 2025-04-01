package ObjectRepository;

import java.awt.Desktop.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC2_DashboardPage {

	WebDriver driver;  //this is for pom file //global variable
	
	public TC2_DashboardPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText = "Products")
	private WebElement productslink;
	
	@FindBy(xpath = "//*[name()='svg' and @role='img']")
	private WebElement profileIcon;
	
	@FindBy(xpath = "//div[text()='Logout ']")
	private WebElement logoutBtn;

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getProductslink() {
		return productslink;
	}

	public WebElement getProfileIcon() {
		return profileIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	public void logout()
	{
		profileIcon.click();
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click();
	}
	
}
