package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.utils.ElementUtil;

public class SearchResultsPage {

	public WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resultsHeader = By.cssSelector("div#content h1");
	
	//Gives the count of search results displayed on the page
	public int getSearchResultsCount() {
		int searchResultsCount = eleUtil.waitForElementsPresence(searchResults, AppConstant.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("Total search results: " + searchResultsCount);
		return searchResultsCount;
	}
	
	//Gives the header text of the search results page
	public String getResultHeaderValue() {
		String header = eleUtil.doElementGetText(resultsHeader);
		System.out.println("Search results page header: " + header);
		return header;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLink = By.linkText(productName);
		System.out.println("Selected product name: " + productName);
		eleUtil.doClick(productLink);
		return new ProductInfoPage(driver);
	
	}
	
}
