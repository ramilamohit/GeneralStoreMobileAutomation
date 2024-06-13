package automationScript;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class LoginTest extends BaseTest{
	protected Logger Loginlog = LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

		@Test
		public void LoginTestTC1() throws MalformedURLException, InterruptedException
	    {
		
			AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");

			LoginPage lp = new LoginPage(driver);

			lp.ClickDropdown();	
			lp.countrySelection.click();
			
			lp.EnterName("Ramila");
			
			driver = ActionUtils.hideKeyBoard(driver);
			
			lp.SelectGender("female");
			driver =lp.ClickLetsShop(driver);
			
			ProductPage pp = new ProductPage(driver);
			String txt =pp.getTitle();
			
			Assert.assertEquals(txt, "Products");
			Loginlog.info("Login Testcase passed");
			extentReport.logTestpassed("Login Testcase passed");
	    }
		
		@Test
		public void ErrorToastMsgTestTC2() throws MalformedURLException
		{
			AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");		
			LoginPage lp = new LoginPage(driver);
			
			lp.ClickDropdown();	
			lp.countrySelection1.click();
			
			driver = ActionUtils.hideKeyBoard(driver);
			lp.SelectGender("female");
			driver =lp.ClickLetsShop(driver);

			String toastMsg = lp.getErrorToastMsg();
			
			//toast message validation
			Assert.assertEquals(toastMsg, "Please enter your name");
		}
		
		@Test
		public void chkDefaultMaleRadioBtnTC3() throws MalformedURLException, InterruptedException 
		{
			AndroidDriver driver = (AndroidDriver) BaseTest.getDriver("android");
			LoginPage lp = new LoginPage(driver);
			Thread.sleep(5000);
		
			driver = ActionUtils.hideKeyBoard(driver);
			//checks default checked radio button for gender is Male or not
			String dftcheck = lp.chkDefaultGender();
			Assert.assertEquals(dftcheck, "true");	
		}
			
		}