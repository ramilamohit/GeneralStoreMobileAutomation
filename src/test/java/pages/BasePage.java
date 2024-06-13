package pages;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

//import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExtentReportsUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage 
{
	protected Logger BaseClasslog = LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	public BasePage(AndroidDriver driver)
	{
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(4000)), this);
	}
	public BasePage(IOSDriver driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getAttributFromElement(WebElement element,String str, String objectName)
	{
		String data = element.getAttribute(str);
		BaseClasslog.info("Attribute name is extracted from "+objectName);
		extentReport.logTestInfo("Attribute name is extracted from "+objectName);
		return data;
	}
	
	public void clickElement(WebElement element, String objectName) {
		if (element.isEnabled())
		{
			element.click();
			BaseClasslog.info(objectName + " button is clicked");
			extentReport.logTestInfo(objectName + " button is clicked");

			
		}
		else
		{
			BaseClasslog.info(objectName+" element is not enabled");
			extentReport.logTestInfo(objectName + " element is not enabled");

		}
	}
	
	public void enterText(WebElement element, String data, String objectName)
	{
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			BaseClasslog.info("data is entered in " + objectName + " textbox");
			extentReport.logTestInfo("data is entered in " + objectName + " textbox");

		} else {
			BaseClasslog.info(objectName + " element is not displayed");
			extentReport.logTestInfo( objectName + " element is not displayed");

		}
	}
	public void checkboxclick(WebElement element, String objectname)
	{
		if(element.isSelected()== false)
		{
			element.click();
			BaseClasslog.info(objectname +" element is checked");
			extentReport.logTestInfo(objectname + " element is checked");

		}
	}
	public String getTextFromElement(WebElement element, String objectName)
	{
		String data = element.getText();
		BaseClasslog.info("text is extracted from "+objectName);
		extentReport.logTestInfo("text is extracted from "+objectName);		
		return data;
	}
	public void enterTextBox(WebElement element, String data, String objectName)
	{
		if (element.isDisplayed())
		{
			element.clear();
			element.sendKeys(data);
			BaseClasslog.info("data is entered in " + objectName + " textbox");
			extentReport.logTestInfo("data is entered in " + objectName + " textbox");

		} else {
			BaseClasslog.info(objectName + " element is not displayed");
			extentReport.logTestInfo( objectName + " element is not displayed");

		}
	}

	public  AndroidDriver switchContext(AndroidDriver driver)
	{
		Set<String> contextHandles = driver.getContextHandles();
		BaseClasslog.info("Number of contextHandles: " + contextHandles.size());
    	int i=0;
    	String Contextname=null;
    	for(String contextHandle : contextHandles)//NATIVE_APP,WEBVIEW_com.androidsample.generalstore
    	{
    		if(i==0)
    		{
    	    	BaseClasslog.info("ContextHandle name: " + contextHandle);    			
    		}
    		else
    		{
    	    	BaseClasslog.info("ContextHandle name: " + contextHandle);    			
    	    	Contextname =  contextHandle;
    		}
    		i++;

    	}    	
    	driver.context(Contextname);
    	return driver;
	}
	
/*	public void implicitwait(int time)
	{
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);

	}

	public  void waitforVisibility( WebElement element, int time, String objectname)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	  //  wait.until(ExpectedConditions.visibilityOf(lp.country));		

		try
		{
			BaseClasslog.info(objectname + " is waiting for visibility");
			extentReport.logTestInfo(objectname + " is waiting for visibility");
			wait = new WebDriverWait(driver,time);
			wait.until(ExpectedConditions.visibilityOf(element));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			BaseClasslog.error(objectname+" is not visible");
			extentReport.logTestFailedWithTimeout(objectname+" is not visible");
			
		}	
		
	}
	*/


}
