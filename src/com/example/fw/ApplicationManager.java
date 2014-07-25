package com.example.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {

	private static WebDriver driver;
	public static String baseUrl;

	private static Properties properties;
	private static HibernateHelper hibernateHelper;
	private static ApplicationManager singleton;
	private static AccountHelper accountHelper;
	private static MailHelper mailHelper;
	private static JamesHelper jamesHelper;

	public ApplicationManager(Properties properties) {
		ApplicationManager.properties = properties;
	}

	public void stop() {
		driver.quit();
	}

	public static ApplicationManager getInstance() {
		if (singleton == null) {
			singleton = new ApplicationManager(properties);
		}
		return singleton;
	}

	public void setProperties (Properties properties) {
		this.properties = properties;
	}
	
	public String getProperty (String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public static WebDriver getDriver() {
		String browser = properties.getProperty("browser");
		if (driver == null) {
			if ("firefox".equals(browser)) {
				driver = new FirefoxDriver();
			} else if ("ie".equals(browser)) {
				driver = new InternetExplorerDriver();
			} else {
				throw new Error("Unsupported browser" + browser);
			}

			baseUrl = properties.getProperty("baseURL");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(baseUrl);
		}
		return driver;
	}

	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}

	public MailHelper getMailHelper() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}

	public JamesHelper getJamesHelper() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}

}