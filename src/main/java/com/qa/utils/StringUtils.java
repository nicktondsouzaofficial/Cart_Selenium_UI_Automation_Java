package com.qa.utils;

public class StringUtils {

	public static String getRandomEmail() {
		String email = "uiAutomation" + System.currentTimeMillis() + "@mail.com";
		System.out.println(email);
		return email;
	}
}
