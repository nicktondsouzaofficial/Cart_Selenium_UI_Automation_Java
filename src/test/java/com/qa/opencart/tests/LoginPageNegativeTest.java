package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider(name = "invalidLoginData")
	public Object[][] getNegativeLoginData() {
		return new Object[][] {
			{"testuser@tgmail.com","test123"},
			{"march2026@gmail.com","test@77788"},
			{"","test123"},
			{"",""}
		};
	}
	
	@Test(dataProvider = "invalidLoginData")
	public void negativeLoginTest(String username, String password) {
		Assert.assertTrue(loginpage.doLoginInvalidCredentials(username, password));
	}

}
