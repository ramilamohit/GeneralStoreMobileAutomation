package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage {
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
	}
	public CartPage(IOSDriver driver)
	{
		super(driver);
	}
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/toolbar_title")
	public WebElement titletxt;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButton;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/appbar_btn_back")
	public WebElement backbtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    public List<WebElement> productnames;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productprice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement chkemailNotification;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement termsOfConditions;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement closebtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public WebElement completePurchasebtn;
	
	public AndroidDriver ClickCart(AndroidDriver driver)
	{
	    clickElement(cartButton, "Cart");
		return driver;
	} 

	public String getTitle()
	{
		String title = getTextFromElement(titletxt,"Title");
		return title;
	}
	
	public void clickBackbtn()
	{
		clickElement(backbtn,"Back btn");
	}
	
	public String getTotalAmount()
	{
		String totAmt = getTextFromElement(totalAmount,"Total Amount");
		return totAmt;

	}
	public void clickClosebtn()
	{
		clickElement(closebtn,"Close TermsOfCondition");
	}
	public AndroidDriver ClickCompletePurchase(AndroidDriver driver)
	{
	    clickElement(completePurchasebtn, "Complete purchase");
		return driver;
	} 

	
	

}
