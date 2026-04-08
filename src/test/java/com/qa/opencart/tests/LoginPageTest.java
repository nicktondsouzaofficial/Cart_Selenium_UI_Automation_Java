package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
/*First base test methods will be executed and then test methods will be executed. So, we can say that base test is the parent class and login page test is the child class.
 * So, we can use all the methods of base test in login page test class. We can also override the methods of base test in login page test class if required. 
 * We can also create multiple test classes for different pages and use the same base test class for all the test classes.
 * This is the beauty of OOPs concepts in Java. We can achieve reusability and maintainability of code by using OOPs concepts in Java.*/
import io.qameta.allure.Story;

/*Sequence of execution is alphabetical order of test methods. So, loginPageTitleTest will be executed first and then loginPageURLTest will be executed and then 
 * forgotPwdLinkExistTest will be executed and then headerExistTest will be executed and then loginTest will be executed.*/


//Sequence is -> Base Test (Before Test) -> Login Page Test (Test) -> Base Test (After Test)

@Epic("Epic - 100: Design Login Page for Open Cart Application")
@Feature("US - 101: Login Page features with title, URL, header and forgot password link")
@Story("US - 101: Login Page title, URL, header and forgot password link test")
public class LoginPageTest extends BaseTest {

	
	@Description("Login page title test")
	@Owner("Nickton")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		ChainTestListener.log("Login page title is: " + title);
		Assert.assertEquals(title, AppConstant.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page url test")
	@Owner("Nickton")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest() {
		String url = loginpage.getLoginPageURL();
		ChainTestListener.log("Login page URL is: " + url);
		Assert.assertTrue(url.contains(AppConstant.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Forgot password link test")
	@Owner("Nickton")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Description("Does header exist test")
	@Owner("Nickton")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void headerExistTest() {
		Assert.assertTrue(loginpage.isHeaderExist());
	}
	
	/**
	 *  This will make sure that this test will be executed at the end of the execution. 
	 */
	@Description("Login test with valid credentials")
	@Owner("Nickton")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = Integer.MAX_VALUE) 
	public void loginTest() {
		accPage = loginpage.doLogin(properties.getProperty("username").trim(), properties.getProperty("password").trim());
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);				
	}
	
}
