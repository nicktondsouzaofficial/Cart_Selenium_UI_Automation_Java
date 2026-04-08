package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlightElement;
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = LogManager.getLogger(DriverFactory.class);
	public OptionsManager optionsManager;

	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * name and return the driver reference
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {

		log.info("Browser name is: " + prop.getProperty("browser"));
		String browserName = prop.getProperty("browser").trim();
		
		highlightElement = prop.getProperty("hightlightElement").trim();
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
			threadLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			threadLocalDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;

		case "edge":
			threadLocalDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			log.error(AppError.INVALID_BROWSER_MESSAGE + " - " + browserName);
			throw new FrameworkException("====INVALID BROWSER NAME EXCEPTION====");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	/**
	 * This method is used to initialize the properties from config.properties file
	 * and return the prop reference
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties initProperties() throws IOException {
		prop = new Properties();
		FileInputStream ip =null;
		
		String envName = System.getProperty("env");
		log.info("Running tests on environment: " + envName);
		try {
		if(envName == null) {
			log.warn("No environment is specified, running tests on default environment: QA");
			ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		else {
			switch (envName.trim().toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;

			case "dev":
				ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;

			case "stage":
				ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;

			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.prod.properties");
				break;

			default:
				log.error(AppError.INVALID_ENV_MESSAGE + " - " + envName);
				throw new FrameworkException("====INVALID ENVIRONMENT NAME EXCEPTION====");
				}
			}
		}
		
		catch (FileNotFoundException e) {
			log.error("Properties file not found: " + e.getMessage());
			throw e;
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			log.error("Failed to load properties file: " + e.getMessage());
			throw e;
		} finally {
			if (ip != null) {
				ip.close();
			}
		}
		return prop;
	}
	
	
	/** below three methods are used to get the screenshot in three different formats - file, byte array and base64 string. We can use any of these methods as per our requirement. 
	 * For example, if we want to save the screenshot in a file then we can use getScreenshotFile method and if we want to attach the screenshot in the report then we can use getScreenshotBytes method and if we want to embed the screenshot in the report then we can use getScreenshotBase64 method. 
	 * We can also use these methods in the listeners to capture the screenshot on test failure and attach it in the report. 
	 * We can also use these methods in the test methods to capture the screenshot at any point of time during the execution of the test cases. */
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	
	public static byte[] getScreenshotBytes() {
		byte[] srcBytes = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
		return srcBytes;
	}
	
	public static String getScreenshotBase64() {
		String srcBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		return srcBase64;
	}
	
	
	
	
	/**
	 * This is used to get the local copy of the driver anytime during the execution of the test cases. 
	 * This will ensure that we are getting the same driver reference which is initialized in the initDriver method.
	 * @return
	 */
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
}
