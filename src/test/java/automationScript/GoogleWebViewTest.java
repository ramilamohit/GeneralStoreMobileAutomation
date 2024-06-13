package automationScript;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pages.CartPage;
import pages.GoogleWebViewPage;
import pages.LoginPage;
import pages.ProductPage;
import tests.BaseTest;
import utilities.ActionUtils;
import utilities.ExtentReportsUtility;

@Listeners(utilities.Listener.class)

public class GoogleWebViewTest extends BaseTest
{
	protected Logger Googlelog = LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();
	ProductPage pp;

	@Test
	public void HybridGoogleTC10() throws MalformedURLException, InterruptedException
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
    	driver = cp.ClickCompletePurchase(driver);

    	Thread.sleep(5000);
    	GoogleWebViewPage googleWeb = new GoogleWebViewPage(driver);
    	driver = googleWeb.switchContext(driver);
    	
    	googleWeb.sendtxt("Appium");
    	googleWeb.googleTxtbox.sendKeys(Keys.ENTER);;

    	Thread.sleep(5000);

    	ActionUtils.pressAndroidBackKey(driver);
    	Thread.sleep(5000);
    	
    	driver = googleWeb.switchContext(driver);
    	
        } 
	
}
