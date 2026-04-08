package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.utils.CsvUtil;
import com.qa.utils.ExcelUtil;
import com.qa.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {
	
	//Sequence is - Base Test (Before Test) -> Registration Page Test (Before Class) -> Registration Page Test (Test)
	
	
	@BeforeClass
	public void goToRegistrationPage() {
		registration = loginpage.navigateToRegisterPage();
	}
	
	
	/**We should use data provider in the code because we can run the same test with different sets of data. 
	It is a good practice to use data provider in the code because it makes the code more readable and maintainable. 
	It also helps us to avoid code duplication. We can use data provider in the code to run the same test with different sets of data. 
	It is a good practice to use data provider in the code because it makes the code more readable and maintainable. It also helps us to avoid code duplication.
	
	Excel sheets are not preferred because they are not easy to maintain and they are not easy to read. They are also not easy to debug. 
	If we have a large number of test cases, then it is difficult to maintain the excel sheet. 
	It is also difficult to read the excel sheet. It is also difficult to debug the excel sheet. 
	If we have a large number of test cases, then it is difficult to maintain the excel sheet. It is also difficult to read the excel sheet. 
	It is also difficult to debug the excel sheet.
	*/
	
	
	/**
	 * This data provider is providing the data to the test method. We can have multiple data providers in a class and we can use them in different test methods.
	 * @return
	 */
	@DataProvider
	public Object[][] getRegistrationData() {
		Object[][] data = {
				{"Tom", "Automation", "8888876576", "Tom@123", "Yes"},
				{"Peter", "Automation", "8888876576", "Peter@123", "No"},
				{"John", "Automation", "8888876576", "John@123", "Yes"}
		};
		return data;
	}
	
	/**
	 * This data provider is reading the data from excel sheet and providing to the test method. 
	 * We can have multiple data providers in a class and we can use them in different test methods.
	 * @return
	 */
	@DataProvider
	public Object[][] getRegistrationDataFromExcel() {
		Object[][] data = ExcelUtil.getTestData("Register");
		return data;
	}
	
	
	/**
	 * This data provider is reading the data from csv file and providing to the test method. 
	 * We can have multiple data providers in a class and we can use them in different test methods.
	 * @return
	 */
	@DataProvider
	public Object[][] getRegistrationDataFromCSV() {
		Object[][] data = CsvUtil.csvData("OpenCartTestDataCSV");
		return data;
	}
	
	@Test(dataProvider="getRegistrationDataFromCSV")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		registration.userRegister(firstName, lastName,StringUtils.getRandomEmail(), telephone, password, subscribe);
	}
}
