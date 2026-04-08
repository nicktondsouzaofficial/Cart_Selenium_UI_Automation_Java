package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.utils.ElementUtil;

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By logo = By.cssSelector("img.img-responsive");
	private final By search = By.name("search");
	private final By footerLinks = By.cssSelector("footer li a");
	
	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}
	
	public boolean isSearchExist() {
		return eleUtil.isElementDisplayed(search);
	}
	
	public List<String> footerLinksExist() {
		List<WebElement> footerList = eleUtil.waitForElementsPresence(footerLinks, AppConstant.DEFAULT_MEDIUM_WAIT);
		List<String> footerValList = new ArrayList<String>();
		for(WebElement e : footerList) {
			String text = e.getText();
			System.out.println(text);
			footerValList.add(text);
		}
		return footerValList;
	}
}
