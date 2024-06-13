package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class GoogleWebViewPage extends BasePage{

	public GoogleWebViewPage(AndroidDriver driver) 
	{
		super(driver);
	}
	public GoogleWebViewPage(IOSDriver driver)
	{
		super(driver);
	}
	
	@FindBy(name = "q")
	public WebElement googleTxtbox;

	public void sendtxt(String data)
	{
		enterTextBox(googleTxtbox, data, "Google Search Text");
	}

}
