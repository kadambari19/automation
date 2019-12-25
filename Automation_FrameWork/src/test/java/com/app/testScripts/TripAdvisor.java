package com.app.testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.lib.BaseClass;
import com.app.lib.CommonLib;
import com.app.lib.FilePath;
import com.app.objectRepo.TripAdvisorPage;
import com.app.reports.ExtentReport;
import com.aventstack.extentreports.Status;

public class TripAdvisor extends BaseClass{

	CommonLib lib = new CommonLib();
	@Test
	public void tripTest()
	{
		TripAdvisorPage trip = new TripAdvisorPage(driver);
		
		trip.clickOnSearchBtn(FilePath.excelPath,"sheet1",0, 1);
		
		//lib.runScript("window.scrollBy(0,5000");
		trip.clickOnWriteReview();
		lib.switchToChildWindow();
		boolean value = trip.moveOnRating();
		Assert.assertTrue(value);
		ExtentReport.testlog.log(Status.PASS, "ratings are highlighted");
		
		trip.writeReview(FilePath.excelPath,"sheet1",0, 1);
		trip.review(FilePath.excelPath,"sheet1",1, 0);
		
		boolean serv = trip.service();
		Assert.assertTrue(serv);
		ExtentReport.testlog.log(Status.PASS, "service ratings are highlighted");
		
		boolean loc = trip.location();
		Assert.assertTrue(loc);
		ExtentReport.testlog.log(Status.PASS, "location ratings are highlighted");
		
		boolean room = trip.rooms();
		Assert.assertTrue(room);
		ExtentReport.testlog.log(Status.PASS, "room ratings are highlighted");
		
		trip.clickCheckbox();
	}
}
