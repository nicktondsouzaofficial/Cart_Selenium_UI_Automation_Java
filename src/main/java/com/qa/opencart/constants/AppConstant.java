package com.qa.opencart.constants;

import java.util.List;

public class AppConstant {

	//Login page constants
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final int FOOTER_LINKS_COUNT = 15;
	
	//Accounts page constants
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final int ACC_PAGE_HEADER_COUNT = 4;
	
	//Default timeouts
	public static final int DEFAULT_SHORT_WAIT = 5;
	public static final int DEFAULT_MEDIUM_WAIT = 10;
	public static final int DEFAULT_LONG_WAIT = 20;
	
	//Header constants
	public static List<String> expectedAccPageHeadersList = List.of("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	//Registration page constants
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String INVALID_LOGIN_ERROR_MESSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final String BLANK_ERROR_MESSG = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	
}
