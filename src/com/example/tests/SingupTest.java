package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.fw.AccountHelper;
import com.example.fw.JamesHelper;
import com.example.fw.User;

public class SingupTest extends TestBase {
	
	public 	User user = new User().setLogin("testuser2")
			.setEmail("testuser2@localhost.localdomain")
			.setPassword("12345");

	private JamesHelper james;

	private AccountHelper accHelper;
	
	@BeforeClass
	public void createMailUser() {
		james = app.getJamesHelper();
		accHelper = app.getAccountHelper();
		if (!james.doesUserExist(user.login)){
			james.createUser(user.login, user.password);
		}
	}
	
	@Test
	public void newUserShouldSignup() {
		accHelper.signup(user);		
		accHelper.login(user);
		assertThat(accHelper.loggedUser(), equalTo(user.login));
	}
	
	/*@Test
	public void existingUserShouldNotSignup() {
		try {
			accHelper.signup(user);	
		} catch (Exception e) {
			assertThat(e.getMessage(), containsString(""));
			return;
		}
		System.out.println("Exception expected");
	}
*/
	@AfterClass
	public void deleteMailUser() {
		if (james.doesUserExist(user.login)){
			james.deleteUser(user.login);
		}
	}
	
}
