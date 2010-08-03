package com.gricai.central.server.dbManager;

import java.sql.SQLException;

public abstract class SQLQuery {

	/**
	 * evaluates any SQL Query to prepared statement string 
	 * @return 
	 * @throws Exception
	 */
	protected abstract String evaluate() throws Exception;
	
	/**
	 * executes Query
	 * @throws SQLException
	 */
	public abstract void execute() throws SQLException;
}
