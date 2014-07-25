package com.example.fw;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriverHelperBase extends HelperBase {

	protected WebDriver driver;
	public static boolean acceptNextAlert = true;
	private WebDriverWait wait;

	public WebDriverHelperBase(ApplicationManager manager) {
		super(manager);
		this.driver = ApplicationManager.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	public void openURL(String string) {
		driver.get(manager.getProperty("baseURL") + string);
	}

	public void openAbsoluteURL(String string) {
		driver.get(string);
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
/*
	public WebElement findElement(By locator) {
		
		return driver.findElement(locator);
		}
*/	
	public WebElement findElement(By linkText) {
		try {
		return driver.findElement(linkText);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	protected int countElements(By locator) {
		return findElements(locator).size();
	}

	protected void type(By locator, String text) {
		if (text != null || text != "") {
			WebElement element = findElement(locator);
			element.clear();
			element.sendKeys(text);
		}
	}

	protected void click(By locator) {
		findElement(locator).click();
	}

	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(findElement(locator)).selectByVisibleText(text);
		}
	}

	protected void selectByIndex(By locator, int index) {
		if (index != 0) {
			new Select(findElement(locator)).selectByIndex(index);
		}
	}

	protected boolean isElementPresent(By locator) {
		try {
			findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
