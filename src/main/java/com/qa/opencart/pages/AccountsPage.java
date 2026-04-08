package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.utils.ElementUtil;

public class AccountsPage {

	public WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
		
		private final By headers = By.cssSelector("div#content h2");
		private final By logoutLink = By.linkText("Logout");
		private final By searchField = By.name("search");
		private final By searchButton = By.cssSelector("div#search button");
		private static final Logger log = LogManager.getLogger(AccountsPage.class);
		
		public List<String> getAccountPageHeaders() {
			List<WebElement> headersList = eleUtil.waitForElementsPresence(headers, AppConstant.DEFAULT_SHORT_WAIT);
			log.info("Total headers on account page: " + headersList.size());
			List<String> headersValueList = new ArrayList<String>();
			for(WebElement e : headersList) {
				String text = e.getText();
				headersValueList.add(text);
			}
			
			return headersValueList;
		}
		
		public boolean isLogoutLinkExist() {
			WebElement logoutEle =  eleUtil.waitForElementVisible(logoutLink, AppConstant.DEFAULT_LONG_WAIT);
			return eleUtil.isElementDisplayed(logoutEle);
		}
		
		public SearchResultsPage doSearch(String searchKey) {
			WebElement searchBoxEle = eleUtil.waitForElementVisible(searchField, AppConstant.DEFAULT_MEDIUM_WAIT);
			searchBoxEle.clear();
			searchBoxEle.sendKeys(searchKey);
			eleUtil.doClick(searchButton);
			return new SearchResultsPage(driver);
		}	
}
