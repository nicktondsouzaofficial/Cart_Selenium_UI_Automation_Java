package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	/*Page class is a example of encapsulation
	 * private variables and methods are encapsulated in the class and we can access them through public methods.
	 */

	private WebDriver driver;
	private ElementUtil eleUtil;

	// private By locators: Page objects
	private final By emailId = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	private final By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page methods and actions
	@Step("Getting login page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs("Account Login", AppConstant.DEFAULT_SHORT_WAIT);
		log.info("Login page title is: " + title);
		return title;
	}

	@Step("Getting login page URL...")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains("route=account/login", AppConstant.DEFAULT_SHORT_WAIT);
		log.info("Login page URL is: " + url);
		return url;
	}

	@Step("Checking if forgot password link is displayed on login page...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("Checking if header is displayed on login page...")
	public boolean isHeaderExist() {
		return eleUtil.isElementDisplayed(header);
	}

	
	/*According to page object, we need to return the next page object after performing the action. 
	So, we need to return the AccountsPage object after performing the login action. But, for now, 
	we are returning the title of the page after performing the login action.*/
	@Step("Login with  correct username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		log.info("Login with username: " + username + " and password: " + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("Login with invalid username: {0} and password: {1}")
	public boolean doLoginInvalidCredentials(String usernameInvalid, String pwdInvalid) {
		log.info("Login with username: " + usernameInvalid + " and password: " + pwdInvalid);
		WebElement ele = eleUtil.waitForElementVisible(emailId, AppConstant.DEFAULT_MEDIUM_WAIT);
		ele.clear();
		ele.sendKeys(usernameInvalid);
		eleUtil.doSendKeys(password, pwdInvalid);
		eleUtil.doClick(loginBtn);
		String errorMessage = eleUtil.doElementGetText(loginErrorMsg);
		log.info("Login error message is: " + errorMessage);
		if(errorMessage.contains(AppConstant.INVALID_LOGIN_ERROR_MESSG) || errorMessage.contains(AppConstant.BLANK_ERROR_MESSG)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method will navigate to the register page by clicking on the register link and return the register page object.
	 */
	@Step("Navigate to register page...")
	public Registration navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstant.DEFAULT_MEDIUM_WAIT).click();
		return new Registration(driver);
	}
}
