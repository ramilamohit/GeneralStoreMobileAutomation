package utilities;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ActionUtils {
	
	public static final Origin view = Origin.viewport();
	public static final Duration ZERO_SEC = Duration.ofSeconds(0);
	public static Duration DurSec(int sec)
	{
		Duration duration = Duration.ofSeconds(sec);
		return duration;
	}
	
	public static void pinchzoom(AndroidDriver driver) throws InterruptedException
	{
		PointerInput finger1 = new PointerInput(Kind.TOUCH,"finger1");		
		Sequence swipe1 = new Sequence(finger1,1);
		swipe1.addAction(finger1.createPointerMove(ZERO_SEC, view, 440, 1230));
		swipe1.addAction(finger1.createPointerDown(0));
		swipe1.addAction(finger1.createPointerMove(DurSec(3), view, 100, 1230));
		swipe1.addAction(finger1.createPointerUp(0));
	
		PointerInput finger2 = new PointerInput(Kind.TOUCH,"finger2");		
		Sequence swipe2 = new Sequence(finger2,1);
		swipe2.addAction(finger2.createPointerMove(ZERO_SEC, view, 460, 1230));
		swipe2.addAction(finger2.createPointerDown(0));
		swipe2.addAction(finger2.createPointerMove(DurSec(3), view, 930, 1230));
		swipe2.addAction(finger2.createPointerUp(0));
		Thread.sleep(5000);
		driver.perform(Arrays.asList(swipe1,swipe2));		
	}

	public static void dragAndDrop(AndroidDriver driver)//long press
	{
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence dnd = new Sequence(finger, 1);
		dnd.addAction(finger.createPointerMove(ZERO_SEC, view, 0,0));
		dnd.addAction(finger.createPointerDown(0));
		dnd.addAction(new Pause(finger, DurSec(2)));
		dnd.addAction(finger.createPointerMove(DurSec(3), view, 500, 500));
		dnd.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dnd));
	}

	public static void doubleTap(AndroidDriver driver) 
	{
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence dt = new Sequence(finger, 1);
		dt.addAction(finger.createPointerMove(ZERO_SEC, view, 0,0));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dt));	
	}
	public static AndroidDriver scroll(AndroidDriver driver, int x1, int y1, int x2, int y2 )
	{
	PointerInput finger = new PointerInput(Kind.TOUCH,"finger");		
	Sequence swipe = new Sequence(finger,1);
	swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), x1, y1));
	swipe.addAction(finger.createPointerDown(0));
	swipe.addAction(finger.createPointerMove(Duration.ofSeconds(3), Origin.viewport(), x2, y2));
	swipe.addAction(finger.createPointerUp(0));
	driver.perform(Arrays.asList(swipe));
	return driver;
	}
	
	public static AndroidDriver ClickGesture(AndroidDriver driver, WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("mobile: clickGesture", ImmutableMap.of("elementId",((RemoteWebElement)element).getId()));
		return driver;
	}
	
	public static AndroidDriver longClickGesture(AndroidDriver driver, WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),"duration",2000));
		return driver;

	}
		
	public static AndroidDriver swipeGesture(AndroidDriver driver, WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),"direction","down","percent",0.75));
		return driver;

	}
	public static AndroidDriver scrollToEnd(AndroidDriver driver)
	{
		boolean canScrollMore;
		do
		{
			canScrollMore = (Boolean)((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction","down","percent",0.9));
		}while(canScrollMore);
		
		return driver;
	}
	
	public static AndroidDriver hideKeyBoard(AndroidDriver driver)
	{
		driver.hideKeyboard();
		return driver;
	}
	public static AndroidDriver pressAndroidBackKey(AndroidDriver driver)
	{
    	driver.pressKey(new KeyEvent(AndroidKey.BACK));
		return driver;
	}

}

