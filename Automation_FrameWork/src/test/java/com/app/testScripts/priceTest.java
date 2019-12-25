package com.app.testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.lib.BaseClass;
import com.app.lib.FilePath;
import com.app.objectRepo.AmazonHomePage;
import com.app.objectRepo.FlipkartHomePage;
import com.app.reports.ExtentReport;
import com.aventstack.extentreports.Status;

public class priceTest extends BaseClass{

	@Test
	public void productPriceCompare()
	{
		
		AmazonHomePage ahome = new AmazonHomePage(driver);
		//validation of amazon home page title
		String aExpTitle = "Online Shopping site";
		String aActTitle = driver.getTitle();
		System.out.println(aActTitle);
		boolean amazonTitle = aActTitle.contains(aExpTitle);
		Assert.assertTrue(amazonTitle);
		ExtentReport.testlog.log(Status.INFO, "amazon page loaded successfully");
				
		ahome.clickOnSearchBtn(FilePath.excelPath,"sheet1",0, 0);
		
		//validation of amazon product page
		String expTitle1 = "Amazon.in:";
		String actTitle1 = driver.getTitle();
		boolean amazonProductPage = actTitle1.contains(expTitle1);
		Assert.assertTrue(amazonProductPage);
		ExtentReport.testlog.log(Status.INFO, "amazon product page loaded successfully");
		int amazonPrice = ahome.getPriceTag();
		System.out.println(amazonPrice);
		ahome.navigateToNewTab();
		
		FlipkartHomePage fhome = new FlipkartHomePage();
		//validation of flipkart home page title
		String fExpTitle = "Online Shopping";
		String fActTitle = driver.getTitle();
		boolean flipkartTitle = fActTitle.contains(fExpTitle);
		Assert.assertTrue(flipkartTitle);
		ExtentReport.testlog.log(Status.INFO, "flipkart page loaded successfully");
		fhome.clickOnCloseBtn();
		fhome.clickOnSearchBtn(FilePath.excelPath,"sheet1",0, 0);
		
		//validation of flipkart product page
		String fExpTitle1 = "Amazon.in:";
		String fActTitle1 = driver.getTitle();
		boolean flipkartProductPage = fActTitle1.contains(fExpTitle1);
		Assert.assertTrue(flipkartProductPage);
		ExtentReport.testlog.log(Status.INFO, "flipkart product page loaded successfully");
		int flipkartPrice = fhome.getPrice();
		System.out.println(flipkartPrice);
		
		//validation
		
		if(amazonPrice<flipkartPrice)
		{
			ExtentReport.testlog.log(Status.INFO, "price of the product is less in amazon");
			System.out.println("price of the product is less in amazon compared to flipkart");
		}
		else
		{
			ExtentReport.testlog.log(Status.INFO, "price of the product is less in flipkart");
			System.out.println( "price of the product is less in flipkart compared to amazon");
		}
			
		
	}
}
