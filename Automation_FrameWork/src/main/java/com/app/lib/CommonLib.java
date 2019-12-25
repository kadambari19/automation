package com.app.lib;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonLib extends BaseClass {
	
	private JavascriptExecutor jse = (JavascriptExecutor)driver;
	private String alert;
	private String parentId;
	private String childId; 
	private Set<String> windows;
	
	
	/**
	 * executing java script
	 * @param script
	 * @return
	 */
	public Object runScript(String script) {
		return jse.executeScript(script);
	}
	
	/**
	 *  wait till page gets loaded
	 */
	public void waitForPageToLoad(){
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver);
			fwait.pollingEvery(Duration.ofMillis(fluentPolling));
			fwait.withTimeout(Duration.ofSeconds(fluentTimeout));
			fwait.ignoring(NoSuchElementException.class);

		fwait.until(new ExpectedCondition<Boolean>(){
			@Override
			public Boolean apply(WebDriver input) {
				String state = (String) runScript("return document.readyState");
				if(state.equals("complete")) {
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * wait till element gets loaded
	 * @param element
	 */
	public void waitForElementToBePresent(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, explicit);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * wait for element and check is it displayed
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitForElementToAppear(WebElement element) throws InterruptedException {
		int timeCount =0;
		while(timeCount<20) {
			try {
				element.isDisplayed();
				break;
			}catch (Exception e) {
				Thread.sleep(fluentPolling);
				timeCount++;
			}
		}
	}
	
	public void switchToChildWindow()
	{
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.Iterator<String> iterator = windowHandles.iterator();
		String parent = iterator.next();
		String child=iterator.next();
		driver.switchTo().window(child);
	}
	
	public void moveElement(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	
	}


