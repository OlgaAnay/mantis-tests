package com.example.fw;

public class User {
	
	public String login;
	public String email;
	public String password;

	public User setLogin(String login) {
		this.login = login;
	return this;
	}

	public User setEmail(String email) {
		this.email = email;
	return this;
	}
	

	public User setPassword(String password) {
		this.password = password;
	return this;
	}

}
