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
	 * executes SQL Query and returns result 
	 * @return 	ResultSet if SQLSelect
	 * 			int if SQLDelete, SQLUpdate, SQLInsert
	 * @throws SQLException
	 */
	public abstract Object execute() throws SQLException;
}
