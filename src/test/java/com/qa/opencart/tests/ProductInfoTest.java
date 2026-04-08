package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginpage.doLogin(properties.getProperty("username").trim(), properties.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {			
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] {			
			{"Macbook", "MacBook Pro",4},
			{"Samsung", "Samsung SyncMaster 941BW",1},
			{"canon", "Canon EOS 5D",3}
		};
	}
	
	@DataProvider
	public Object[][] getProductDataFromExcel() {
		return ExcelUtil.getTestData("Product");
	}
	
	@Test(dataProvider = "getProductDataFromExcel")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, productName, "Product header is not matching");
	}
	
	@Test(dataProvider = "getProductImages")
	public void productImageCountTest(String searchKey, String productName, int expectedImageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualProductImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualProductImageCount, expectedImageCount,"Product image count is not matching");
	}
	
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actualProductInfoMap = productInfoPage.getProductData();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(actualProductInfoMap.get("exTaxPrice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	/**
	 * AAA - Arrange, Act, Assert means in the test method we need to arrange the test data, 
	 * then we need to perform the action and then we need to assert the actual vs expected result
	 * Only one assert in one test method, if we have multiple asserts then we need to break the test method into multiple test methods
	 * One hard assert and multiple soft asserts.
	 */
}
