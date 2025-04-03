package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC3_CampaignsPage {
WebDriver driver;  //this is for pom file //global variable
	
	public TC3_CampaignsPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{//object Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//object Identification
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement createCampaignBtn;
	
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement searchByDD;
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement searchField;
	
	@FindBy(xpath ="//div[@role='alert']")
	private WebElement confMsg;

	//Object Encapsulation
	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

	public WebElement getSearchByDD() {
		return searchByDD;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
	
}
