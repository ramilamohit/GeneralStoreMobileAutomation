package automationScript;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import tests.BaseTest;
import utilities.ActionUtils;
import utilities.ExtentReportsUtility;

@Listeners(utilities.Listener.class)

public class CartTest extends BaseTest{
	
	protected Logger Cartlog = LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();
	ProductPage pp;

	    @Test
		public void ProductNamesInCartTC6() throws MalformedURLException, InterruptedException
		{
	    	AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");			
			
	    	LoginPage lp = new LoginPage(driver);
			lp.ClickDropdown();	
			lp.countrySelection.click();
			lp.EnterName("Ramila");
			
			driver = ActionUtils.hideKeyBoard(driver);
			
			lp.SelectGender("female");
			driver =lp.ClickLetsShop(driver);
		    
			pp = new ProductPage(driver);
			driver = ProductTest.addToCart(driver,"Converse All Star");
			driver = ProductTest.addToCart(driver,"Jordan Lift Off");  
			driver = ProductTest.addToCart(driver,"PG 3");	
			driver = pp.ClickCart(driver);
			CartPage cp = new CartPage(driver);
			Thread.sleep(4000);
			driver = ActionUtils.scroll(driver, 529, 1531, 529, 381);
			Thread.sleep(4000);

		
			int count = cp.productnames.size();

			Cartlog.info("Number of Products in the cart:" + count);
			extentReport.logTestInfo("Number of Products in the cart:" + count);
		
			ArrayList<String> productInCart = new ArrayList<String>();
			for(int i =0; i<count;i++)
			{
				Thread.sleep(2000);
				driver = ActionUtils.scroll(driver, 529, 1531, 529, 381);

				String vsProductName =cp.productnames.get(i).getText();
				productInCart.add(vsProductName);  	
			}
			Cartlog.info("Products in the cart:" + productInCart);
			extentReport.logTestInfo("Products in the cart:" + productInCart);
	
			Assert.assertEquals(productInCart, ProductTest.productAddedToCart);
			Cartlog.info("ProductNames in cart are same as added");
			extentReport.logTestInfo("ProductNames in cart are same as added");
	
	}
		@Test
		public void TotalAmountValidateTC7() throws MalformedURLException, InterruptedException
		{
			AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
	    	LoginPage lp = new LoginPage(driver);
			lp.ClickDropdown();	
			lp.countrySelection.click();
			lp.EnterName("Ramila");
			
			driver = ActionUtils.hideKeyBoard(driver);
			
			lp.SelectGender("female");
			driver =lp.ClickLetsShop(driver);
		    
			pp = new ProductPage(driver);
			driver = ProductTest.addToCart(driver,"Converse All Star");
			driver = ProductTest.addToCart(driver,"Nike SFB Jungle");  
			driver = ProductTest.addToCart(driver,"Jordan Lift Off");  
			driver = ProductTest.addToCart(driver,"PG 3");

			driver = pp.ClickCart(driver);
			CartPage cp = new CartPage(driver);
			Thread.sleep(4000);
			driver = ActionUtils.scroll(driver, 529, 1531, 529, 381);
			Thread.sleep(4000);
			int count = cp.productnames.size();

			Cartlog.info("Number of Products in the cart:" + count);
			extentReport.logTestInfo("Number of Products in the cart:" + count);

		    double sum =0;
		    double price = 0;
			  for(int i =0; i<count;i++)
			    {	
				  String productPrice = cp.productprice.get(i).getText();
			    	productPrice = productPrice.substring(1);
			    	price = Double.parseDouble(productPrice);
			    	sum = sum + price; 	 
			    }
			  String cost = cp.getTotalAmount();
		      cost = cost.substring(1);
		      double totalCost = Double.parseDouble(cost);
		      Assert.assertEquals(sum, totalCost);
		}
		
		@Test
		public void TermsOfConditionsTC8() throws MalformedURLException, InterruptedException
		{
			AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
	    	LoginPage lp = new LoginPage(driver);
			lp.ClickDropdown();	
			lp.countrySelection.click();
			lp.EnterName("Ramila");
			
			driver = ActionUtils.hideKeyBoard(driver);
			
			lp.SelectGender("female");
			driver =lp.ClickLetsShop(driver);
		    
			pp = new ProductPage(driver);
			driver = ProductTest.addToCart(driver,"Converse All Star");
			driver = ProductTest.addToCart(driver,"Jordan Lift Off");  

			driver = pp.ClickCart(driver);
			CartPage cp = new CartPage(driver);
			
			ActionUtils.ClickGesture(driver, cp.chkemailNotification);
			ActionUtils.longClickGesture(driver, cp.termsOfConditions);
			
			cp.clickClosebtn();
	    	driver = cp.ClickCompletePurchase(driver);
		
		}

	 
	@Test
	public void BackBtnTestTC9() throws InterruptedException, MalformedURLException {
		
		AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.ClickDropdown();	
		lp.countrySelection.click();
		
		lp.EnterName("Ramila");
		
		driver = ActionUtils.hideKeyBoard(driver);
		
		lp.SelectGender("female");
		driver =lp.ClickLetsShop(driver);
	    
		 pp = new ProductPage(driver);
		driver = ProductTest.addToCart(driver,"Converse All Star");
		
		driver = pp.ClickCart(driver);
		
		CartPage cp = new CartPage(driver);
		String titleCart = cp.getTitle();
		Cartlog.info("Current title: " + titleCart);
		extentReport.logTestInfo("Current title: " + titleCart);
		
		cp.clickBackbtn();
		Thread.sleep(4000);
		String titleProduct = cp.getTitle();
		
		Cartlog.info("Current title: " + titleProduct);
		extentReport.logTestInfo("Current title: " + titleProduct);


		Assert.assertEquals(titleProduct, "Products");
		
		cp.clickBackbtn();
		Thread.sleep(4000);
		
		String titleGeneralStore = cp.getTitle();
		Cartlog.info("Current title: " + titleGeneralStore);
		extentReport.logTestInfo("Current title: " + titleGeneralStore);

		Assert.assertEquals(titleGeneralStore, "General Store");
		
	}

}
