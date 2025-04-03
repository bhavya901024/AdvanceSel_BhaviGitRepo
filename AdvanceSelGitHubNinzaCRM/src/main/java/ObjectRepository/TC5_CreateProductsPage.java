package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import GenericUtility.WebDriverUtility;

public class TC5_CreateProductsPage {
WebDriver driver;  //this is for pom file //global variable
	
	public TC5_CreateProductsPage(WebDriver driver) //this driver is different, it used to call the constructor //local variable
	{//object Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//object Identification
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement addProductBtn;
	
	@FindBy(name="productName")
	private WebElement productNameField;
	
	@FindBy(name="quantity")
	private WebElement quantityField;
	
	@FindBy(name="price")
	private WebElement priceField;
	
	@FindBy(name="productCategory")
	private WebElement selectproductCategoryField;
	
	@FindBy(name="vendorId")
	private WebElement selectvendorIdField;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath ="//div[@role='alert']")
	private WebElement confMsg;

	//object Encapsulation
	public WebElement getAddProductBtn() {
		return addProductBtn;
	}

	public WebElement getProductNameField() {
		return productNameField;
	}

	public WebElement getQuantityField() {
		return quantityField;
	}

	public WebElement getPriceField() {
		return priceField;
	}

	public WebElement getSelectproductCategoryField() {
		return selectproductCategoryField;
	}

	public WebElement getSelectvendorIdField() {
		return selectvendorIdField;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
	
	public void createProducts(String name,String quantity,String priceperunit)
	{
		productNameField.sendKeys(name);
		quantityField.clear();
		quantityField.sendKeys(quantity);
		priceField.clear();
		priceField.sendKeys(priceperunit);
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.select(selectproductCategoryField, 2);
		wUtil.select(selectvendorIdField, 1);
		submitBtn.click();	
	}
}

