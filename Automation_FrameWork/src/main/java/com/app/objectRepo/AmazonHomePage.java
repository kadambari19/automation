package com.app.objectRepo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.lib.CommonLib;
import com.app.lib.FilePath;
import com.app.lib.GenericLib;

public class AmazonHomePage {
	
	private WebDriver driver;
	private CommonLib common;

	public AmazonHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		common= new CommonLib();
	}
	
	//search btn
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBtn;
	
	public void clickOnSearchBtn(String fPath,String sheet,int rowNum,int cellNum)
	{
		
		common.waitForElementToBePresent(searchBtn);
		try {
			searchBtn.click();
			searchBtn.sendKeys(GenericLib.getData(fPath, sheet, rowNum, cellNum),Keys.ENTER);
		} catch (Throwable e) {
			
		}	
		//searchBtn.click();
	}
	
	//getting text
	@FindBy(xpath="//span[text()='Apple iPhone XR (64GB) - Yellow']/../../../../../../..//span[@class='a-price-whole']")
	private WebElement priceTag;
	
	public int getPriceTag()
	{
		int amazonPrice =Integer.parseInt(priceTag.getText()) ;
		return amazonPrice;
	}
	
	//navigate to new tab
	public void navigateToNewTab()
	{
		Robot rob = null;
		try {
			rob = new Robot();
		} catch (AWTException e1) {
			
		}
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_T);
		common.switchToChildWindow();
		String url = null;
		try {
			url = GenericLib.getValue(FilePath.propPath,"FlipkartUrl");
		} catch (Throwable e) {
			
		}
		driver.get(url);
	}
	
}
