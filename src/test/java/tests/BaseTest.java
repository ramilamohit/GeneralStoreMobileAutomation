package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;

import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utilities.Constants;
import utilities.ExtentReportsUtility;


public class BaseTest 
{
	public static AppiumDriver driver =null;
	AppiumDriverLocalService service;
	protected static Logger BaseTestlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	
	@BeforeSuite
	public void startServer()
	{
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\ramil\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(5000)).build();
		service.start();
	}
	
    @AfterSuite
	public void stopServer()
	{
	    driver.quit();
		service.stop();	
	}
    
    @AfterMethod
	public void teardown() {
		 LogEntries logEntries = driver.manage().logs().get("logcat");
		 for(LogEntry logEntry : logEntries) {
			 System.out.println(logEntry.getMessage());
		 }
	} 
	public static AppiumDriver getDriver(String driverName) throws MalformedURLException
	{
		String name = driverName.toLowerCase();
		switch(name) {
		case "android":
			URL url = new URL("http://127.0.0.1:4723/");
			UiAutomator2Options dc = new UiAutomator2Options();
			dc.setPlatformName("android");
			dc.setPlatformVersion("14");
			dc.setDeviceName("192.168.1.24:44831");
			dc.setAutomationName("uiAutomator2");
			dc.setAppPackage(Constants.PACKAGE_NAME);
			dc.setAppActivity(Constants.ACTIVITY_NAME);
			dc.setChromedriverExecutable("C:\\Users\\ramil\\ChromeDriver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new AndroidDriver(url,dc);
			break;
		case "ios":
			URL url1 = new URL("http://127.0.0.1:4723/");
			XCUITestOptions xct = new XCUITestOptions();
			xct.setPlatformName("IOS");
			xct.setPlatformVersion("17.31");
			xct.setDeviceName("192.168.1.24:44831");
			xct.setAutomationName("XCUITDriver");
			xct.setApp(Constants.PACKAGE_NAME);			
			driver = new IOSDriver(url1, xct);
			break;

		default:
			break;

		}
		return driver;
	}
	
	public static void takescreenshot(String filepath)
	{
		TakesScreenshot screenCapture = (TakesScreenshot)driver;
		File src = screenCapture.getScreenshotAs(OutputType.FILE);
		File destination = new File(filepath);
		try {
			Files.copy(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
			//BaseTestlog.info("Screenshot is not captured");
			//extentReport.logTestInfo("Screenshot is not captured");

		}
		
	}

	

}
