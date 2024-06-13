package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage
{
	public LoginPage(AndroidDriver driver)
	{
		super(driver);
	}
	public LoginPage(IOSDriver driver)
	{
		super(driver);
	}
	

	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id = "android:id/text1")
	public WebElement country;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));")
	public WebElement countrySelection;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahamas\"));")
	public WebElement countrySelection1;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));")
	public WebElement countrySelection2;
	
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement name;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	public WebElement radioFemale;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	@iOSXCUITFindBy(id="")
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	public WebElement toastMsg;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	public WebElement radioMale;
	
	
	public void ClickDropdown()
	{	    	
		clickElement(country, "Country Dropdown");
	}
	
	public void EnterName(String data)
	{
		enterText(name, data, "Login name");
	}
	public String getErrorToastMsg()
	{
		String message = getAttributFromElement(toastMsg,"name", "Toast Error Message");
		
		return message;
	}
	public void SelectGender(String gender)
	{
		if(gender.equalsIgnoreCase("female"))
		{
		checkboxclick(radioFemale,"Check Female Gender");
		}
		else if(gender.equalsIgnoreCase("male"))
		{
  		checkboxclick(radioMale,"Check Male Gender");	
		}
		else
		{
			System.out.println("You didn't select any gender");
		}
	}
	public String chkDefaultGender()
	{
		String checked = getAttributFromElement(radioMale,"checked", "Male Gender CheckBox");
		return checked;
	}
	public AndroidDriver ClickLetsShop(AndroidDriver driver)
	{
		clickElement(btnLetsShop, "Let's shop");
		return driver;
	}
	
}
