package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

public class CommonPageTest extends BaseTest {
	
	@Test
	public void commonElementsOnLoginPageTest() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonsPage.isLogoExist());
		softAssert.assertTrue(commonsPage.isSearchExist());
		List<String> footerList = commonsPage.footerLinksExist();
		softAssert.assertEquals(footerList.size(), AppConstant.FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
	
	@Test
	public void commonElementsOnAccountsPageTest() {
		loginpage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonsPage.isLogoExist());
		softAssert.assertTrue(commonsPage.isSearchExist());
		List<String> footerList = commonsPage.footerLinksExist();
		softAssert.assertEquals(footerList.size(), AppConstant.FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
	
	

}
