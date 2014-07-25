package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends WebDriverHelperBase{

	public AccountHelper(ApplicationManager applicationManager) {
		super(applicationManager); 
	}

	public void signup(User user) {
		openURL("signup_page.php");
		//openURL("/");
		//click(By.cssSelector("bracket-link"));
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.cssSelector("input.button"));
		
		WebElement errorMessage = findElement(By.cssSelector("table.class50 tbody tr td p"));
		if (errorMessage !=null) {
			throw new RuntimeException(errorMessage.getText());
		}

		pause(5000);
		Msg msg = manager.getMailHelper().getNewMail(user.login, user.password);
		String confimationLink = msg.getConfirmationLink();
		openAbsoluteURL(confimationLink);
		
		type(By.name("password"), user.password);
		type(By.name("password1"), user.password);
		click(By.cssSelector("input.button"));
		
	}

	public String loggedUser() {
		WebElement element = findElement(By.cssSelector("td.login-info-left span"));
		
		return element.getText();
	}

	public void login(User user) {
		openURL("/");
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.cssSelector("input.button"));

		
	}

}
