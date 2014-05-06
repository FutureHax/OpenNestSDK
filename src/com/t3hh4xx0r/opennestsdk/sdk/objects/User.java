package com.t3hh4xx0r.opennestsdk.sdk.objects;

import java.util.Properties;

public class User {
	String username, password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	public Properties toProperties () {
		Properties properties = new Properties();
		properties.setProperty("username", username);
		properties.setProperty("password", password);
		return properties;
	}

	
}
