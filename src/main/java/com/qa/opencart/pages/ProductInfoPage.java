package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.utils.ElementUtil;

public class ProductInfoPage {

	public WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By header = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img"); // tag.tagname tag
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private final static Logger log = LogManager.getLogger(ProductInfoPage.class);
	
	private Map<String,String> productInfoMap;

	/**
	 * This method is used to get the product header text
	 * 
	 * @return
	 */
	public String getProductHeader() {
		String headerVal = eleUtil.waitForElementVisible(header, AppConstant.DEFAULT_MEDIUM_WAIT).getText();
		log.info("Product header is: " + headerVal);
		return headerVal;
	}

	/**
	 * This method is used to get the count of product images
	 * 
	 * @return
	 */
	public int getProductImagesCount() {
		int imageCount = eleUtil.waitForElementsVisible(productImages, AppConstant.DEFAULT_MEDIUM_WAIT).size();
		log.info("Product images count is: " + imageCount);
		return imageCount;
	}
	

	/**
	 * This method is used to get the product price data
	 * Uses hashmap to store the key value pair of price data
	 */
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstant.DEFAULT_MEDIUM_WAIT);
		log.info("Product meta data list count is: " + metaList.size());
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaArr[] = metaText.split(":");
			String metaKey = metaArr[0].trim();
			String metaValue = metaArr[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}
	
	
	/**
	 * This method is used to get the product price data
	 * Uses hashmap to store the key value pair of price data
	 */
	private void getProductPrice() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstant.DEFAULT_MEDIUM_WAIT);
		log.info("Product price data list count is: " + priceList.size());
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", exTaxPrice);
	}
	
	
	
	public Map<String,String> getProductData() {
		productInfoMap = new HashMap<String,String>();
		productInfoMap.put("productName", getProductHeader());
		productInfoMap.put("imageCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPrice();
		System.out.println(productInfoMap);
		return productInfoMap;
	}
}
