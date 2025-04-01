package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC5_ProductsPage {
WebDriver driver;  //this is for pom file //global variable
	
	public TC5_ProductsPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{//object Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//object Identification
	@FindBy(linkText = "Products")
	private WebElement productslink;
	
	@FindBy(xpath = "//select[@class='form-control']")
	private WebElement searchByID;
	
	@FindBy(xpath = "//input[@placeholder='Search by product Id']")
	private WebElement searchField;
	
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement addProductBtn;
	
	@FindBy(xpath ="//div[@role='alert']")
	private WebElement confMsg;

	//object Encapsulation
	public WebElement getProductslink() {
		return productslink;
	}

	public WebElement getSearchByID() {
		return searchByID;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getAddProductBtn() {
		return addProductBtn;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
}
