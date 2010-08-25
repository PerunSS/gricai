package com.gricai.nio.framework.impl;

import com.gricai.nio.framework.Connection;

public class User {

	private String username;
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
