package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductPage extends BasePage
{
	public ProductPage(AndroidDriver driver)
	{
		super(driver);
	}
	public ProductPage(IOSDriver driver)
	{
		super(driver);
	}
	

	@iOSXCUITFindBy(id="")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
	public WebElement productLabel;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    public List<WebElement> productnames;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	public List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/counterText")
	public WebElement NumberOfProducts;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButton;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	public WebElement toastError;
	
	public AndroidDriver ClickCart(AndroidDriver driver)
	{
	    clickElement(cartButton, "Cart");
		return driver;
	} 
	
	public String getTitle()
	{
		String title = getTextFromElement(productLabel, "Product Label");	
		return title;
	}
	
	public String getToastMessage(String str)
	{
		String message = getAttributFromElement(toastError, "name", "Error Toast Message");
		return message;
	}
	
}
