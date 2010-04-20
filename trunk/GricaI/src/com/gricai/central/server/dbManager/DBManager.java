package com.gricai.central.server.dbManager;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DBManager {
	
	private static final String DB_LOCATION = "localhost:xxxx/db_gricai";
	private static final String USERNAME = "";
	private static final String password = "";

	public abstract Connection connect();
	
	public abstract void execute(SQLQuery query);
	public abstract ResultSet select(SQLSelect query);
}
