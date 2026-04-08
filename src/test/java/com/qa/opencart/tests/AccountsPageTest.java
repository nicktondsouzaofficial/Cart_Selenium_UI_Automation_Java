package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetup() {
		accPage = loginpage.doLogin(properties.getProperty("username").trim(), properties.getProperty("password").trim());
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accountPageActualHeadersList = accPage.getAccountPageHeaders();
		Assert.assertEquals(accountPageActualHeadersList.size(), AppConstant.ACC_PAGE_HEADER_COUNT);
		Assert.assertEquals(accountPageActualHeadersList, AppConstant.expectedAccPageHeadersList);
	}
}
