package com.qa.opencart.base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.Registration;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {

	protected WebDriver driver;
	DriverFactory driverfactory;
	//protected because we want to use this in child class (LoginPageTest)
	protected LoginPage loginpage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected Properties properties;
	protected Registration registration;
	protected CommonsPage commonsPage;
	
	/**
	 * This method is used to initialize the driver and launch the browser and app
	 * @param browser
	 * @throws IOException
	 */
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browser) throws IOException {
		driverfactory = new DriverFactory();
		properties = driverfactory.initProperties();
		if(browser!=null) {
			properties.setProperty("browser", browser);
		}
		driver = driverfactory.initDriver(properties);
		loginpage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
