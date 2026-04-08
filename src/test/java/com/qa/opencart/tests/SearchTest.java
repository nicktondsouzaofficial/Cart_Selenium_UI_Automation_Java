package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchSetup() {
		accPage = loginpage.doLogin(properties.getProperty("username").trim(), properties.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {			
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider = "searchData")
	public void searchTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, productName, "Product header is not matching");
	}
}
