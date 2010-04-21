package com.gricai.central.server.dbManager;

import java.sql.SQLException;

public abstract class SQLQuery {

	public abstract String evaluate() throws Exception;
	public abstract Object execute() throws SQLException;
}
