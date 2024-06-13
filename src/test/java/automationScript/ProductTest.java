package automationScript;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;
import pages.ProductPage;
import tests.BaseTest;
import utilities.ActionUtils;
import utilities.ExtentReportsUtility;

@Listeners(utilities.Listener.class)

public class ProductTest extends BaseTest {

	protected static Logger Productlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	static ArrayList<String> productAddedToCart = new ArrayList<String>();
	public static int cartNumber = 0;
	static ProductPage pp ;

	public static AndroidDriver addToCart(AndroidDriver driver,String productName)
	{
		System.out.println("**********************************************************************************");
		pp= new ProductPage(driver);
		try 
		{
		String scrollContainer = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")";
	    String elementToScroll = "new UiSelector().textMatches(\""+productName+"\")";		
		
	    driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable("+scrollContainer+")"
			+ ".scrollIntoView("+elementToScroll+")"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	    int count = pp.productnames.size();
	    Productlog.info("Number of Products display on screen:" + count);
	    extentReport.logTestInfo("Number of Products display on screen:" + count);
	    
	    int addnumber = pp.addToCart.size();
	    Productlog.info("Number of AddtoCart display on screen" + addnumber);
	    extentReport.logTestInfo("Number of AddtoCart display on screen" + addnumber);

	    System.out.println("Number of addtoCart: "+addnumber);
	    try {
		 for(int i =0; i<count;i++)
		    {
			 
		    	String vsProductName = pp.productnames.get(i).getText();
		    	if(vsProductName.equalsIgnoreCase(productName))
		    	{
		    		try 
		    		{
		    		   pp.addToCart.get(i).click();
     		    		productAddedToCart.add(vsProductName);
			    		cartNumber++;
		
		    	     }
		    		catch(Exception e)
		    		{
		    		System.out.println(e.getMessage());
		    		continue;
		    		}
		    	}
		    }
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());

	    }
		 Productlog.info("Products added in cart "+productAddedToCart);
		 extentReport.logTestInfo("Products added in cart "+productAddedToCart);
		 
		 Productlog.info("Cart Number "+cartNumber);
		 extentReport.logTestInfo("Cart Number "+cartNumber);

		 return driver;
	}
	

	
	@Test
	public void CartNumberTestTC4() throws MalformedURLException {
		
	    AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.ClickDropdown();	
		lp.countrySelection.click();
		
		lp.EnterName("Ramila");
		
		driver = ActionUtils.hideKeyBoard(driver);
		
		lp.SelectGender("female");
		driver =lp.ClickLetsShop(driver);
	    
		// pp = new ProductPage(driver);
		driver = addToCart(driver,"Converse All Star");
		driver = addToCart(driver,"Jordan Lift Off");  
		driver = addToCart(driver,"PG 3");	
		
		if(cartNumber!=0)
		{
		String numberProductsinCart = pp.NumberOfProducts.getText();
	 	int numberOfProductsinCart = Integer.parseInt(numberProductsinCart);
		Assert.assertEquals(numberOfProductsinCart, cartNumber);
		}
		else
		{
			 Productlog.info("You didn't add any products in cart");
			 extentReport.logTestInfo("You didn't add any products in cart");

		}

	}
	
	@Test
	public void AddCartToastMsgTestTC5() throws MalformedURLException, InterruptedException {
		
	    AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
		
		LoginPage lp = new LoginPage(driver);
			
		lp.ClickDropdown();	
		lp.countrySelection2.click();
		lp.EnterName("Ramila");
		
		driver = ActionUtils.hideKeyBoard(driver);
		
		lp.SelectGender("female");
		driver =lp.ClickLetsShop(driver);
				
		Thread.sleep(5000);
		pp = new ProductPage(driver);
		pp.ClickCart(driver);
		String toastmsg = pp.getToastMessage("name");
		Assert.assertEquals(toastmsg, "Please add some product at first");
	}
	
}
