package com.app.lib;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.app.reports.ExtentReport;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass implements Constants,FilePath{
	

	
	public WebDriver driver;
	
	/**
	 * Launch the selected browser
	 * @throws Throwable
	 */
	@BeforeClass
	public void configureBeforeClass() {
		
		String url = GenericLib.getValue(FilePath.propPath,"tripUrl");
		String browser = GenericLib.getValue(FilePath.propPath,"browser");

		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		try{
			driver.manage().timeouts().implicitlyWait(implicit, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	@AfterClass
	public void configureafterClass() {
		
	try{
		driver.quit();
	}catch (Exception e) {
		// TODO: handle exception
	}
	}

}
