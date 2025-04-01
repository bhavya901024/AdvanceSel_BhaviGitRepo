package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC3_CreateCampaignsPage {
WebDriver driver;  //this is for pom file //global variable
	
	public TC3_CreateCampaignsPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(name = "campaignName")
	private WebElement campaignName;
	
	@FindBy(name="targetSize")
	private WebElement targetSize;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedCloseDateField;
	
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement createCampaignBtn;

	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}

	public WebElement getExpectedCloseDateField() {
		return expectedCloseDateField;
	}

	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

	public void createcampaignWithmandatoryFields(String campName, String target)
	{
		campaignName.sendKeys(campName);
		targetSize.clear();
		targetSize.sendKeys(target);
		createCampaignBtn.click();
	}
	public void createCampaignWithCloseDate(String campName,String target,String date)
	{
		campaignName.sendKeys(campName);
		targetSize.clear();
		targetSize.sendKeys(target);
		expectedCloseDateField.sendKeys(date);
		createCampaignBtn.click();
	}
	
}
